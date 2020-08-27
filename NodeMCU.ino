#include <PubSubClient.h>
#include <FirebaseArduino.h>
#include <ESP8266WiFi.h>
#include <ArduinoJson.h>
#include <dhtnew.h>

#define FIREBASE_HOST "firebase_link"
#define FIREBASE_AUTH "firebase_secret"

#define WIFI_SSID "wifi_name"
#define WIFI_PASSWORD "wifi_password"

int soilPin = 5, airPin = A0, dhtPin = 16;

DHTNEW dhtsensor(dhtPin);

void setup() {
  //Serial Begin at 9600 Baud 
  pinMode(soilPin, INPUT);
  pinMode(airPin, INPUT);
  
  Serial.begin(9600);
     WiFi.begin (WIFI_SSID, WIFI_PASSWORD);
        while (WiFi.status() != WL_CONNECTED) {
           delay(500);
            Serial.print("CONNECTION ERROR...");
  
         }
          Serial.println ("");
          Serial.println ("WiFi Connected!");
          Serial.println(WiFi.localIP());
     Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);


  
}

void loop() {

int soilData = digitalRead(soilPin);
Firebase.setInt("soilData", soilData);

int airData = analogRead(airPin);
Firebase.setInt("airData", airData);

dhtsensor.read();
float t = dhtsensor.temperature;
float h = dhtsensor.humidity;
Firebase.setFloat("t", t);
Firebase.setFloat("h", h);


delay(2000);




}
