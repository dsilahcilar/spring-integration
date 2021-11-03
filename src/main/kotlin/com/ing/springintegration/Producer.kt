package com.ing.springintegration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.integration.channel.DirectChannel
import org.springframework.integration.support.MessageBuilder
import org.springframework.jms.core.JmsTemplate
import org.springframework.messaging.PollableChannel
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import javax.jms.Queue


@RestController
class Producer {
    @Autowired
    private lateinit var repq: Queue

    @Autowired
    private lateinit var jmsTemplate: JmsTemplate

    @Autowired
    private lateinit var reqChannel: DirectChannel

    @Autowired lateinit var repChannel: PollableChannel

    @GetMapping("req/{msg}")
    fun req(@PathVariable("msg") msg: String): String {
        //TODO 1: Wait for the response from reply queue
        reqChannel.send(MessageBuilder.withPayload(msg).build())
        var reply = repChannel.receive()
        return "Your reply is <b>$reply</b> published successfully"
    }

    @GetMapping("rep/{msg}")
    fun rep(@PathVariable("msg") msg: String): String {
        jmsTemplate.convertAndSend(repq, msg)
        return "Your message <b>$msg</b> published successfully"
    }
}