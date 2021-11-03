package com.ing.springintegration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.integration.core.MessagingTemplate
import org.springframework.integration.support.MessageBuilder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController


@RestController
class Producer {
    @Autowired lateinit var gatewayTemplate: MessagingTemplate

    @GetMapping("req/{msg}")
    fun req(@PathVariable("msg") msg: String): String {
        var reply = gatewayTemplate.sendAndReceive(MessageBuilder.withPayload(msg).build())
        return "Your reply is <b>$reply</b> published successfully"
    }

}