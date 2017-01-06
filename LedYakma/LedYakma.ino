
#include <SoftwareSerial.h>



SoftwareSerial bt(10, 11); // (tx,rx)
const int LED1 = 8; // led pin bolme1
const int LED2 = 9; // led pin bolme2
const int LED3 = 12; // led pin bolme3
const int LED4 = 13; // led pin bolme4

void listenBluetooth();
void listenSerial();


void setup() {
  bt.begin(9600);
  Serial.begin(9600);
  pinMode(LED1, OUTPUT);
  pinMode(LED2, OUTPUT);
  pinMode(LED3, OUTPUT);
  pinMode(LED4, OUTPUT);



}

String bluetoothData = "";
String serialData = "";

void loop() {
  
  listenBluetooth();
  listenSerial();
}


void listenBluetooth(){
  while (bt.available() > 0) 
  {
    char data = bt.read();
    if(data=='A') //BOLME1
      digitalWrite(LED1,1);
    if(data=='W')
      digitalWrite(LED1,0);

    if(data=='B') //BOLME2
      digitalWrite(LED2,1);
    if(data=='X')
      digitalWrite(LED2,0);

    if(data=='C')//BOLME3
      digitalWrite(LED3,1);
    if(data=='Y')
      digitalWrite(LED3,0);

    if(data=='D') //BOLME4
      digitalWrite(LED4,1);
    if(data=='Z')
      digitalWrite(LED4,0);
    delay(100);
    bluetoothData = "";
    }
  }


void listenSerial(){
  while (Serial.available() > 0) {
    char data = Serial.read();
    if(data=='A') //BOLME1
      digitalWrite(LED1,1);
    if(data=='W')
      digitalWrite(LED1,0);

    if(data=='B') //BOLME2
      digitalWrite(LED2,1);
    if(data=='X')
      digitalWrite(LED2,0);

    if(data=='C')//BOLME3
      digitalWrite(LED3,1);
    if(data=='Y')
      digitalWrite(LED3,0);

    if(data=='D') //BOLME4
      digitalWrite(LED4,1);
    if(data=='Z')
      digitalWrite(LED4,0);

    delay(100);
    serialData = "";
    }
  }
  
