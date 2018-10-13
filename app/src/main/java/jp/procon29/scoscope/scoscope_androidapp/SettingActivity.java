package jp.procon29.scoscope.scoscope_androidapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

public class SettingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }
}
