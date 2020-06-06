package com.demo.digivalet;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.amplifyframework.AmplifyException;

import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;

public class Login {

    public static void initializePlugins(Context context){
        try {
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            //Amplify.addPlugin(new AWSApiPlugin());
            Amplify.configure(context);
            Log.i("MyAmplifyApp", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
        }
    }

    public static void fetchSession(Callback callback){
        Amplify.Auth.fetchAuthSession(
                result -> {
                    callback.onSuccess(result.isSignedIn());
                },
                error -> {
                    callback.onError(error.getMessage().toString());
                }
        );
    }

    public static void signIn(String username, String password,Callback callback){
        Amplify.Auth.signIn(
                username,
                password,
                result -> {
                    callback.onSuccess(result.isSignInComplete());
                    Log.i("AuthQuickstart", result.isSignInComplete() ? "Sign in succeeded" : "Sign in not complete");
                } ,
                error -> {
                    callback.onError(error.toString());
                    Log.e("AuthQuickstart", error.toString());
                }
        );
    }

    public void showToast(String message, Context context){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }
}
