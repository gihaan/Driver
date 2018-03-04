package com.example.gihan.driver.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.gihan.driver.R;
import com.example.gihan.driver.fragment.FirstFragment;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        //FOR APPLY FONT
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder().setDefaultFontPath("fonts/Arkhip_font.ttf").setFontAttrId(R.attr.fontPath).build());
        setContentView(R.layout.activity_first);

        //TO PUT FRAGMENT ON ACTIVITY
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.firist_activity, new FirstFragment())
                    .commit();
        }
    }
}
