package com.example.gihan.driver.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.gihan.driver.R;
import com.example.gihan.driver.fragment.RegesterFragment;

public class RegesterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regester);

        //TO PUT FRAGMENT ON ACTIVITY
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.regester_activity, new RegesterFragment())
                    .commit();
        }


        Toolbar mToolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.regester_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(getString(R.string.tool_bar_regeser));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
}
