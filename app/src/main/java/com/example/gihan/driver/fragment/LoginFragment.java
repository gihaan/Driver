package com.example.gihan.driver.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.gihan.driver.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class LoginFragment extends Fragment {
    EditText phone;
    ImageButton btnGo;

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    String currentUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_login, container, false);


        phone = (EditText) v.findViewById(R.id.et_login_phone);
        btnGo = (ImageButton) v.findViewById(R.id.btn_login_go);

        mAuth = FirebaseAuth.getInstance();
        currentUser=mAuth.getCurrentUser().getUid();
        mDatabase= FirebaseDatabase.getInstance().getReference().child("drivers").child(currentUser);

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Login();
            }

        });


        return v;
    }


    public void Login() {
        if ( !TextUtils.isEmpty(phone.getText().toString())) {
            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    String phoneNumber=dataSnapshot.child("phone").getValue().toString();
                    String g=phone.getText().toString();
                    if (phoneNumber.matches(g)){
                        Toast.makeText(getActivity(),"تم تسجيل الدخول بنجاح ",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getActivity(),"من فضلك قم بكتابه الرقم بطريقه صحيحه ",Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(getActivity(),databaseError.getMessage(),Toast.LENGTH_SHORT).show();


                }
            });


        }


    }
}
