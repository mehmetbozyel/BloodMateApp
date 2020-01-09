package com.example.bloodmateapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static androidx.core.content.ContextCompat.checkSelfPermission;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyHolder> {

    Context context;
    List<Post> postList;

    public PostAdapter(Context context, List<Post> postList) {
        this.context = context;
        this.postList = postList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.activity_post_row, viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {

        String name = postList.get(i).getName();
        String bloodtype = postList.get(i).getBloodtype();
        String city = postList.get(i).getCity();
        String hospital = postList.get(i).getHospital();
        String details = postList.get(i).getDetails();
        String phoneNum = postList.get(i).getPhonenumber();
        String time = postList.get(i).getTime();
        String username = postList.get(i).getUser();

        /*Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.setTimeInMillis(Long.parseLong(time));
        String date = DateFormat.format("dd/MM/yyyy hh:mm aa", calendar).toString();*/

        myHolder.bloodType.setText(bloodtype);
        myHolder.nameSurname.setText("Patient: " + name);
        myHolder.city.setText("City: " + city);
        myHolder.hospital.setText("Hospital: " + hospital);
        myHolder.details.setText("Details: " + "\n" + details);
        myHolder.time.setText(time);
        myHolder.phoneNumber.setText("Phone Number: " + phoneNum);
        myHolder.userName.setText("Shared by " + username);

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        TextView bloodType, nameSurname, city, hospital, phoneNumber, time, details, userName;


        public MyHolder(@NonNull View itemView) {
            super(itemView);

            bloodType = itemView.findViewById(R.id.bloodtypetxt);
            nameSurname = itemView.findViewById(R.id.namesurnametxt);
            city = itemView.findViewById(R.id.citytxt);
            hospital = itemView.findViewById(R.id.hospitaltxt);
            phoneNumber = itemView.findViewById(R.id.phonetxt);
            time = itemView.findViewById(R.id.timetxt);
            details = itemView.findViewById(R.id.txt);
            userName = itemView.findViewById(R.id.userShared);


            phoneNumber.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println(phoneNumber.getText().toString().substring(14));
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:" + phoneNumber.getText().toString().substring(14)));
                    context.startActivity(callIntent);

                }
            });

            hospital.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + city.getText().toString().substring(6) + hospital.getText().toString().substring(9));
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    context.startActivity(mapIntent);
                }
            });

            city.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + city.getText().toString().substring(6) + hospital.getText().toString().substring(9));
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    context.startActivity(mapIntent);
                }
            });

        }
    }


}
