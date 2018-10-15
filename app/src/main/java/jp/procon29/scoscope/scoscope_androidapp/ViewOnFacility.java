package jp.procon29.scoscope.scoscope_androidapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewOnFacility extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.view_on_facility);


        ListView listView = (ListView) findViewById(R.id.facilityList);

        String names[] = {"徳山工業高等専門学校","徳山大学"};
        String details[] = {"徳山工業高等専門学校は山口県周南市にある国立高等専門学校で、1974年に設置された。","徳山大学は山口県周南市学園台にある日本の私立大学で、1971年に設置された。"};

        List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
        for (int i = 0; i < names.length; i++) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("Name", names[i]);
            map.put("Detail", details[i]);
            dataList.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(this, dataList,
                android.R.layout.simple_list_item_2, new String[] { "Name","Detail" },
                new int[] { android.R.id.text1,android.R.id.text2 });

        listView.setAdapter(adapter);
    }
}
