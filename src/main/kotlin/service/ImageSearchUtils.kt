package org.example.service

import org.example.entity.Image
import org.example.organization.model.Organization
import org.example.organization.repository.OrganizationRepository
import org.example.repository.ImageRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Files
import java.nio.file.Paths
import java.util.Optional


@Service
class ImageSearchUtils {

    @Autowired
    lateinit var imageImpl: ImageRepository

    @Autowired
    lateinit var organizationImpl: OrganizationRepository

    fun findById(id: Long): Optional<Image?>{
        return imageImpl.findById(id);
    }
    fun insertForOrg(idOrg: Long, file: MultipartFile){
        val image = Image(value = file.bytes)
        imageImpl.save(image)

        val organization = organizationImpl.findById(idOrg).get()
        organization.idImage = image.id!!
        organizationImpl.save(organization)
    }
    private fun saveImageToResources(imageBytes: ByteArray, resourceName: String) {
        val resourcePath = Paths.get("src/main/resources/$resourceName")
        Files.write(resourcePath, imageBytes)
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