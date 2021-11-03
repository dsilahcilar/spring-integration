package com.ing.springintegration

import org.springframework.jms.annotation.JmsListener
import org.springframework.stereotype.Component

@Component
class Consumer {

    //TODO 2: remove this part and use DSL
    @JmsListener(destination = "repq")
    fun repListener(msg: String) {
        println("Reply Received : $msg")
    }
}