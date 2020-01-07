package com.example.bloodmateapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


    EditText name;
    EditText email;
    EditText city;
    EditText phoneNumber;
    EditText bloodType;
    Button logout;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_announcement, container, false);

        name = view.findViewById(R.id.name);
        email = view.findViewById(R.id.email);
        city = view.findViewById(R.id.city);
        phoneNumber = view.findViewById(R.id.phone);
        bloodType = view.findViewById(R.id.bloodType);
        logout = view.findViewById(R.id.logout);


        return view;
    }



}
