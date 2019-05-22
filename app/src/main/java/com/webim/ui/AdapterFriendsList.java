package com.webim.testshaikhivaliev.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.webim.testshaikhivaliev.R;
import com.webim.testshaikhivaliev.model.User;

import java.util.ArrayList;
import java.util.List;

public class AdapterFriendsList extends RecyclerView.Adapter<HolderFriend> {

    private List<User> mUsers = new ArrayList<>();


    @NonNull
    @Override
    public HolderFriend onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.holder_friend, viewGroup, false);
        return new HolderFriend(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderFriend viewHolder, int i) {
        User user = mUsers.get(i);
        viewHolder.bind(user);

    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public void addData(List<User> data) {
        if (data == null) return;
        mUsers.clear();
        mUsers.addAll(data);
        notifyDataSetChanged();
    }
}
