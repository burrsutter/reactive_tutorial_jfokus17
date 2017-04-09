# Vertx as a MQTT Service
Clients are Node.js running on Intel Edison's connected via Bluetooth
to TI SensorTag and LightBlue Bean

# Edison1
ssh root@192.168.3.5
rfkill unblock bluetooth
hciconfig hci0 up
cd ~/git/ti_sensortag_mqtt/
note: [The Edison/Raspberry Pi codebase](https://github.com/burrsutter/ti_sensortag_mqtt)
node ir_temperature_cc2650.js NOBLE_HCI_DEVICE_ID=hci0

produces:
{"sensorid":"ti_cc2650", "temp":29.6, "time":1485389839744}

# Edison2
ssh root@192.168.3.6
rfkill unblock bluetooth
hciconfig hci0 up
cd git/lightbluebean_node
note: [LightBlue Bean code](https://github.com/burrsutter/lightbluebean_node/blob/master/poller_temp_mqtt.js)
node poller_temp_mqtt.js NOBLE_HCI_DEVICE_ID=hci0

produces:
{"sensorid":"lbb_sense", "temp":26.0, "time":1485389121978}

I often just use two TI SensorTags instead of the LightBlue Bean.

My [Youtube Channel](https://www.youtube.com/playlist?list=PLuWlr4oKSRUaPdRIoO9wGzpx4DqdpVYlV) includes several recordings of various IoT hardware+software demos. 

Note: My Intel Edison's and Raspberry Pis are pre-configured to look for
a specific SSID, a wifi router that travels with me (HooToo), they acquire an IP address via DNS and I use the router's console to see what IP addreses were assigned.
