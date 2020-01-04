package com.bindstone.shop.service

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.annotation.PartitionOffset
import org.springframework.kafka.annotation.TopicPartition
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.handler.annotation.Header
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component


@Component
class Task(private val kafkaTemplate: KafkaTemplate<String, String>) {

    // Starting with offset from beginning of the Kafka Logs...

    @KafkaListener(topicPartitions = [TopicPartition(topic = "shop_command",
            partitionOffsets = [PartitionOffset(partition = "0", initialOffset = "0")])])
    fun listenToParition(
            @Payload data: String,
            @Header(KafkaHeaders.RECEIVED_PARTITION_ID) partition: Int) {
        println("billing Message - $data")
    }

}
