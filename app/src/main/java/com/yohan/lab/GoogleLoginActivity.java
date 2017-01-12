package com.yohan.lab;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class GoogleLoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    private GoogleSignInOptions mGoogleSignInOptions;
    private GoogleApiClient mGoogleApiClient;
    private GoogleSignInResult mGoogleSignInResult;
    private GoogleSignInAccount mGoogleSignInAccount;
    private static final int RC_GOOGLE_SIGN_IN = 101;
    @OnClick(R.id.button_google_sign_in)
    public void signInToGoogle(){
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_GOOGLE_SIGN_IN);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_login);
        ButterKnife.bind(this);
        mGoogleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        mGoogleApiClient = new  GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, mGoogleSignInOptions)
                .build();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==RC_GOOGLE_SIGN_IN){
            mGoogleSignInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            if(mGoogleSignInResult.isSuccess()){
                mGoogleSignInAccount = mGoogleSignInResult.getSignInAccount();
                StringBuilder stringBuilder= new StringBuilder();
                stringBuilder.append("Email: ");
                stringBuilder.append(mGoogleSignInAccount.getEmail());
                stringBuilder.append("\n Name: ");
                stringBuilder.append(mGoogleSignInAccount.getDisplayName());
                stringBuilder.append("\n Id: ");
                stringBuilder.append(mGoogleSignInAccount.getId());

                showDialog(stringBuilder.toString());
            }

        }
    }

    public void addFragment(Fragment fragment, String title){

    };

    public void showDialog(String msg){

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(msg);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                dialogInterface.dismiss();
            }
        });

        final AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        showDialog("Connection Failed");
    }
}
