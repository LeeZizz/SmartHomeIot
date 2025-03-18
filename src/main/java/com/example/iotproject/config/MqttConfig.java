package com.example.iotproject.config;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Configuration
public class MqttConfig {

    private static final String MQTT_BROKER_URL = "ssl://ef8f15f9626644d5955521a6ea0dccc2.s1.eu.hivemq.cloud:8883";
    private static final String MQTT_CLIENT_ID = "SpringBootClient";
    private static final String MQTT_USERNAME = "Phu";
    private static final String MQTT_PASSWORD = "88888888";
    private static final String MQTT_TOPIC = "sensor_data";

    // Kênh nhận dữ liệu từ MQTT
    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    // Cấu hình MQTT Client Factory
    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();

        // Cấu hình MqttConnectOptions
        MqttConnectOptions options = new MqttConnectOptions();
        options.setServerURIs(new String[]{MQTT_BROKER_URL});
        options.setUserName(MQTT_USERNAME);
        options.setPassword(MQTT_PASSWORD.toCharArray());

        // Thiết lập MqttConnectOptions cho factory
        factory.setConnectionOptions(options);

        return factory;
    }

    // Cấu hình Adapter để nhận tin nhắn từ MQTT
    @Bean
    public MessageProducer mqttInbound(MqttPahoClientFactory mqttClientFactory) {
        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter(MQTT_CLIENT_ID, mqttClientFactory, MQTT_TOPIC);
        adapter.setCompletionTimeout(5000);
        adapter.setRecoveryInterval(10000);
        adapter.setQos(1);
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }

    // Xử lý tin nhắn từ kênh mqttInputChannel
    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handler() {
        return message -> {
            System.out.println("Received MQTT message: " + message.getPayload());
        };
    }
}
