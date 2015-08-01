public class ThermostatController implements SensorEventListener{
    Device currentMode;
    double refTemp;
    double ambTemp;
    bool deviceOn;  // used only for 5 state machines
    
    // constructor
    public ThermostatController() {
        // when we power on the app, 
        // set the current Mode to whatever is on the interface 
        // (UI would by default have it "off")
        currentMode = Thermostat.getMode();
        deviceOn = false;   // this is an assumption
    }
    
    // 5 state machine version 1
    // new ambient temperature reading
	public void onSensorChanged(SensorEvent se) {
		if (se.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {
		// se.values[0] provides the ambient temperature in degrees Celsius
        
        ambTemp = (double) se.values[0]; // store our ambient temperature reading
        Device userModeChoice = Thermostat.getMode();
        
        // state machine, switch case on the current mode
        // if statements on transitions
        switch(currentMode)
        case null:  // system off
            if(userModeChoice == Device.AirCond){
                currentMode = Device.AirCond;   // switch current mode / update state
                deviceOn = false;
                // to be safe, switch it off
                Thermostat.turnOff(Device.AirCond);
            } else {  /*if (userModeChoice == Device.Heater) */
                currentMode = Device.Heater;
                deviceOn = false;
                // to be safe, switch it off
                Thermostat.turnOff(Device.Heater);
            } 
            // else if (userModeChoice == null) {
            //    currentMode = null; // self transition
            //}
            break;
        case Device.AirCond:
            if(deviceOn) {  // Cool On (sub)state
                if(userModeChoice == null){
                    currentMode = null;
                    Thermostat.turnOff(Device.AirCond);
                    deviceOn = false;
                } else if(ambTemp <= refTemp) {
                    currentMode = Device.AirCond;
                    Thermostat.turnOff(Device.AirCond);
                    deviceOn = false;
                }
                // optional code, self transition
                /*
                else if(ambTemp > refTemp) {
                    currentMode = Device.AirCond;
                    deviceOn = true;
                }
                */
            } else {    // Cool Off
                if(userModeChoice == null){   // switch to System Off
                    currentMode = null;
                    // to be safe
                    Thermostat.turnOff(Device.AirCond);
                    deviceOn = false;
                } else if(ambTemp > refTemp + 2.0) {    // turn on AC
                    currentMode = Device.AirCond;
                    Thermostat.turnOn(Device.AirCond);
                    deviceOn = true;
                }
                // optional code, self transition
                /*
                else if(ambTemp <= refTemp + 2.0) {
                    currentMode = Device.AirCond;
                    deviceOn = false;
                }
                */
            }
            break;
        case Device.Heater:
            if(deviceOn) {  // Heat On (sub)state
                if(userModeChoice == null) {  // switch system off
                    currentMode = null;
                    deviceOn = false;
                    Thermostat.turnOff(Device.Heater);
                }
                else if(ambTemp >= refTemp) {   // turn off heater
                    currentMode = Device.Heater;
                    deviceOn = false;
                    Thermostat.turnOff(Device.Heater);
                }
            } else {    // Heat Off
                if(userModeChoice == null) {
                    currentMode = null;
                    deviceOn = false;
                    Thermostat.turnOff(Device.Heater);
                } else if(ambTemp < refTemp - 2.0) {
                    currentMode = Device.Heater;
                    deviceOn = true;
                    Thermostat.turnOn(Device.Heater);
                }
            }
            break;
        default:    // invalid state
            // throw exception
            break;
		}
	}
    
    // 5 state machine version 2
    // new ambient temperature reading
	public void onSensorChanged(SensorEvent se) {
        if (se.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {
            ambTemp = (double) se.values[0]; // store our ambient temperature reading
            Device userModeChoice = Thermostat.getMode();
            int currentState = -1;
            
            // this version pre-processes the states so that the switch case has 5 cases
            if(currentMode == null) {
                currentState = 0;   // system off
            } else if (currentMode == Device.AirCond && !deviceOn) {
                currentState = 1;   // cool off
            } else if (currentMode == Device.AirCond && deviceOn) {
                currentState = 2;   // cool on
            } else if (currentMode == Device.Heater && !deviceOn) {
                currentState = 3;   // heat off
            } else if (currentMode == Device.Heater && deviceOn) {
                currentState = 4;   // heat on
            }
            
            // switch current state, if blocks for transitions
            switch(currentState) {
            case 0: // system off
                if(userModeChoice == Device.AirCond){
                    currentMode = Device.AirCond;   // switch current mode / update state
                    deviceOn = false;
                    // to be safe, switch it off
                    Thermostat.turnOff(Device.AirCond);
                } else if (userModeChoice == Device.Heater){
                    currentMode = Device.Heater;
                    deviceOn = false;
                    Thermostat.turnOff(Device.Heater);
                }
                break;
            case 1: // cool off
                if(userModeChoice == null){   // switch to System Off
                    currentMode = null;
                    // to be safe
                    Thermostat.turnOff(Device.AirCond);
                    deviceOn = false;
                } else if(ambTemp > refTemp + 2.0) {    // turn on AC
                    currentMode = Device.AirCond;
                    Thermostat.turnOn(Device.AirCond);
                    deviceOn = true;
                }
                break;
            case 2: // cool on
                if(userModeChoice == null){
                    currentMode = null;
                    Thermostat.turnOff(Device.AirCond);
                    deviceOn = false;
                } else if(ambTemp <= refTemp) {
                    Thermostat.turnOff(Device.AirCond);
                    currentMode = Device.AirCond;
                    deviceOn = false;
                }
                break;
            case 3: // heat off
                if(userModeChoice == null) {
                    currentMode = null;
                    Thermostat.turnOff(Device.Heater);
                    deviceOn = false;
                } else if(ambTemp < refTemp - 2.0) {
                    currentMode = Device.Heater;
                    Thermostat.turnOn(Device.Heater);
                    deviceOn = true;
                }
                break;
            case 4: // heat on
                if(userModeChoice == null) {  // switch system off
                    currentMode = null;
                    Thermostat.turnOff(Device.Heater);
                    deviceOn = false;
                }
                else if(ambTemp >= refTemp) {   // turn off heater
                    currentMode = Device.Heater;
                    Thermostat.turnOff(Device.Heater);
                    deviceOn = false;
                }
                break;
            default:
            }
        }
    }
        
    // 3 state machine
    public void onSensorChanged(SensorEvent se) {
		if (se.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {
            ambTemp = (double) se.values[0]; // store our ambient temperature reading
            Device userModeChoice = Thermostat.getMode();
            
            // state machine, switch case on the current mode
            // if statements on transitions
            switch(currentMode)
            case null:  // system off
                if(userModeChoice == Device.AirCond) {
                    currentMode = Device.AirCond;
                } else if(userModeChoice == Device.Heater) {
                    currentMode = Device.Heater;
                } else {
                    // do nothing
                }
                break;
            case Device.AirCond:    // Cooling
                if(userModeChoice == null) {  // Switch system to off
                    currentMode = null;
                    Thermostat.turnOff(Device.AirCond);
                } else if(ambTemp < refTemp) {  // Switch off AC
                    Thermostat.turnOff(Device.AirCond);
                } else if(ambTemp >= refTemp) { // Switch on AC
                    Thermostat.turnOn(Device.AirCond);                   
                }
                break;
            case Device.Heater:     // Heating
                if(userModeChoice == null) {  // switch to system off
                    currentMode = null;
                    Thermostat.turnOff(Device.Heater);
                } else if(ambTemp > refTemp) {  // Switch off Heater
                    Thermostat.turnOff(Device.Heater);
                } else if(ambTemp <= refTemp) { // Switch on Heater
                    Thermostat.turnOn(Device.Heater);
                }
                break;
            default: // invalid state
                // throw exception
                break;
        }
    }
    
}

/*
double Thermostat.getRefTemperature();
can be used to retrieve the user set reference temperature

Device Thermostat.getMode();
can be used to retrieve the user set mode of operation (cooling, heating), it returns null if the user sets the mode to off.

void Thermostat.turnOn(Device); and 
void Thermostat.turnOff(Device); 
where Device could be Device.AirCond or Device.Heater, will turn on and off the respective devices.
*/