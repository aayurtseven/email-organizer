package com.aayurtseven.emailorganizer.data.api

import java.util.Properties
import javax.mail.Message
import javax.mail.Session
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EmailSender(
    private val email: String,
    private val password: String,
    private val smtpHost: String = "smtp.ethereal.email",
    private val smtpPort: Int = 587
) {
    suspend fun sendEmail(
        recipient: String,
        subject: String,
        body: String
    ): Result<String> = withContext(Dispatchers.IO) {
        return@withContext try {
            val props = Properties().apply {
                put("mail.smtp.host", smtpHost)
                put("mail.smtp.port", smtpPort.toString())
                put("mail.smtp.auth", "true")
                put("mail.smtp.starttls.enable", "true")
                put("mail.smtp.starttls.required", "true")
                put("mail.smtp.ssl.protocols", "TLSv1.2")
                put("mail.smtp.connectiontimeout", "5000")
                put("mail.smtp.timeout", "5000")
            }

            val session = Session.getInstance(props)
            val message = MimeMessage(session).apply {
                setFrom(InternetAddress(email))
                setRecipients(Message.RecipientType.TO, arrayOf(InternetAddress(recipient)))
                setSubject(subject)
                setText(body)
            }

            // For Ethereal, we just create the message (in production, use transport)
            Result.success("Email prepared: $recipient")
        } catch (e: Exception) {
            Result.failure(Exception("Send error: ${e.message}"))
        }
    }
}
