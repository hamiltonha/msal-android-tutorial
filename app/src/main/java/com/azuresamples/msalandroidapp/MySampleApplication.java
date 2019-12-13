package com.azuresamples.msalandroidapp;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.microsoft.identity.client.IAccount;
import com.microsoft.identity.client.IPublicClientApplication;
import com.microsoft.identity.client.ISingleAccountPublicClientApplication;
import com.microsoft.identity.client.PublicClientApplication;
import com.microsoft.identity.client.SingleAccountPublicClientApplication;
import com.microsoft.identity.client.exception.MsalException;


public class MySampleApplication extends Application {
    private static Context context;
    private static ISingleAccountPublicClientApplication mSingleAccountApp;

    private static final String TAG = MySampleApplication.class.getSimpleName();

    TextView logTextView;

    public void onCreate() {
        super.onCreate();
        MySampleApplication.context = getApplicationContext();
        PublicClientApplication.createSingleAccountPublicClientApplication(getApplicationContext(), R.raw.auth_config_single_account, new IPublicClientApplication.ISingleAccountApplicationCreatedListener() {
            @Override
            public void onCreated(ISingleAccountPublicClientApplication application) {
                mSingleAccountApp = application;
                Log.d(TAG, "CREATED!");
            }

            @Override
            public void onError(MsalException exception) {
                Log.d(TAG, "HERE");
                displayError( exception);
            }

        });
    }

    public static Context getAppContext() {
        return MySampleApplication.context;
    }

    public static ISingleAccountPublicClientApplication getSingleAccountApp(){
        if (mSingleAccountApp == null){
            Log.d(TAG, "Failed to get Single Public Client App");
            return null;
        }

        return mSingleAccountApp;
    }

    private void displayError(@NonNull final Exception exception) {
        logTextView.setText(exception.toString());
    }



}
