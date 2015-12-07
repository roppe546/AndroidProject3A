package com.example.robin.androidproject3a;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    SensorManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor accelerometer = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        if (accelerometer != null) {
            // Success! There's an accelerometer
            Log.i("SENSOR", "Sensors found.");

            manager.registerListener(sensorEventListener, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }
        else {
            Log.i("SENSOR", "No sensor found.");
        }
    }

    private final SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            double x = event.values[0];
            double y = event.values[1];
            double z = event.values[2];
            long time = event.timestamp;

            Log.i("SENSE", "x: " + x + ", y: " + y + ", z: " + z + ", at " + time);

            if(x < 1) {

            } else if(x < 2) {

            } else if (x < 3) {

            } else if(x < 4) {

            } else if(x < 5) {

            } else if(x < 6) {

            } else if (x < 7) {

            } else if (x < 8) {

            } else if (x < 9) {

            } else {

            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            return;
        }
    };
}
