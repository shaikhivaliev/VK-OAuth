package com.webim.testshaikhivaliev.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.webim.testshaikhivaliev.R;

import static com.webim.testshaikhivaliev.BuildConfig.CLIENT_ID;
import static com.webim.testshaikhivaliev.BuildConfig.REDIRECT_URL;


public class FragmentAuth extends Fragment {

    private Button mAuthVk;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_auth, container, false);

        mAuthVk = v.findViewById(R.id.btn_sign_up_with_vk);
        mAuthVk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://oauth.vk.com/authorize" + "?client_id=" + CLIENT_ID + "&display=mobile" + "&redirect_uri=" + REDIRECT_URL + "&scope=friends&response_type=token&v=5.92&revoke=1";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });
        return v;
    }

}
