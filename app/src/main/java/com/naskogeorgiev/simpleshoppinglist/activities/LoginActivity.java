package com.naskogeorgiev.simpleshoppinglist.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.naskogeorgiev.simpleshoppinglist.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.tv_login_info)
    TextView tvLoginInfo;

    @BindView(R.id.btn_login)
    LoginButton btnLogin;

    @BindView(R.id.btn_open_lists)
    Button btnOpenLists;

    private CallbackManager callbackManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this);
        AppEventsLogger.activateApp(getApplication());
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);


        callbackManager = CallbackManager.Factory.create();

        btnLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                tvLoginInfo.setText(
                        "User ID: "
                                + loginResult.getAccessToken().getUserId()
                                + "\n" +
                                "Auth Token: "
                                + loginResult.getAccessToken().getToken()
                );
            }

            @Override
            public void onCancel() {
                tvLoginInfo.setText("Login attempt canceled.");
            }

            @Override
            public void onError(FacebookException error) {
                tvLoginInfo.setText("Login attempt failed.");

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @OnClick(R.id.btn_open_lists)
    void onOpenListsClicked() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }
}
