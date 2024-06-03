package org.example.feedbacks.service

import org.example.feedbacks.entity.FeedBacks
import org.example.feedbacks.repository.FeedBacksRepository
import org.example.organization.repository.OrganizationRepository
import org.example.repository.BasicUserRepository
import org.example.uuid.repository.UUIDRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FeedBacksService {

    @Autowired
    private lateinit var feedBacksRepository: FeedBacksRepository

    @Autowired
    private lateinit var organizationRepository: OrganizationRepository

    @Autowired
    private lateinit var userRepository: BasicUserRepository

    @Autowired
    private lateinit var uuidRepository: UUIDRepository

    fun getAllfeedbacksByUser(idUser: Long): List<FeedBacks> {
        return feedBacksRepository.findByUser(userRepository.findById(idUser).get())
    }
    fun getAllfeedbacksByOrg(idOrg: Long): List<FeedBacks> {
        return feedBacksRepository.findByOrganization(organizationRepository.findById(idOrg).get())
    }
    fun getAllfeedbacksByUuid(uuid: Long): FeedBacks {
        return feedBacksRepository.findByUuidCustom(uuidRepository.findById(uuid).get())
    }

    fun getMiddleStar(idOrg: Long): List<Number?> {
        val feedbacks = getAllfeedbacksByOrg(idOrg)
        return if(feedbacks.isNotEmpty()) listOf(feedbacks.map { it.rating!! }.average(), feedbacks.size)
                else listOf(null, null)
    }

}