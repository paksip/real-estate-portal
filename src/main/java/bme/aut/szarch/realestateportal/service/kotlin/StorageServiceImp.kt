package bme.aut.szarch.realestateportal.service.kotlin

import bme.aut.szarch.realestateportal.service.kotlin.util.addIdentifier
import bme.aut.szarch.realestateportal.service.kotlin.util.operations.create.executeCreateOperation
import bme.aut.szarch.realestateportal.service.kotlin.util.operations.read.executeRead
import bme.aut.szarch.realestateportal.service.kotlin.util.result.StorageMethodResult
import bme.aut.szarch.realestateportal.service.kotlin.util.result.StorageMethodResult.*
import org.slf4j.LoggerFactory
import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.FileSystemUtils
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.streams.asSequence
import bme.aut.szarch.realestateportal.service.kotlin.util.result.DataTransferResult.Success as DataTransferSuccess


@Service
@Transactional
open class StorageServiceImp : StorageService {
    val log = LoggerFactory.getLogger(this::class.java)
    private val rootLocation = Paths.get("filestorage")

    override fun uploadFiles(realEstateId: Long, file: MultipartFile) = executeCreateOperation(
        operationCall = { Files.copy(file.inputStream, rootLocation.resolve(file.originalFilename!!.addIdentifier(realEstateId.toString()))) }
    )

    override fun deleteAll(): StorageMethodResult<Void> {
        return if (FileSystemUtils.deleteRecursively(rootLocation.toFile())) {
            Success
        } else {
            Failed("Occurred an issue during deleting")
        }
    }

    override fun init(): StorageMethodResult<Void> {
        return try {
            if (!Files.exists(rootLocation)) {
                Files.createDirectory(rootLocation)
            }
            Success
        } catch (e: IOException) {
            Failed("Occurred an issue during init", e)
        }
    }

    override fun loadFiles(realEstateId: Long): List<String> {
        return try {
            Files.walk(rootLocation, 1)
                .asSequence()
                .filter { it != rootLocation }
                .map(rootLocation::relativize)
                .toList()
                .map { it.toFile() }
                .filter { it.nameWithoutExtension.endsWith(realEstateId.toString(), true) }
                .map { it.name }
        } catch (e: IOException) {
            emptyList()
        }
    }

    override fun loadFile(filename: String) = executeRead(
        getTargetEntity = {
            val file = rootLocation.resolve(filename)
            UrlResource(file.toUri()) as Resource
        },
        validationCall = { resource -> check(resource.exists() || resource.isReadable) { "Does not exist any resource with name :$filename" } },
        mappingCall = { resource -> resource },
        onSuccess = { resource ->
            val headers = HttpHeaders()
            headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.filename + "\"")
            DataTransferSuccess(HttpStatus.OK, resource, headers)
        }
    )
}

