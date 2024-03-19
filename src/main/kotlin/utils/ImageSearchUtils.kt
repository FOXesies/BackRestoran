package org.example.utils

import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Service
import java.io.InputStream

@Service
class ImageSearchUtils {
    companion object {
        fun getInputStream(pathForImage: String): InputStream? {
            val imgFile = ClassPathResource("${ConstantsPathImages.ORGANIZATION_PATH}/$pathForImage")
            return if(imgFile.exists())
                imgFile.getInputStream();
            else
                null
        }

    }

    object ConstantsPathImages {
        const val ORGANIZATION_PATH = "organizations_images"
    }
}