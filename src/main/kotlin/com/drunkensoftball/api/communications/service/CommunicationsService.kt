package com.drunkensoftball.api.communications.service

interface CommunicationsService {
    fun sendEmail(message: String, subject: String, to: String, fromAddress: String, fromName: String? = null, cc: String? = null, bcc: String? = null)
}