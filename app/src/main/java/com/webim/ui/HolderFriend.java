package com.webim.testshaikhivaliev.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.webim.testshaikhivaliev.R;
import com.webim.testshaikhivaliev.model.User;

public class HolderFriend extends RecyclerView.ViewHolder {

    private ImageView mImage;
    private TextView mName;
    private TextView mSurname;
    private TextView mCity;

    public HolderFriend(@NonNull View itemView) {
        super(itemView);
        mImage = itemView.findViewById(R.id.iv_photo);
        mName = itemView.findViewById(R.id.tv_friend_name);
        mSurname = itemView.findViewById(R.id.tv_friend_surname);
        mCity = itemView.findViewById(R.id.tv_friend_city);
    }


    public void bind(User user) {
        Picasso.get().load(user.getPhoto())
                .fit()
                .into(mImage);
        mName.setText(user.getName());
        mSurname.setText(user.getSurname());
        if (user.getCity()!=null){
            mCity.setText(user.getCity().getCityName());
        }
    }
}
