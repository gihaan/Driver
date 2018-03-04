package com.example.gihan.driver.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gihan.driver.R;
import com.example.gihan.driver.model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegesterFragment extends Fragment {

    EditText name, email, phone, password;
    Button createAccount;

    FirebaseAuth mAuth;
    FirebaseDatabase mDatabase;
    DatabaseReference mUser;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_regester, container, false);


        name = (EditText) v.findViewById(R.id.et_regester_name);
        email = (EditText) v.findViewById(R.id.et_regester_email);
        phone = (EditText) v.findViewById(R.id.et_regester_phone);
        password = (EditText) v.findViewById(R.id.et_regester_password);
        createAccount = (Button) v.findViewById(R.id.btn_create_account);

        //INITALIZE FIREBASE
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        mUser = mDatabase.getReference("drivers");

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValidation()) {
                    createAccount();
                    Toast.makeText(getActivity(), "تم التسجيل بنجاح  ", Toast.LENGTH_SHORT).show();

                } else
                    Toast.makeText(getActivity(), "من فضلك اكمل البيانات بصوره صحيحه ", Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }


    public boolean checkValidation() {
        if (TextUtils.isEmpty(name.getText().toString())) {
            return false;

        }

        if (TextUtils.isEmpty(email.getText().toString())) {
            return false;

        }
        if (TextUtils.isEmpty(phone.getText().toString()) && phone.getText().length() < 11) {
            return false;

        }
        if (TextUtils.isEmpty(password.getText().toString())) {
            return false;


        } else
            return true;

    }

    public void createAccount() {

        //REGESTER NEW USER
        mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {

                        //SAVE USER DATA IN DATABASE
                        User user = new User();
                        user.setEmail(email.getText().toString());
                        user.setName(name.getText().toString());
                        user.setPassword(password.getText().toString());
                        user.setPhone(phone.getText().toString());

                        mUser.child(mAuth.getCurrentUser().getUid()).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        });


                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }

}
