package com.example.elmin.plastenik;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {


    DatabaseReference dref;
    TextView text_temperature, text_humidity, text_airquality, text_soilmoisture;
    String temp, hum, air, soilmoisture;
    int col1 = Color.parseColor("#b32d00"), col2 = Color.parseColor("#000000");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        text_temperature = (TextView) findViewById(R.id.textTemp);
        text_airquality = (TextView) findViewById(R.id.textAir);
        text_humidity = (TextView) findViewById(R.id.textHum);
        text_soilmoisture = (TextView) findViewById(R.id.textSoil);



        dref = FirebaseDatabase.getInstance().getReference();
        dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                temp = dataSnapshot.child("t").getValue().toString();
                text_temperature.setText(temp + "C");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        dref = FirebaseDatabase.getInstance().getReference();
        dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                hum = dataSnapshot.child("h").getValue().toString();
                text_humidity.setText(hum + "%");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        dref = FirebaseDatabase.getInstance().getReference();
        dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                air = dataSnapshot.child("airData").getValue().toString();
                text_airquality.setText(air);
              
                }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        dref = FirebaseDatabase.getInstance().getReference();
        dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                soilmoisture = dataSnapshot.child("soilData").getValue().toString();
                int soilData = Integer.parseInt(soilmoisture);


                if (soilData == 0) {

                    text_soilmoisture.setText("No watering required.");
                    text_soilmoisture.setTextColor(col2);

                }
             else {
                    text_soilmoisture.setText("Watering required!");
                    text_soilmoisture.setTextColor(col1);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
