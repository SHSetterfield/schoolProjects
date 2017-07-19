
int adc=0;
int potPin=0;
int potVal=0;
float loadVoltage=0;
int dutyCycle=0;
float userInput=0;

void setup() {
  // put your setup code here, to run once:
Serial.begin(9600);
pinMode(3, OUTPUT);
//TCCROB = TCCROB & B11111OOO | BOOOOOOO
//while(! Serial);
}

void loop() {

  Serial.println("Enter desired load voltage between 0 and 7:  \n");
  while(Serial.available() == 0){}
  userInput=Serial.parseInt();
  while(1){
    adc=analogRead(5);                   //read the voltage across the load
    loadVoltage=map(adc,0,1023,7,0);    //map the values backwards because my ground reference is negative
    if(loadVoltage<userInput){
      dutyCycle++;   //this does create some oscillation, still need to implement PI system
      if(dutyCycle>255){
        dutyCycle=255;
      }
    }
    if(loadVoltage>userInput){
      dutyCycle--;
      if(dutyCycle<0){
        dutyCycle=0;
      }
    }
    analogWrite(3, dutyCycle);
    //userInput=Serial.parseInt();
    delay(20);
    int count;
    count++;
    if(count>100){
    Serial.println("User Input: ");
    Serial.println(userInput);
    Serial.println("Load Voltage: ");
    Serial.println(loadVoltage);
    Serial.println("User Input: ");
    Serial.println(userInput);
    Serial.println("Duty Cycle: ");
    Serial.println(dutyCycle);
    count=0;
    }
  }
  

}
/*testing with inductor in series with load, 15K resistor between gate and source
 * dutyCycle value of 36 corresponds to 7 volts
 * dutyCycle value of 8 corresponds to 1 volt
 * lower voltage range seems to work more smoothly with inductor in place
 */


/*Basic N channel 09N03LA testing
 * DC motor using DC power supply @ 9v, no inductors, caps or diodes:
 * dutyCycle value of 2 or 3 corresponds with approx 1.5 volt
*
 * dutyCycle value of 132 corresponds to 9 volts across load
 */
