package com.example.robin.androidproject3a;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private SensorManager manager;
    private Sensor accelerometerSensor;
    private Sensor magneticSensor;
    private float[] accelerometerData;
    private float[] magneticData;
    private float[] radValues = new float[3];
    private float[] degreeValues = new float[3];
    private float[] rotation = new float[9];
    private boolean highPassFilter = true;
    private boolean lowPassFilter = false;
    private SensorFilter[] highPassFilters = {new SensorFilter(0f, 0.10f), new SensorFilter(0f, 0.10f), new SensorFilter(0f, 0.10f)};
    private SensorFilter[] lowPassFilters = {new SensorFilter(0f, 0.90f), new SensorFilter(0f, 0.90f), new SensorFilter(0f, 0.90f)};

    private FlowerAnimation flowerAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get sensor manager and sensors needed to calculate orientation
        manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometerSensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magneticSensor = manager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        flowerAnimation = new FlowerAnimation(getApplicationContext(), null);
    }

    @Override
    protected void onResume() {
        super.onResume();

        manager.registerListener(sensorListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
        manager.registerListener(sensorListener, magneticSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        manager.unregisterListener(sensorListener);
    }

    private final SensorEventListener sensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            // Save sensor data to the corresponding arrays
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                accelerometerData = event.values;
            }
            else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
                magneticData = event.values;
            }

            getOrientation();
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    /**
     * Calculate the orientation of each axis (x, y, z) of the phone in
     * degrees.
     */
    private void getOrientation() {
        // If data from both accelerometer and magnetic field sensor exists,
        // use it to calculate the orientation
        if (accelerometerData != null && magneticData != null) {
            SensorManager.getRotationMatrix(rotation, null, accelerometerData, magneticData);
            SensorManager.getOrientation(rotation, radValues);

            // Filtering here if needed
            if (highPassFilter) {
                for (int i = 0; i < radValues.length; i++) {
                    radValues[i] = highPassFilters[i].filter(radValues[i]);
                }
            }
            else if (lowPassFilter) {
                for (int i = 0; i < radValues.length; i++) {
                    radValues[i] = lowPassFilters[i].filter(radValues[i]);
                }
            }


            for (int i = 0; i < radValues.length; i++) {
                degreeValues[i] = (float) Math.toDegrees(radValues[i]);
            }
            Log.i("SENSE", "x: " + degreeValues[0] + ", y: " + degreeValues[1] + ", z: " + degreeValues[2]);
            flowerAnimation.drawFlower(degreeValues[1]);
        }
    }
}
