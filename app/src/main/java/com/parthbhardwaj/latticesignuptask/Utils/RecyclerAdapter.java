package com.parthbhardwaj.latticesignuptask.Utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parthbhardwaj.latticesignuptask.DataBase.UserTable;
import com.parthbhardwaj.latticesignuptask.R;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<itemHolder> {

    private static final String TAG = "RecyclerAdapter";

    Context context;
    List<UserTable> userTableList;



    public RecyclerAdapter(Context context, List<UserTable> userTableList ) {
        this.context = context;
        this.userTableList = userTableList;
    }

    @NonNull
    @Override
    public itemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item,viewGroup,false);

        return new itemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull itemHolder itemHolder, int i) {

        final UserTable userTable = userTableList.get(i);

        itemHolder.user_name.setText(userTable.getName());
        itemHolder.user_address.setText(userTable.getAddress());
        itemHolder.user_email.setText(userTable.getEmail());
        itemHolder.user_phone.setText(userTable.getPhone());


    }

    @Override
    public int getItemCount() {
        return userTableList.size();
    }
}
