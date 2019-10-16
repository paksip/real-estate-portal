package bme.aut.szarch.realestateportal.service


import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;


interface StorageService {
    fun uploadFiles(realEstateId: Long, file: MultipartFile): StorageMethodResult<Void>
    fun deleteAll(): StorageMethodResult<Void>
    fun init(): StorageMethodResult<Void>
    fun loadFiles(realEstateId: Long): StorageMethodResult<Stream<Path>>
}
