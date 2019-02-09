package com.parthbhardwaj.latticesignuptask.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.parthbhardwaj.latticesignuptask.DataBase.UserTable;
import com.parthbhardwaj.latticesignuptask.R;
import com.parthbhardwaj.latticesignuptask.Utils.RecyclerAdapter;

import java.util.List;

import static com.parthbhardwaj.latticesignuptask.Activity.MainActivity.userDatabase;

public class RegisteredUser extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);


        initRecycler();
    }

    public void initRecycler(){

        List<UserTable> userList = userDatabase.userDao().getAccount();

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        RecyclerAdapter adapter = new RecyclerAdapter(this,userList);
        recyclerView.setAdapter(adapter);

    }
}
