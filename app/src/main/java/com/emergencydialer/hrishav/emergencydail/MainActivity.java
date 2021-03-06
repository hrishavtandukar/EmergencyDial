package com.emergencydialer.hrishav.emergencydail;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.call_list);

        // since this apps depends on the call permission
        checkPermission();

        // Create a list of words
        final ArrayList<Dial> dial = new ArrayList<Dial>();
        dial.add(new Dial("Police Control Room", "100"));
        dial.add(new Dial("Traffic Police Control Room", "103"));
        dial.add(new Dial("Fire Fighters", "101"));
        dial.add(new Dial("Bir Hospital", "014221988"));
        dial.add(new Dial("Emergency Police Service", "014228435"));
        dial.add(new Dial("Crime Information", "014412748"));
        dial.add(new Dial("Child Missing", "104"));
        dial.add(new Dial("Nepal Electricity Authority", "014153164"));
        dial.add(new Dial("Blood Bank", "014225344"));
        dial.add(new Dial("Nepal Red Cross Society", "014270650"));

        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        DialAdapter adapter = new DialAdapter(this, dial);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml layout file.
        ListView listView = (ListView) findViewById(R.id.list);

        /*
        listView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + String.valueOf(dial)));

                startActivity(callIntent);

            }

        });
        */

        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.
        listView.setAdapter(adapter);
    }

    public void checkPermission() {
        // permission should be explicitly called if the sdk is greater than 23
        if(Build.VERSION.SDK_INT > 23) {
            if(ActivityCompat.checkSelfPermission(this,Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 123);
            }
        }
    }

    // this method is called after the application calls requestPermission()
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 123) {
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED) {
            } else {
                finish();       // finish the activity
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
