package com.webim.testshaikhivaliev.ui;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.webim.testshaikhivaliev.AppDelegate;
import com.webim.testshaikhivaliev.R;

public class ActivityMain extends AppCompatActivity {

    private String mAccessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(AppDelegate.getInstance());
        mAccessToken = preferences.getString("accessToken", null);

        if (savedInstanceState == null) {
            if (mAccessToken == null) {
                changeFragment(new FragmentAuth());
            } else changeFragment(new FragmentFriendList());
        }
    }

    public void changeFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        Uri uri = getIntent().getData();
        if (uri != null) {
            String mock_url = "http://callback" + "?" + uri.getFragment();
            Uri mock_uri = Uri.parse(mock_url);
            String access_token = mock_uri.getQueryParameter("access_token");
            getAccessToken(access_token, mock_url);
        }
    }

    private void getAccessToken(String access_token, String uri) {

        if (mAccessToken == null) {
            SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = sh.edit();
            editor.putString("accessToken", access_token);
            editor.apply();
            changeFragment(new FragmentFriendList());
        }
    }

}



