package jp.procon29.scoscope.scoscope_androidapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button directionBt = findViewById(R.id.directionBt);
        directionBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DirectionGuide.class);
                startActivity(intent);
            }
        });

        Button viewBt = findViewById(R.id.viewOnFacilityBt);
        viewBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ViewOnFacility.class);
                startActivity(intent);
            }
        });
    }
}
