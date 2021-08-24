package com.example.ram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Timer;
import java.util.TimerTask;

public class check extends AppCompatActivity implements SensorEventListener {
        private static final String TAG = "Gyroscope";
        private SensorManager sensorManager;
        Sensor accelerometer,gyro;
        TextView xvalue ,yvalue,zvalue,xgvalue,ygvalue,zgvalue,counter,counter1;
        EditText user_name;
        int x,y,z,xg,yg,zg;
        int count=0;
        Button start,stop;
        FirebaseDatabase database;
        DatabaseReference reff1;
        User user;
        int child_node;
        CountDownTimer countDownTimer;
        boolean isTimmerrunning,check_user;

        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_check);
            Log.d(TAG, "onCreate:Initializing Sensor Services");
            database=FirebaseDatabase.getInstance();

            user=new User();
            counter=findViewById(R.id.counter);
            counter1=findViewById(R.id.counter1);
            xvalue =  findViewById(R.id.xvalue);
            yvalue =  findViewById(R.id.yvalue);
            zvalue =  findViewById(R.id.zvalue);
            xgvalue =  findViewById(R.id.xgvalue);
            ygvalue =  findViewById(R.id.ygvalue);
            zgvalue =  findViewById(R.id.zgvalue);
            start=findViewById(R.id.start);
            stop=findViewById(R.id.stop);
            user_name=findViewById(R.id.user_name);

            start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    final String s=user_name.getText().toString();
                    checkuser(s);
                    if(s.isEmpty()){
                        Toast.makeText(v.getContext(),"enter username",Toast.LENGTH_SHORT).show();
                    }
                    else if(check_user)
                    {Toast.makeText(v.getContext(),"user name present",Toast.LENGTH_SHORT).show();}

                    else
                    {
                        String user =user_name.getText().toString();
                        counter(user);
                    }
                }
            });
            stop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    countDownTimer.cancel();
                }
            });
            sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            if (accelerometer != null)
            {
                sensorManager.registerListener(check.this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
                Log.d(TAG, "onCreate : Registered accelerometer listner");

            }
            else
            {
                xvalue.setText("ACCELEROMETER NOT SUPPORTED");
                yvalue.setText("ACCELEROMETER NOT SUPPORTED");
                zvalue.setText("ACCELEROMETER NOT SUPPORTED");
            }
            gyro = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
            if (gyro != null)
            {
                sensorManager.registerListener(check.this, gyro, SensorManager.SENSOR_DELAY_NORMAL);
                Log.d(TAG, "onCreate : Registered gyro listner");

            }
            else
            {
                xgvalue.setText("GYRO NOT SUPPORTED");
                ygvalue.setText("GYRO NOT SUPPORTED");
                zgvalue.setText("GYRO NOT SUPPORTED");
            }

        }

    private void checkuser(final String s) {
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                check_user=dataSnapshot.hasChild(s);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }});
    }


    private void counter(final String users) {
            countDownTimer=new CountDownTimer(60000,1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                   count=(61000-(int) millisUntilFinished)/1000;
                   counter1.setText("count"+count);
                    reff1 = FirebaseDatabase.getInstance().getReference(users);
                    if (count % 2 == 0 & count <= 60) {
                        reff1.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                child_node = count/2;
                                getValue();
                                reff1.child(users + child_node).setValue(user);
                            }
                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                            }
                        });
                    }
                    if(count==60)
                    {Toast.makeText(check.this,"Data Update",Toast.LENGTH_SHORT).show();}
                }

                @Override
                public void onFinish() {

                }
            }.start();
            isTimmerrunning=true;
        }
        @Override
        public void onSensorChanged(SensorEvent sensorEvent)
        {
            Sensor sensor=sensorEvent.sensor;
            if(sensor.getType()==Sensor.TYPE_ACCELEROMETER)
            {
                Log.d(TAG, "onSensorChanged: X:" + sensorEvent.values[0] + "Y:" + sensorEvent.values[1] + "Z:" + sensorEvent.values[2]);

                x=(int)(sensorEvent.values[0]*100);
                y=(int)(sensorEvent.values[1]*100);
                z=(int)(sensorEvent.values[2]*100);
                xvalue.setText("xvalue" +x);
                yvalue.setText("yvalue" +y);
                zvalue.setText("zvalue" +z);
                x=x+4000;
                y=y+4000;
                z=z+4000;
            }

            if (sensor.getType() == Sensor.TYPE_GYROSCOPE)
            {
                xg = (int) (sensorEvent.values[0] * 100);
                yg = (int) (sensorEvent.values[1] * 100);
                zg = (int) (sensorEvent.values[2] * 100);
                xgvalue.setText("xvalue" + xg);
                ygvalue.setText("yvalue" + yg);
                zgvalue.setText("zvalue" + zg);
                xg = xg + 200;
                yg = yg + 230;
                zg = zg + 200;
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy)
        {

        }
        private void getValue()
        {
            user.setXvalue(x);
            user.setYvalue(y);
            user.setZvalue(z);
            user.setXGvalue(xg);
            user.setYGvalue(yg);
            user.setZGvalue(zg);

        }
    }