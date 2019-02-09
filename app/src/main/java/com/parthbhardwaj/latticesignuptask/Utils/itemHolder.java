package com.parthbhardwaj.latticesignuptask.Utils;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.parthbhardwaj.latticesignuptask.R;

public class itemHolder extends RecyclerView.ViewHolder {


    TextView user_name, user_email, user_address, user_phone;

    public itemHolder(@NonNull View itemView) {
        super(itemView);

        user_address = itemView.findViewById(R.id.user_address);
        user_phone = itemView.findViewById(R.id.user_phone);
        user_email = itemView.findViewById(R.id.user_email);
        user_name = itemView.findViewById(R.id.user_name);
    }
}
