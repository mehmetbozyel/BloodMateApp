package com.example.bloodmateapp;


import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class AnnouncementFragment extends Fragment {

    FirebaseAuth firebaseAuth;
    EditText nametxt;
    EditText detailstxt;
    Button announcebtn;
    String email;
    String uid;
    String bloodItem;
    String cityItem;
    String hospitalItem;
    String name;
    String details;
    Spinner bloodTypeSpinner;
    Spinner citiesSpinner;
    Spinner hospitalsSpinner;



    public AnnouncementFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_announcement, container, false);

        nametxt = view.findViewById(R.id.nameSurname);
        detailstxt = view.findViewById(R.id.details);
        announcebtn = view.findViewById(R.id.announce);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();


        bloodTypeSpinner = (Spinner) view.findViewById(R.id.bloodType);

        ArrayAdapter<String> bloodTypeAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.bloodTypeList));
        bloodTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bloodTypeSpinner.setAdapter(bloodTypeAdapter);


        citiesSpinner = (Spinner) view.findViewById(R.id.city);

        ArrayAdapter<String> citiesAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.cityList));
        citiesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citiesSpinner.setAdapter(citiesAdapter);


        hospitalsSpinner = (Spinner) view.findViewById(R.id.hospital);

        ArrayAdapter<String> hospitalsAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.hospitals));
        hospitalsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hospitalsSpinner.setAdapter(hospitalsAdapter);

        bloodTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Select Blood Type")){
                    Toast.makeText(parent.getContext(),"Please select blood type",Toast.LENGTH_SHORT).show();
                }
                else {
                    bloodItem = parent.getItemAtPosition(position).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(parent.getContext(),"Any blood type is not selected",Toast.LENGTH_SHORT).show();
            }
        });

        citiesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Select City")){
                    Toast.makeText(parent.getContext(),"Please select a city",Toast.LENGTH_SHORT).show();
                }
                else {
                    cityItem = parent.getItemAtPosition(position).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(parent.getContext(),"Any city is not selected",Toast.LENGTH_SHORT).show();
            }
        });

        hospitalsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Select Hospital Name")){
                    Toast.makeText(parent.getContext(),"Please select a hospital",Toast.LENGTH_SHORT).show();
                }
                else {
                    hospitalItem = parent.getItemAtPosition(position).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(parent.getContext(),"Any hospital is not selected",Toast.LENGTH_SHORT).show();
            }
        });


        announcebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                email = user.getEmail();
                uid = user.getUid();
                name = nametxt.getText().toString();
                details = detailstxt.getText().toString();



/*                HashMap<Object, String> hashMap = new HashMap<>();

                hashMap.put("email", email);
                hashMap.put("uid", uid);
                hashMap.put("name",name);
                hashMap.put("bloodtype",bloodItem);
                hashMap.put("city",cityItem);
                hashMap.put("hospital",hospitalItem);
                hashMap.put("details", details);

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference reference = database.getReference("posts");
                reference.child(uid).setValue(hashMap);
                Log.d("Bloddmate", "database");
*/

                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference ref = database.getReference("posts");
                DatabaseReference usersRef = ref.child("post");
                HashMap<String, Post> postHashMap = new HashMap<>();

                postHashMap.put(name + " " + "5 ocak", new Post(name,bloodItem,cityItem,hospitalItem,details,"5 ocak"));

                usersRef.setValue(postHashMap);

                
                HomeFragment homeFragment = new HomeFragment();
                FragmentTransaction ft1 = getActivity().getSupportFragmentManager().beginTransaction();
                ft1.replace(R.id.context, homeFragment, "");
                ft1.commit();

            }
        });
        // Inflate the layout for this fragment
        return view;
    }



}
