package com.ing.springintegration

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.integration.dsl.IntegrationFlow
import org.springframework.integration.dsl.IntegrationFlows
import org.springframework.integration.jms.dsl.Jms
import org.springframework.jms.connection.SingleConnectionFactory

@Configuration
@Profile("!multiple-instance")
class TiStubFlowConfiguration {
    companion object {
        private val logger = LoggerFactory.getLogger(TiStubFlowConfiguration::class.java)
    }

    @Bean
    fun tiStubFlow(connectionFactory: SingleConnectionFactory): IntegrationFlow {
        return IntegrationFlows.from(
            Jms.messageDrivenChannelAdapter(
                connectionFactory
            ).destination("reqq")
        ).log<String> {
            logger.debug(it.payload)
        }.transform<String, String> {
            it + " successfully consumed"
        }.handle(
            Jms.outboundAdapter(connectionFactory).destination("repq")
        ).get()
    }

}