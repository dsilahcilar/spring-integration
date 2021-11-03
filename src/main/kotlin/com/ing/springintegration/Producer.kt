package com.ing.springintegration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.integration.channel.DirectChannel
import org.springframework.integration.support.MessageBuilder
import org.springframework.jms.core.JmsTemplate
import org.springframework.messaging.PollableChannel
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController


@RestController
class Producer {
    @Autowired
    private lateinit var jmsTemplate: JmsTemplate

    @Autowired
    private lateinit var reqChannel: DirectChannel

    @Autowired lateinit var repChannel: PollableChannel

    @GetMapping("req/{msg}")
    fun req(@PathVariable("msg") msg: String): String {
        //TODO 1: Wait for the response from reply queue
        reqChannel.send(MessageBuilder.withPayload(msg).build())
        var waitMs = msg.split("-")[1].toLong() * 1000
        Thread.sleep(waitMs)
        var reply = repChannel.receive()
        return "Your reply is <b>$reply</b> published successfully"
    }

}