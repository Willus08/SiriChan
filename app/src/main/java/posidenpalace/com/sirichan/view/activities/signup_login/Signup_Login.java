package posidenpalace.com.sirichan.view.activities.signup_login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import javax.inject.Inject;

import posidenpalace.com.sirichan.R;
import posidenpalace.com.sirichan.view.injection.signup_login.DaggerSignup_LoginComponent;

public class Signup_Login extends AppCompatActivity implements Signup_LoginContract.View{

    @Inject Signup_LoginPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup__login);
        setupDagger();
        presenter.addView(this);
//
//        mCallbackManager = CallbackManager.Factory.create();
//        LoginButton loginButton = (LoginButton) findViewById(R.id.btnfacebook_login);
//        loginButton.setReadPermissions("email", "public_profile");
//        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                Log.d(TAG, "facebook:onSuccess:" + loginResult);
//                handleFacebookAccessToken(loginResult.getAccessToken());
//            }
//
//            @Override
//            public void onCancel() {
//                Log.d(TAG, "facebook:onCancel");
//                // ...
//            }
//
//            @Override
//            public void onError(FacebookException error) {
//                Log.d(TAG, "facebook:onError", error);
//                // ...
//            }
//        });
    }

    public void setupDagger(){
        DaggerSignup_LoginComponent.create().inject(this);
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void changeToSignUp() {

    }

    @Override
    public void changeToLogIn() {

    }

    public void LogIn(View view) {
    }

    public void StartSignUp(View view) {
    }

    public void CreateAccount(View view) {
    }

//    private void handleFacebookAccessToken(AccessToken token) {
//        Log.d(TAG, "handleFacebookAccessToken:" + token);
//        facebooktoken= token;
//        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
//        mAuth.signInWithCredential(credential)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's information
//                            Log.d(TAG, "signInWithCredential:success");
//                            stuff();
//                            Intent intent = new Intent(LogIn.this,LogIn.class);
//                            startActivity(intent);
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            updateUI(user);
//                        } else {
//                            // If sign in fails, display a message to the user.
//                            Log.w(TAG, "signInWithCredential:failure", task.getException());
//                            Toast.makeText(LogIn.this, "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();
//                            updateUI(null);
//                        }
//
//                        // ...
//                    }
//                });
//      }

}

