package com.webim.testshaikhivaliev.ui;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.webim.testshaikhivaliev.R;
import com.webim.testshaikhivaliev.model.FriendResponse;
import com.webim.testshaikhivaliev.model.UserResponse;
import com.webim.testshaikhivaliev.net.ApiUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class FragmentFriendList extends Fragment implements
        SwipeRefreshLayout.OnRefreshListener {

    private TextView mUserName;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mRefresher;
    private View mErrorView;
    private boolean isShowError = false;
    private AdapterFriendsList mAdapter = new AdapterFriendsList();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
        getFriendList();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_friends_list, container, false);
        mUserName = v.findViewById(R.id.tv_user_name);
        mErrorView = v.findViewById(R.id.errorView);
        mRecyclerView = v.findViewById(R.id.rv_friends_list);
        mRefresher = v.findViewById(R.id.refresher);
        mRefresher.setOnRefreshListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
        showError(isShowError);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onRefresh() {
        mRefresher.post(new Runnable() {
            @Override
            public void run() {
                mRefresher.setRefreshing(true);
                FragmentFriendList.this.getFriendList();
            }
        });
    }

    private void showError(boolean error) {
        if (error) {
            mErrorView.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);

        } else {
            mErrorView.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
        }
    }


    @SuppressLint("CheckResult")
    private void getFriendList() {

        ApiUtils.getApiService().getFriendList("random", "5", "photo_50, city")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        mRefresher.setRefreshing(false);
                    }
                })
                .subscribe(new Consumer<FriendResponse>() {
                    @Override
                    public void accept(FriendResponse response) throws Exception {
                        showError(false);
                        isShowError = false;
                        mAdapter.addData(response.getResponse().getUsers());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        showError(true);
                        isShowError = true;
                    }
                });

        ApiUtils.getApiService().getUserInformation()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        mRefresher.setRefreshing(false);
                    }
                })
                .subscribe(new Consumer<UserResponse>() {
                    @Override
                    public void accept(UserResponse response) throws Exception {
                        mUserName.setText(response.getUsers().get(0).getFullName());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        showError(true);
                        isShowError = true;
                    }
                });

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item_logout) {

            SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getActivity());
            SharedPreferences.Editor editor = sh.edit();
            editor.remove("accessToken");
            editor.apply();

            getActivity().getIntent().setData(null);

            ((ActivityMain) getActivity()).changeFragment(new FragmentAuth());
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
            outState.putString("userName", mUserName.getText().toString());
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            mUserName.setText(savedInstanceState.getString("userName"));
        }
    }
}
