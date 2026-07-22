package com.aayurtseven.emailorganizer.data.repository

import com.aayurtseven.emailorganizer.data.api.EtherealSmtpClient
import com.aayurtseven.emailorganizer.data.api.GeminiApiClient
import com.aayurtseven.emailorganizer.data.api.GmailImapClient
import com.aayurtseven.emailorganizer.data.database.AttachmentDao
import com.aayurtseven.emailorganizer.data.database.EmailDao
import com.aayurtseven.emailorganizer.data.database.LabelDao
import com.aayurtseven.emailorganizer.data.database.ScheduledEmailDao
import com.aayurtseven.emailorganizer.data.database.StarredEmailDao
import com.aayurtseven.emailorganizer.data.database.StatisticsDao
import com.aayurtseven.emailorganizer.data.database.VIPContactDao
import com.aayurtseven.emailorganizer.data.model.Attachment
import com.aayurtseven.emailorganizer.data.model.Email
import com.aayurtseven.emailorganizer.data.model.ScheduledEmail
import com.aayurtseven.emailorganizer.data.model.StarredEmail
import com.aayurtseven.emailorganizer.data.model.VIPContact
import kotlinx.coroutines.flow.Flow
import java.util.Date
import java.util.UUID

// Extend existing EmailRepository
class ExtendedEmailRepository(
    private val emailDao: EmailDao,
    private val gmailImapClient: GmailImapClient,
    private val etherealSmtpClient: EtherealSmtpClient,
    private val geminiClient: GeminiApiClient,
    private val statisticsDao: StatisticsDao,
    private val scheduledEmailDao: ScheduledEmailDao,
    private val labelDao: LabelDao,
    private val starredEmailDao: StarredEmailDao,
    private val attachmentDao: AttachmentDao,
    private val vipContactDao: VIPContactDao
) {
    // Base operations from original repository...
    
    // Statistics Operations
    suspend fun getDetailedStats() = statisticsDao.getEmailStats()
    
    suspend fun getTopSenders(limit: Int = 10) = statisticsDao.getTopSenders(limit)
    
    suspend fun getCategoryStats() = statisticsDao.getCategoryStats()
    
    // Scheduled Email Operations
    suspend fun scheduleEmail(scheduled: ScheduledEmail) {
        scheduledEmailDao.insertScheduledEmail(scheduled)
    }
    
    fun getAllScheduledEmails(): Flow<List<ScheduledEmail>> {
        return scheduledEmailDao.getAllScheduledEmails()
    }
    
    suspend fun cancelScheduledEmail(emailId: String) {
        scheduledEmailDao.getScheduledEmailById(emailId)?.let {
            scheduledEmailDao.deleteScheduledEmail(it)
        }
    }
    
    // Label Operations
    suspend fun addLabel(emailId: String, labelName: String, color: String) {
        labelDao.insertLabel(
            com.aayurtseven.emailorganizer.data.model.EmailLabel(
                id = UUID.randomUUID().toString(),
                name = labelName,
                color = color,
                emailId = emailId
            )
        )
    }
    
    suspend fun getEmailLabels(emailId: String) = labelDao.getLabelsByEmail(emailId)
    
    fun getAllLabels(): Flow<List<String>> = labelDao.getAllLabelNames()
    
    // Starred Operations
    suspend fun toggleStar(emailId: String) {
        val isStarred = starredEmailDao.isStarred(emailId)
        if (isStarred > 0) {
            starredEmailDao.unstarEmail(StarredEmail(emailId))
        } else {
            starredEmailDao.starEmail(StarredEmail(emailId))
        }
    }
    
    fun getStarredEmails(): Flow<List<String>> = starredEmailDao.getStarredEmailIds()
    
    // Attachment Operations
    suspend fun addAttachment(attachment: Attachment) {
        attachmentDao.insertAttachment(attachment)
    }
    
    suspend fun getEmailAttachments(emailId: String) = attachmentDao.getAttachmentsByEmail(emailId)
    
    suspend fun markAttachmentAsDownloaded(attachmentId: String, path: String) {
        attachmentDao.markAsDownloaded(attachmentId, path)
    }
    
    // VIP Contact Operations
    suspend fun addVIPContact(contact: VIPContact) {
        vipContactDao.insertVIPContact(contact)
    }
    
    suspend fun removeVIPContact(email: String) {
        vipContactDao.getVIPContact(email)?.let {
            vipContactDao.deleteVIPContact(it)
        }
    }
    
    fun getAllVIPContacts(): Flow<List<VIPContact>> = vipContactDao.getAllVIPContacts()
    
    suspend fun isVIP(email: String) = vipContactDao.isVIP(email) > 0
}
