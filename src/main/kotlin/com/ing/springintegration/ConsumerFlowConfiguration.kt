package com.ing.springintegration

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.dsl.IntegrationFlow
import org.springframework.integration.dsl.IntegrationFlows
import org.springframework.integration.dsl.MessageChannels
import org.springframework.integration.jms.dsl.Jms
import org.springframework.jms.connection.SingleConnectionFactory

@Configuration
class ConsumerFlowConfiguration {
    companion object{
        private var logger = LoggerFactory.getLogger(ConsumerFlowConfiguration::class.java)
    }

    //TODO: run it and test it for multiple thread
    // Execute multiple curl request from different tabs
    // curl localhost/req/req-5
    // curl localhost/req/req-1
    @Bean
    fun reqFlow(connectionFactory: SingleConnectionFactory): IntegrationFlow {
        return IntegrationFlows
            .from(reqChannel())
            .log<String> {
                logger.debug(it.payload)
            }.handle(
                Jms.outboundGateway(
                    connectionFactory
                )
                    .requestDestination("reqq")
                    .replyDestination("repq")
                    .correlationKey("JMSCorrelationID")
                    .receiveTimeout(25000)
            ).channel(repChannel())
            .get()
    }

    @Bean
    fun reqChannel() = MessageChannels.direct().get()

    @Bean
    fun repChannel() = MessageChannels.queue().get()
}