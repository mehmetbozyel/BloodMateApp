package com.example.bloodmateapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    EditText name;
    EditText email;
    EditText city;
    EditText phoneNumber;
    EditText bloodGroup;
    Button logout;

    String nameValue,addressValue,emailValue,phoneValue,bloodValue;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_profile, container, false);

        name = view.findViewById(R.id.name);
        email = view.findViewById(R.id.email);
        city = view.findViewById(R.id.city);
        phoneNumber = view.findViewById(R.id.phone);
        bloodGroup = view.findViewById(R.id.bloodtypetxt);
        logout = view.findViewById(R.id.logout);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Users");


        Query query = databaseReference.orderByChild("email").equalTo(firebaseUser.getEmail());

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    nameValue = "" + ds.child("name").getValue();
                    addressValue = "" + ds.child("address").getValue();
                    emailValue = "" + ds.child("email").getValue();
                    bloodValue = "" + ds.child("bloodtype").getValue();
                    phoneValue = "" + ds.child("mobilenumber").getValue();

                    System.out.println(bloodValue);

                    name.setText(nameValue);
                    city.setText(addressValue);
                    email.setText(firebaseUser.getEmail());
                    phoneNumber.setText(phoneValue);
                    bloodGroup.setText(bloodValue);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                getActivity().startActivity(intent);



            }
        });

        return view;
    }




}
