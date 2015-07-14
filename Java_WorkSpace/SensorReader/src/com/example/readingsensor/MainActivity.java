package com.example.readingsensor;

import java.util.Arrays;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
	
	static LineGraphView graph;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        
		graph = new LineGraphView(getApplicationContext(), 100, Arrays.asList("x","y","z"));
		graph.setVisibility(View.VISIBLE);
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    
    public static class PlaceholderFragment extends Fragment {
    
    	public PlaceholderFragment() {
    	}
    	
    	@Override
    	public View onCreateView(LayoutInflater inflater, ViewGroup container,
    	Bundle savedInstanceState) {
    		
    		/* method to replace label*/
    		View rootView = inflater.inflate(R.layout.fragment_main, container, false);
    		TextView tv = (TextView) rootView.findViewById(R.id.label1);
    		tv.setText("Sensor Reader\n===============");
    		
    		/* create TextView Object*/
    		TextView lightIntensity = new TextView(rootView.getContext());
    		TextView accelerometer = new TextView(rootView.getContext());
    		TextView magneticField = new TextView(rootView.getContext());
    		TextView rotationVector = new TextView(rootView.getContext());
    		
    		/*puts its contents in non-overlapping positions*/
    		LinearLayout layout = (LinearLayout) rootView.findViewById(R.id.layout1);
    		
    		/*stack all of the TextView objects vertically rather than horizontally*/
    		layout.setOrientation(LinearLayout.VERTICAL);
    		
    		/* add TextView Object to Layout*/
    		layout.addView(graph);
    		layout.addView(lightIntensity);
    		layout.addView(accelerometer);
    		layout.addView(magneticField);
    		layout.addView(rotationVector);
    		
    		/*In your Activity request the sensor manager:*/
    		SensorManager sensorManager = (SensorManager) rootView.getContext().getSystemService(SENSOR_SERVICE);
    		
    		/* In your Activity request the different sensors */
    		Sensor lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
    		Sensor accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    		Sensor fieldSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    		Sensor rotationSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
    		
    		/*In your Activity create an instance of your new SensorEventListener and register it.*/
    		SensorEventListener lightSensorListener = new LightSensorEventListener(lightIntensity);
    		SensorEventListener accelerometerSensorListener = new AccelerometerEventListener(accelerometer);
    		SensorEventListener fieldSensorListener = new FieldEventListener(magneticField);
    		SensorEventListener rotationSensorListener = new RotationEventListener(rotationVector);
    		sensorManager.registerListener(lightSensorListener, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
    		sensorManager.registerListener(accelerometerSensorListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
    		sensorManager.registerListener(fieldSensorListener, fieldSensor, SensorManager.SENSOR_DELAY_NORMAL);
    		sensorManager.registerListener(rotationSensorListener, rotationSensor, SensorManager.SENSOR_DELAY_NORMAL);
    		
    		return rootView;
    	}
    	
    	
    	
    	class LightSensorEventListener implements SensorEventListener {
        	TextView output;
        	double record;
        	
        	public LightSensorEventListener(TextView outputView){
        		output = outputView;
        	}
        	
        	public void onAccuracyChanged(Sensor s, int i){}
        	
        	public void onSensorChanged(SensorEvent se){
        		if (se.sensor.getType() == Sensor.TYPE_LIGHT) {
        			double sensorValue = (double) se.values[0];
        			
        			if(sensorValue > record)
        				record = sensorValue;
        			
        			String value = "===============";
        			value += "\n---Light Intensity---";
        			value += String.format("\nRecord Intensity: %.0f", record);
        			value += String.format("\nReal-Time Intensity: %.0f", sensorValue);
        			value += "\n===============";
        			
        			output.setText( value );
        		}
        	}
        }
    	
    	class AccelerometerEventListener implements SensorEventListener {
        	TextView output;
        	double[] record;
        	
        	public AccelerometerEventListener(TextView outputView){
        		output = outputView;
        		record = new double[3];
        	}
        	
        	public void onAccuracyChanged(Sensor s, int i){
        		
        	}
        	
        	public void onSensorChanged(SensorEvent se){
        		if (se.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
        			double x = (double) se.values[0];
        			double y = (double) se.values[1];
        			double z = (double) se.values[2];
        			
        			if(x > record[0])
        				record[0] = x;
        			
        			if(y > record[1])
        				record[1] = y;
        			
        			if(z > record[2])
        				record[2] = z;
        			
        			graph.addPoint(se.values);
        			
        			String value = "---Accelerometer---";
        			
        			value += "\nRecord Values";
        			value += String.format("\nX: %f", record[0]);
        			value += String.format("\nY: %f", record[1]);
        			value += String.format("\nZ: %f", record[2]);
        			
        			value += "\nReal-Time Values";
        			value += String.format("\nX: %f", x);
        			value += String.format("\nY: %f", y);
        			value += String.format("\nZ: %f", z);
        			value += "\n===============";
        			
        			output.setText( value );
        		}
        	}
        }
    	
    	class FieldEventListener implements SensorEventListener {
        	TextView output;
        	double[] record;
        	
        	public FieldEventListener(TextView outputView){
        		output = outputView;
        		record = new double[3];
        	}
        	
        	public void onAccuracyChanged(Sensor s, int i){}
        	
        	public void onSensorChanged(SensorEvent se){
        		if (se.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
        			double x = (double) se.values[0];
        			double y = (double) se.values[1];
        			double z = (double) se.values[2];
        			
        			if(x > record[0])
        				record[0] = x;
        			
        			if(y > record[1])
        				record[1] = y;
        			
        			if(z > record[2])
        				record[2] = z;
        			
        			String value = "---Magnetic Field---";
        			
        			value += "\nRecord Values";
        			value += String.format("\nX: %f", record[0]);
        			value += String.format("\nY: %f", record[1]);
        			value += String.format("\nZ: %f", record[2]);
        			
        			value += "\nReal-Time Values";
        			value += String.format("\nX: %f", x);
        			value += String.format("\nY: %f", y);
        			value += String.format("\nZ: %f", z);
        			value += "\n===============";
        			
        			output.setText( value );
        		}
        	}
        }
    	
    	class RotationEventListener implements SensorEventListener {
        	TextView output;
        	double[] record;
        	
        	public RotationEventListener(TextView outputView){
        		output = outputView;
        		record = new double[3];
        	}
        	
        	public void onAccuracyChanged(Sensor s, int i){
        		
        	}
        	
        	public void onSensorChanged(SensorEvent se){
        		if (se.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR) {
        			double x = (double) se.values[0];
        			double y = (double) se.values[1];
        			double z = (double) se.values[2];
        			
        			if(x > record[0])
        				record[0] = x;
        			
        			if(y > record[1])
        				record[1] = y;
        			
        			if(z > record[2])
        				record[2] = z;
        			
        			String value = "---Rotation Vector---";
        			
        			value += "\nRecord Values";
        			value += String.format("\nX: %f", record[0]);
        			value += String.format("\nY: %f", record[1]);
        			value += String.format("\nZ: %f", record[2]);
        			
        			value += "\nReal-Time Values";
        			value += String.format("\nX: %f", x);
        			value += String.format("\nY: %f", y);
        			value += String.format("\nZ: %f", z);
        			value += "\n===============";
        			
        			output.setText( value );
        		}
        	}
        }
	}
}