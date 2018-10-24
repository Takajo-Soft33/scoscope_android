package jp.procon29.scoscope.scoscope_androidapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

public class AroundMapActivity extends AppCompatActivity {
    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.around_map);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);

        try {
            googleMap = mapFragment.getMap();

            if(savedInstanceState == null) {
                mapFragment.setRetainInstance(true);

                mapInit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mapInit() {
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.setMyLocationEnabled(true);

        CameraPosition cameraPos = new CameraPosition.Builder().target(new LatLng(35.681382, 139.766084)).zoom(15.5f).build();

        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPos));
    }
}
