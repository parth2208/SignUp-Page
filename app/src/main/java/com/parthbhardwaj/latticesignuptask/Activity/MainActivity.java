package com.parthbhardwaj.latticesignuptask.Activity;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.hbb20.CountryCodePicker;
import com.parthbhardwaj.latticesignuptask.DataBase.UserDatabase;
import com.parthbhardwaj.latticesignuptask.DataBase.UserTable;
import com.parthbhardwaj.latticesignuptask.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    TextInputEditText name, address, phone, email, password;

    AppCompatImageButton submit_btn, submit_btn_null;

    CountryCodePicker codePicker;

    public static UserDatabase userDatabase;

    UserTable userTable = new UserTable();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Log.d(TAG, "onCreate: Database is initialized");
        userDatabase = Room.databaseBuilder(this,UserDatabase.class,"dbContact").fallbackToDestructiveMigration().allowMainThreadQueries().build();


        initWidget();
        userSignUp();

    }



   public void initWidget(){

        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        address = findViewById(R.id.address);
        password = findViewById(R.id.password);
        codePicker = findViewById(R.id.phone_code_picker);
        submit_btn = findViewById(R.id.submit_btn);
        submit_btn_null = findViewById(R.id.submit_btn_null);
        submit_btn_null.setVisibility(View.VISIBLE);
        submit_btn.setVisibility(View.GONE);



        name.addTextChangedListener(watcher);
        phone.addTextChangedListener(watcher);
        email.addTextChangedListener(watcher);
       address.addTextChangedListener(watcher);
       password.addTextChangedListener(watcher);


   }

   public void userSignUp(){

            submit_btn_null.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "Enter Valid Details", Toast.LENGTH_SHORT).show();
                }
            });

            submit_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    storeData();

//                    initFragment();

                    name.setText("");
                    address.setText("");
                    phone.setText("");
                    email.setText("");
                    password.setText("");

                    Intent intent = new Intent(MainActivity.this, RegisteredUser.class);

                    startActivity(intent);
                }
            });
   }


   private final TextWatcher watcher = new TextWatcher() {
       @Override
       public void beforeTextChanged(CharSequence s, int start, int count, int after) {

       }

       @Override
       public void onTextChanged(CharSequence s, int start, int before, int count) {

       }

       @Override
       public void afterTextChanged(Editable s) {

           String nameValidate = name.getText().toString().trim();
           String phoneValidate = phone.getText().toString().trim();
           String addressValidate = address.getText().toString().trim();

           String passwordValidate = password.getText().toString().trim();

           String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";

           String emailValidate = email.getText().toString().trim();

           String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

           if (nameValidate.length()<4){

               name.setError("Atleast 4 char is required");

           }else if (addressValidate.length()<10){

               name.setError(null);
               address.setError("Atleast !0 char is required");

           }else if (!emailValidate.matches(emailPattern)&& s.length()>0 ){

               address.setError(null);
               email.setError("Enter Valid Email");

           }else if (phoneValidate.length()<10){

               email.setError(null);
               phone.setError("Atleast 10 digit is required");

           }else if (!passwordValidate.matches(passwordPattern)&& s.length()>0){

               phone.setError(null);
               password.setError("Password must contain one upper char, one lower char and a number.");

           }else{

               password.setError(null);

               submit_btn.setVisibility(View.VISIBLE);
               submit_btn_null.setVisibility(View.GONE);

               userSignUp();

           }
       }

   };


    public void storeData(){

        String input_name = name.getText().toString();
        String input_address = address.getText().toString();
        String input_phone = phone.getText().toString();
        String input_password = password.getText().toString();
        String input_email = email.getText().toString();

        userTable.setName(input_name);
        userTable.setAddress(input_address);
        userTable.setEmail(input_email);
        userTable.setPhone(input_phone);
        userTable.setPassword(input_password);

        userDatabase.userDao().addUser(userTable);

    }

}