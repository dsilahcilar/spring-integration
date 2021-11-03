package com.ing.springintegration

import org.springframework.jms.annotation.JmsListener
import org.springframework.stereotype.Component

@Component
class Consumer {

    //TODO: remove this part and
    // consume from reqq and produce to repq
    // DO it with spring integration DSL
//    @JmsListener(destination = "reqq")
//    fun reqListener(msg: String) {
//        println("Request Received : $msg")
//    }

    @JmsListener(destination = "repq")
    fun repListener(msg: String) {
        println("Reply Received : $msg")
    }
}