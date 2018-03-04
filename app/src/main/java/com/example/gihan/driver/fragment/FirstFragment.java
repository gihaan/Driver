package com.example.gihan.driver.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.gihan.driver.R;
import com.example.gihan.driver.ui.LoginActivity;
import com.example.gihan.driver.ui.RegesterActivity;


public class FirstFragment extends Fragment {


    Button btnLogin;
    Button btnRegister;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_first, container, false);


        btnLogin=(Button)v.findViewById(R.id.btn_first_fragment_sign);
        btnRegister=(Button)v.findViewById(R.id.btn_first_fragment_regester);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(), LoginActivity.class);
                startActivity(i);
//                Toast.makeText(getActivity(),"Gggggggggg",Toast.LENGTH_SHORT).show();

            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(), RegesterActivity.class);
                startActivity(i);

            }
        });


        return v;
    }

}
