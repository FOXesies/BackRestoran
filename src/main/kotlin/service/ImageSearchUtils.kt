package org.example.service

import org.example.entity.Image
import org.example.repository.ImageRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Service
import java.nio.file.Files
import java.util.Optional


@Service
class ImageSearchUtils {

    @Autowired
    lateinit var imageImpl: ImageRepository

    fun findById(id: Long): Optional<Image?>{
        return imageImpl.findById(id);
    }
    companion object {
        fun getInputStream(pathForImage: String): ByteArray? {
            val imgFile = ClassPathResource("${ConstantsPathImages.ORGANIZATION_PATH}/$pathForImage")
            return if(imgFile.exists())
                Files.readAllBytes(imgFile.file.toPath())
            else
                null
        }

    }

    object ConstantsPathImages {
        const val ORGANIZATION_PATH = "organizations_images"
    }
}