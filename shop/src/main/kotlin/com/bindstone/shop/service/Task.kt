package com.bindstone.shop.service

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component


@Component
class Task(private val kafkaTemplate: KafkaTemplate<String, String>) {

    @Scheduled(fixedDelay = 5000)
    fun scheduledCreateMessage() {
        val time = System.currentTimeMillis() / 1000
        println("create Message - $time")
        kafkaTemplate.send("shop_command", time.toString())
    }

    @KafkaListener(topics = ["shop_command"], containerFactory = "shopKafkaListenerContainerFactory")
    fun greetingListener(data: String) {
        println("receive Message - $data")
    }
}
