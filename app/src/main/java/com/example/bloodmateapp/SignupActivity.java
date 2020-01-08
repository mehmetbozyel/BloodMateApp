package com.example.bloodmateapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import static android.widget.Toast.LENGTH_SHORT;

public class SignupActivity extends AppCompatActivity {

    private EditText nameText;
    private EditText addressText;
    private EditText emailText;
    private EditText mobilenumberText;
    private EditText passwordText;
    private EditText passwordconfirmText;
    private Button signupButton;
    private TextView loginLink;
    private FirebaseAuth mAuth;
    private Intent MainActivity;
    Spinner bloodTypeSpinner;
    String bloodItem;
    Spinner addressSpinner;
    String addressItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();

        nameText = findViewById(R.id.input_name);
        emailText = findViewById(R.id.input_email);
        mobilenumberText = findViewById(R.id.input_mobile);
        passwordText = findViewById(R.id.input_password);
        passwordconfirmText = findViewById(R.id.input_reEnterPassword);
        signupButton = findViewById(R.id.btn_signup);
        loginLink = findViewById(R.id.link_login);
        MainActivity = new Intent(this, MainActivity.class);
        bloodTypeSpinner = (Spinner) findViewById(R.id.bloodType);
        addressSpinner = (Spinner) findViewById(R.id.input_address);

        ArrayAdapter<String> bloodTypeAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.bloodTypeList));
        bloodTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bloodTypeSpinner.setAdapter(bloodTypeAdapter);
        ;

        ArrayAdapter<String> citiesAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.cityList));
        citiesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        addressSpinner.setAdapter(citiesAdapter);

        bloodTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Select Blood Type")){
                    Toast.makeText(parent.getContext(),"Please select blood type", LENGTH_SHORT).show();
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

        System.out.println(bloodItem);
        addressSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Select City")){
                    Toast.makeText(parent.getContext(),"Please select a city", LENGTH_SHORT).show();
                }
                else {
                    addressItem = parent.getItemAtPosition(position).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(parent.getContext(),"Any city is not selected", LENGTH_SHORT).show();
            }
        });

        System.out.println(addressItem);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singup();
            }
        });


        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
    }
    public void singup() {


        if (!validate()) {
            onSignupFailed();
            return;
        }

        signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        //String name = nameText.getText().toString();
       // String address = addressText.getText().toString();
        String email = emailText.getText().toString();
        //String mobile = mobilenumberText.getText().toString();
        String password = passwordText.getText().toString();
       // String reEnterPassword = passwordconfirmText.getText().toString();

        // TODO: Implement your own signup logic here.

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            FirebaseUser user = mAuth.getCurrentUser();

                            String email = user.getEmail();
                            String uid = user.getUid();
                            String name = nameText.getText().toString();
                            String mobilenumber = mobilenumberText.getText().toString();

                            System.out.println(bloodItem);


                            HashMap<Object, String> hashMap = new HashMap<>();
                            hashMap.put("email", email);
                            hashMap.put("uid", uid);
                            hashMap.put("name",name);
                            hashMap.put("address",addressItem);
                            hashMap.put("bloodtype",bloodItem);
                            hashMap.put("mobilenumber",mobilenumber);

                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference reference = database.getReference("Users");
                            reference.child(uid).setValue(hashMap);
                            // Sign in success, update UI with the signed-in user's information
                            onSignupSuccess();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(SignupActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            onSignupFailed();
                        }

                        // ...
                    }
                });

    }

    public void onSignupSuccess() {
        signupButton.setEnabled(true);
        //setResult(RESULT_OK, null);

        startActivity(MainActivity);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = nameText.getText().toString();
        String email = emailText.getText().toString();
        String mobile = mobilenumberText.getText().toString();
        String password = passwordText.getText().toString();
        String reEnterPassword = passwordconfirmText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            nameText.setError("at least 3 characters");
            valid = false;
        } else {
            nameText.setError(null);
        }


        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailText.setError("enter a valid email address");
            valid = false;
        } else {
            emailText.setError(null);
        }

        if (mobile.isEmpty() || mobile.length()!=10) {
            mobilenumberText.setError("Enter Valid Mobile Number");
            valid = false;
        } else {
            mobilenumberText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            passwordText.setError(null);
        }

        if (reEnterPassword.isEmpty() || reEnterPassword.length() < 4 || reEnterPassword.length() > 10 || !(reEnterPassword.equals(password))) {
            passwordconfirmText.setError("Password Do not match");
            valid = false;
        } else {
            passwordconfirmText.setError(null);
        }

        return valid;
    }
}
