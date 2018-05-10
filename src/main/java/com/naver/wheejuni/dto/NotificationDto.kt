package com.naver.wheejuni.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.naver.wheejuni.domain.Notification
import com.naver.wheejuni.domain.UserGroups
import com.naver.wheejuni.domain.UserNotificationInbox
import java.time.LocalDateTime

data class NotificationDto(

        @field:JsonProperty("issuedDate")
        val date: LocalDateTime = LocalDateTime.now(),

        val name: String? = null,

        @field:JsonProperty("id")
        val id: Long? = null,

        @field:JsonProperty("groups")
        val targetGroups: MutableSet<UserGroups>? = null,

        @field:JsonProperty("new")
        val new: Long? = null,

        @field:JsonProperty("notifications")
        val notifications: MutableList<Notification>? = null) {

    companion object {
        fun fromModel(inbox: UserNotificationInbox): NotificationDto {
            return NotificationDto(id = inbox.id, name = inbox.name, targetGroups = inbox.listeningGroups, new = inbox.unreadNotificationsCount, notifications = inbox.notifications)
        }
    }
}