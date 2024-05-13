package org.example.controller

import jakarta.servlet.http.HttpServletResponse
import org.apache.tomcat.util.http.fileupload.IOUtils
import org.example.service.ImageSearchUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ByteArrayResource
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.InputStreamResource
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.util.StreamUtils
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.util.*


@RestController
@RequestMapping("api/v1/upload/img")
class ControllerImage {

    @Autowired
    lateinit var imageService: ImageSearchUtils
    @RequestMapping(path = ["/{id}"], method = [RequestMethod.GET],
        produces = [MediaType.IMAGE_JPEG_VALUE]
    )
    @Throws(IOException::class)
    fun getImage(response: HttpServletResponse, @PathVariable(value = "id") idOrganization: Long): ResponseEntity<InputStreamResource> {

        val imgFile = File("фото")
        imgFile.writeBytes(imageService.findById(idOrganization).get().value!!)
        StreamUtils.copy(imgFile.inputStream(), response.outputStream)

        return ResponseEntity
            .ok()
            .contentType(MediaType.IMAGE_JPEG)
            .body(InputStreamResource(imgFile.inputStream()));
    }

    @PostMapping("/upload_org/")
    fun updateOrgImage(@RequestParam("image") file: MultipartFile,
                         @RequestParam("orgId") orgId: Long): ResponseEntity<Any> {
        // Обработка полученного файла
        try {
            // Сохранение файла на сервере, например
            imageService.insertForOrg(orgId, file)
            return ResponseEntity.ok().build()
        } catch (e: IOException) {
            e.printStackTrace()
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }

    @PostMapping("/upload_product/")
    fun updateproductImage(@RequestParam("image") file: MultipartFile,
                         @RequestParam("productId") orgId: Long): ResponseEntity<Any> {
        // Обработка полученного файла
        try {
            // Сохранение файла на сервере, например
            imageService.insertForOrg(orgId, file)
            return ResponseEntity.ok().build()
        } catch (e: IOException) {
            e.printStackTrace()
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }

    @RequestMapping(path = ["/"], method = [RequestMethod.GET], produces = [MediaType.IMAGE_JPEG_VALUE] )
    @Throws(IOException::class)
    fun getImages(response: HttpServletResponse, @RequestParam("ids") ids: List<Long>): ResponseEntity<List<ByteArrayResource>> {
        val images = mutableListOf<ByteArrayResource>()

        for (id in ids) {
            val imgFile = File(id.toString())
            imgFile.writeBytes(imageService.findById(id).get().value!!)
            val bytes = imgFile.readBytes()

            val resource = ByteArrayResource(bytes)
            images.add(resource)
        }

        return ResponseEntity
            .ok()
            .contentType(MediaType.IMAGE_JPEG)
            .body(images)
    }
}