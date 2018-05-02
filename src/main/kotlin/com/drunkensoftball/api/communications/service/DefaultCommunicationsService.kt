package com.drunkensoftball.api.communications.service

import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.AWSCredentialsProvider
import com.amazonaws.regions.Regions
import org.springframework.beans.factory.annotation.Value
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder
import com.amazonaws.services.simpleemail.model.*
import org.springframework.stereotype.Service

@Service
open class DefaultCommunicationsService: CommunicationsService {

    @Value("\${aws.access-key-id}")
    lateinit var accessKeyId: String

    @Value("\${aws.secret-access-key}")
    lateinit var secretAccessKey: String


    override fun sendEmail(message: String, subject: String, to: String, fromAddress: String, fromName: String?, cc: String?, bcc: String?) {
        val credentialsProvider = DSCredentialsProvider(accessKeyId, secretAccessKey)
        val source = if (fromName.isNullOrBlank()) fromAddress else "\"$fromName\" <$fromAddress>"
        val client = AmazonSimpleEmailServiceClientBuilder.standard().withCredentials(credentialsProvider)
                // Replace US_WEST_2 with the AWS Region you're using for
                // Amazon SES.
                .withRegion(Regions.US_WEST_2).build()
        val request = SendEmailRequest()
            .withDestination(
                Destination().withToAddresses(to))
            .withReplyToAddresses("no-reply@miles-smiles.us")
            .withMessage(Message()
                .withBody(Body()
                    .withText(Content()
                        .withCharset("UTF-8").withData(message)))
                .withSubject(Content()
                    .withCharset("UTF-8").withData(subject)))
            .withSource(source)
        client.sendEmail(request)
    }

    class DSCredentialsProvider(var accessKeyId: String, var secretAccessKey: String) : AWSCredentialsProvider {

        override fun getCredentials(): AWSCredentials {
            return DSAWSCredentials(accessKeyId, secretAccessKey)
        }

        override fun refresh() {
        }

        class DSAWSCredentials(var accessKeyId: String, var secretAccessKey: String) : AWSCredentials {
            override fun getAWSAccessKeyId(): String {
                return accessKeyId
            }

            override fun getAWSSecretKey(): String {
                return secretAccessKey
            }
        }
    }
}