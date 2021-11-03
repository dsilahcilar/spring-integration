package com.ing.springintegration

import org.apache.activemq.command.ActiveMQQueue
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.jms.annotation.EnableJms
import javax.jms.Queue


@Configuration
@EnableJms
@Profile("!multiple-instance")
class JmsConfiguration {

    @Bean
    fun reqq(): Queue {
        return ActiveMQQueue("reqq")
    }

    @Bean
    fun repq(): Queue {
        return ActiveMQQueue("repq")
    }
}