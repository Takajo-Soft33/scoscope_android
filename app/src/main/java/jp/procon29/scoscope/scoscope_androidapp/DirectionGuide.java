package jp.procon29.scoscope.scoscope_androidapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class DirectionGuide extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.direction_guide);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ListView listView = (ListView) findViewById(R.id.searchList);

        ArrayList<String> list = new ArrayList<>();
        list.add("徳山工業高等専門学校");
        list.add("徳山工業高等専門学校 高城寮");
        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);

        listView.setAdapter(adapter);

        Button aroundBt = findViewById(R.id.aroundBt1);
        aroundBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DirectionGuide.this, AroundMapActivity.class);
                startActivity(intent);
            }
        });
    }
}
