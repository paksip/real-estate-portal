package bme.aut.szarch.realestateportal.service.kotlin

import bme.aut.szarch.realestateportal.service.kotlin.util.result.StorageMethodResult
import bme.aut.szarch.realestateportal.service.kotlin.util.result.StorageMethodResult.*
import org.slf4j.LoggerFactory
import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.FileSystemUtils
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.stream.Stream


@Service
@Transactional
open class StorageServiceImp : StorageService {


    val log = LoggerFactory.getLogger(this::class.java)
    private val rootLocation = Paths.get("filestorage")

    override fun uploadFiles(realEstateId: Long, file: MultipartFile): StorageMethodResult<Void> {
        return try {
            Files.copy(file.inputStream, rootLocation.resolve(file.originalFilename!! + realEstateId))
            Success
        } catch (e: IOException) {
            Failed("Occurred an issue during deleting", e)
        } catch (e: KotlinNullPointerException) {
            Failed("File not exists", e)
        }

    }

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

    override fun loadFiles(realEstateId: Long): StorageMethodResult<Stream<Path>> {
        return try {
            SuccessWithResult(Files.walk(rootLocation, 1)
                .filter { it != rootLocation }
                .filter { it.endsWith(realEstateId.toString()) }
                .map(rootLocation::relativize))
        } catch (e: IOException) {
            Failed("Occurred an issue during loading files. Related resource's id : $realEstateId", e)
        }
    }

    override fun loadFile(filename: String): StorageMethodResult<Resource> {
        return try {
            val file = rootLocation.resolve(filename)
            val resource = UrlResource(file.toUri())

            return if (resource.exists() || resource.isReadable) {
                SuccessWithResult(resource as Resource)
            } else {
                Failed("File does not exist. Related resource name is : $filename")
            }
        } catch (e: Exception) {
            Failed("Occurred an issue during loading files. Related resource name : $filename . Exception message: ${e.message}", e)
        }
    }
}

