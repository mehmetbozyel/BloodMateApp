package com.example.bloodmateapp;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import static android.widget.Toast.LENGTH_SHORT;


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
    String time;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;
    String userValue,phoneValue;



    public AnnouncementFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_announcement, container, false);

        nametxt = view.findViewById(R.id.nameSurname);
        detailstxt = view.findViewById(R.id.txt);
        announcebtn = view.findViewById(R.id.announce);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Users");


        Query query = databaseReference.orderByChild("email").equalTo(firebaseUser.getEmail());

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    phoneValue = "" + ds.child("mobilenumber").getValue();
                    userValue = "" + ds.child("name").getValue();



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



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
                    //Toast.makeText(parent.getContext(),"Please select blood type", LENGTH_SHORT).show();
                }
                else {
                    bloodItem = parent.getItemAtPosition(position).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(parent.getContext(),"Any blood type is not selected", LENGTH_SHORT).show();
            }
        });

        citiesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Select City")){
                    //Toast.makeText(parent.getContext(),"Please select a city", LENGTH_SHORT).show();
                }
                else {
                    cityItem = parent.getItemAtPosition(position).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(parent.getContext(),"Any city is not selected", LENGTH_SHORT).show();
            }
        });

        hospitalsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Select Hospital Name")){
                    //Toast.makeText(parent.getContext(),"Please select a hospital", LENGTH_SHORT).show();
                }
                else {
                    hospitalItem = parent.getItemAtPosition(position).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(parent.getContext(),"Any hospital is not selected", LENGTH_SHORT).show();
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
                time = String.valueOf(System.currentTimeMillis());
                Calendar calendar = Calendar.getInstance(Locale.getDefault());
                calendar.setTimeInMillis(Long.parseLong(time));
                String date = DateFormat.format("dd/MM/yyyy hh:mm aa", calendar).toString();




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

                HashMap<Object, String> postHashMap = new HashMap<>();
                //postHashMap.put(name, new Post(name,bloodItem,cityItem,hospitalItem,details,"5 ocak"));
                /*postHashMap.put("NameSurname",name);
                postHashMap.put("BloodType", bloodItem);
                postHashMap.put("City", cityItem);
                postHashMap.put("Hospital", hospitalItem);
                postHashMap.put("Details", details);
                postHashMap.put("Date", date);*/

                DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("Posts");
                dbref.child(time).setValue(new Post(name,bloodItem,cityItem,hospitalItem,details,date,phoneValue,userValue))
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                //Toast toast = Toast.makeText(AnnouncementFragment.this,"Post Published", LENGTH_SHORT);
                                nametxt.setText("");
                                detailstxt.setText("");
                            }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                //Toast.makeText(AnnouncementFragment.this,"Post Published", LENGTH_SHORT).show();
                            }
                        });
                
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
