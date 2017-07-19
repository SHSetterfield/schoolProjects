// Connect A0 to A1, then open the Serial Plotter.
#define DAC_PIN A0 // Make code a bit more legible
//Note:  Firgelli Dir is connected to pin 2 (digital pin)
//Note:  Firgeli PWM is connected to pin 5 (PWM pin)
float x = 0; // Value to take the sin of
float duty_cycle = 0.001;  // Value to increment x by each time
int frequency = 10; // Frequency of sine wave
int pin;     //pin to write pwm to
char Buffer [100] = {};
float  num7, num6, num19, num18;
void setup() 
{
  analogWriteResolution(10); // Set analog out resolution to max, 10-bits
  analogReadResolution(12); // Set analog input resolution to max, 12-bits
  pinMode(3, OUTPUT);
  pinMode(4, OUTPUT);
  pinMode(5, OUTPUT);
  pinMode(6, OUTPUT);
  pinMode(5, OUTPUT);
  pinMode(7, OUTPUT);
  pinMode(8, INPUT);
  pinMode(10, OUTPUT);
  pinMode(11, OUTPUT);
  pinMode(12, OUTPUT);
  pinMode(13, OUTPUT);
  pinMode(15, OUTPUT);
  pinMode(16, OUTPUT);
  pinMode(17, OUTPUT);
  SerialUSB.begin(9600);
  Serial.Println("Let's write some PWM and sample voltages bruh.\n\n"
  Serial.flush();
}
void loop() 
{  
    Serial.println (Buffer, "Pin 5 = Firgelli PWM \nPin 2 = Firgelli Direction\nPin 7 = Base Direction\nPin 8 = Rotunda\nPin 9 = Claw Servo\nPin 13 = Base Speed\n\nEnter a pin #: ";
    while (Serial.available() == 0);   // Wait here until buffer has input
     
     pin = Serial.parseInt();
     Serial.print("pin = "); Serial.println(pin, INT);
     
    Serial.println (Buffer, "Enter Duty Cycle, values 0-1024: ");
    while (Serial.available() == 0) ;   //wait for buffer input
    
     duty_cycle = Serial.parseFloat();
     Serial.print("Duty cycle = "); Serial.println(duty_cycle, FLOAT);
    
  analogWrite(pin, duty_cycle);   //Write PWM to pin 5, to control firgelli speed
  num17 = analogRead(17);
  sprintf (Buffer, "BaseEncoderValue: %d ", (int)num17);
  SerialUSB.println(Buffer);
  if (Serial.available()
  delay(1); // Delay 1ms
 }
