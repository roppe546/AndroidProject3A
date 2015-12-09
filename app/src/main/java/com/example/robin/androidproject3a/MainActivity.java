package com.example.robin.androidproject3a;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private SensorManager manager;
    private Sensor accelerometerSensor;
    private Sensor magneticSensor;
    private float[] accelerometerData;
    private float[] magneticData;
    private float[] radValues = new float[3];
    private float[] degreeValues = new float[3];
    private float[] rotation = new float[9];
    private float acceleration;
    private float currentAcceleration;
    private float previousAcceleration;
    private boolean highPassFilter = true;
    private boolean lowPassFilter = false;
    private SensorFilter[] highPassFilters = {new SensorFilter(0f, 0.10f), new SensorFilter(0f, 0.10f), new SensorFilter(0f, 0.10f)};
    private SensorFilter[] lowPassFilters = {new SensorFilter(0f, 0.90f), new SensorFilter(0f, 0.90f), new SensorFilter(0f, 0.90f)};
    private float maxAcc = 0;
    private float minAcc = 0;
    private int accCounter = 0;
    private Date time;

    private FlowerAnimation flowerAnimation;

    private ImageView petal1;
    private ImageView petal2;
    private ImageView petal3;
    private ImageView petal4;
    private Animation petalAnimation1;
    private Animation petalAnimation2;
    private Animation petalAnimation3;
    private Animation petalAnimation4;
    private Animation petalAnimation5;
    private ImageView petal5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get sensor manager and sensors needed to calculate orientation
        manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometerSensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magneticSensor = manager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        flowerAnimation = (FlowerAnimation) findViewById(R.id.flowerView);

        petal1 = (ImageView) findViewById(R.id.petal1);
        petal1.setVisibility(View.INVISIBLE);
        petal2 = (ImageView) findViewById(R.id.petal2);
        petal2.setVisibility(View.INVISIBLE);
        petal3 = (ImageView) findViewById(R.id.petal3);
        petal3.setVisibility(View.INVISIBLE);
        petal4 = (ImageView) findViewById(R.id.petal4);
        petal4.setVisibility(View.INVISIBLE);
        petal5 = (ImageView) findViewById(R.id.petal5);
        petal5.setVisibility(View.INVISIBLE);

        petalAnimation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.nopetals1);
        petalAnimation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.nopetals2);
        petalAnimation3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.nopetals3);
        petalAnimation4 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.nopetals4);
        petalAnimation5 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.nopetals5);
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
            } else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
                magneticData = event.values;
            }

            senseIfShake();
            getOrientation();
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    private boolean senseIfShake() {
        if (accelerometerData != null) {
            float x = accelerometerData[0];
            float y = accelerometerData[1];
            float z = accelerometerData[2];
            previousAcceleration = currentAcceleration;
            currentAcceleration = (float) Math.sqrt(x * x + y * y + z * z);
            float delta = currentAcceleration - previousAcceleration;
            // Multiply by 0.9 to use it as a high pass filter
            acceleration = acceleration * 0.90f + delta;

            if (time == null)
                time = new Date();

            System.out.println(acceleration);

            if (acceleration > maxAcc) {
                maxAcc = acceleration;
            }
            if (acceleration < minAcc) {
                minAcc = acceleration;
            }

            if (new Date().getTime() - time.getTime() > 200) {
                if (maxAcc > 1.5f || minAcc < -1.5f) {
                    accCounter++;
                    System.out.println("we HAVE been shaking for 0.2s");
                    if (accCounter >= 5) {
//                        flowerAnimation.playShake();
                        playShake();

//                        System.out.println("we HAVE been shaking for 1.0s");
                    }
                } else {
                    System.out.println("we have NOT shaken for 0.2s");
                    accCounter = 0;
                }
                time = new Date();
                maxAcc = 0;
                minAcc = 0;
            }
        }
        return false;
    }

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
            } else if (lowPassFilter) {
                for (int i = 0; i < radValues.length; i++) {
                    radValues[i] = lowPassFilters[i].filter(radValues[i]);
                }
            }

            for (int i = 0; i < radValues.length; i++) {
                degreeValues[i] = (float) Math.toDegrees(radValues[i]);
            }
//            Log.i("SENSE", "x: " + degreeValues[0] + ", y: " + degreeValues[1] + ", z: " + degreeValues[2]);
            flowerAnimation.drawFlower(degreeValues[1]);
        }
    }

    private void playShake() {
        petal5.startAnimation(petalAnimation5);
        petal1.startAnimation(petalAnimation1);
        petal2.startAnimation(petalAnimation2);
        petal3.startAnimation(petalAnimation3);
        petal4.startAnimation(petalAnimation4);
    }
}
