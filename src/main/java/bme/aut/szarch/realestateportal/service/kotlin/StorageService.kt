package bme.aut.szarch.realestateportal.service.kotlin


import bme.aut.szarch.realestateportal.service.kotlin.util.result.StorageMethodResult
import org.springframework.core.io.Resource
import org.springframework.http.ResponseEntity
import org.springframework.web.multipart.MultipartFile


interface StorageService {
    fun uploadFiles(realEstateId: Long, file: MultipartFile): ResponseEntity<Void>
    fun deleteAll(): StorageMethodResult<Void>
    fun init(): StorageMethodResult<Void>
    fun loadFiles(realEstateId: Long): List<String>
    fun loadFile(filename: String): ResponseEntity<Resource>
}
