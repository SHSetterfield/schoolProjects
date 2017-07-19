/*
 'Fade' based code to test a motor driver in a simple way.
  Increases duty cycle from zero to max, then reverses direction.  
 */

int pwmPin = 9;           // the PWM pin driving pwmPin is attached to
int dirPin = 8;           // the pin controlling motor driver direction

int dutyCycle = 0;    // duty cycle to be applied to pwmPin
int direction = LOW;    //direction to be applied to dirPin
int fadeAmount = 5;    // how many points to ++/-- the duty cycle by

// the setup routine runs once when you press reset:
void setup() {
  // declare pin 9 to be an output:
  pinMode(pwmPin, OUTPUT);
  pinMode(dirPin, OUTPUT);
}

// the loop routine runs over and over again forever:
void loop() {
  // set the dutyCycle of pin 9:
  analogWrite(pwmPin, dutyCycle);
  digitalWrite(dirPin, direction);

  // change the dutyCycle for next time through the loop:
  dutyCycle = dutyCycle + fadeAmount;
  if(dutyCycle == 0){
    direction = !direction;
  }

  // reverse the direction of the fading at the ends of the fade:
  if (dutyCycle == 0 || dutyCycle == 255) {
    fadeAmount = -fadeAmount ;
    
  }
  
  // wait for 30 milliseconds to see the dimming effect
  delay(100);
}
