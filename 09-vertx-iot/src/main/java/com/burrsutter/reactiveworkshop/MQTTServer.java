package com.burrsutter.reactiveworkshop;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import io.vertx.mqtt.MqttServer;

import java.nio.charset.Charset;

/**
 * Created by burr on 1/27/17.
 */
public class MQTTServer extends AbstractVerticle {

    final String TISENSOR2650 = "ti_cc2650";
    final String TISENSOR2541 = "ti_cc2541";
    final String LBBSENSOR = "lbb_sense";

    public void start() throws Exception {
        MqttServer mqttServer = MqttServer.create(vertx);

        mqttServer.endpointHandler(endpoint -> {

            // shows main connect info
            System.out.println("MQTT client [" +
                    endpoint.clientIdentifier() +
                    "] request to connect, clean session = " +
                    endpoint.isCleanSession());

            if (endpoint.auth() != null) {
                System.out.println("[username = " +
                        endpoint.auth().userName() +
                        ", password = " +
                        endpoint.auth().password() + "]");
            }
            if (endpoint.will() != null) {
                System.out.println("[will topic = " + endpoint.will().willTopic() + " msg = " + endpoint.will().willMessage() +
                        " QoS = " + endpoint.will().willQos() + " isRetain = " + endpoint.will().isWillRetain() + "]");
            }

            // System.out.println("[keep alive timeout = " + endpoint.keepAliveTimeSeconds() + "]");

            // accept connection from the remote client
            endpoint.accept(false);

            endpoint.publishHandler(message -> {
                String msg = message.payload().toString(Charset.defaultCharset());
                System.out.println("Received message @" + msg + "@ with QoS [" + message.qosLevel() + "]");
                JsonObject msgAsJSON = new JsonObject(msg);
                if (msgAsJSON.getString("sensorid").equals(TISENSOR2650)) {
                    vertx.eventBus().publish(TISENSOR2650,msg);
                } else if (msgAsJSON.getString("sensorid").equals(TISENSOR2541)) {
                    vertx.eventBus().publish(TISENSOR2541,msg);
                } else if (msgAsJSON.getString("sensorid").equals(LBBSENSOR)) {
                    vertx.eventBus().publish(LBBSENSOR,msg);
                } else {
                    System.out.println("Not sure: " + msgAsJSON.getString("sensorid") );
                }


            });

        })
                .listen(ar -> {
                    if (ar.succeeded()) {
                        System.out.println("MQTT server is listening on port " + ar.result().actualPort());
                    } else {

                        System.out.println("Error on starting the server");
                        ar.cause().printStackTrace();
                    }
                });

    }
}
