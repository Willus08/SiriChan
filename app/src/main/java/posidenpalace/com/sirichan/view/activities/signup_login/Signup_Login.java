package posidenpalace.com.sirichan.view.activities.signup_login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import posidenpalace.com.sirichan.R;
import posidenpalace.com.sirichan.view.activities.main_menu.MainMenu;
import posidenpalace.com.sirichan.view.injection.signup_login.DaggerSignup_LoginComponent;

public class Signup_Login extends AppCompatActivity implements Signup_LoginContract.View{

    private static final String TAG = "signup";
    @Inject Signup_LoginPresenter presenter;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private CallbackManager mCallbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup__login);
        mAuth = FirebaseAuth.getInstance();
        ButterKnife.bind(this);
        setupFirebaseLogin();
        setupFacebookLogin();
        setupDagger();
        presenter.addView(this);

    }

    @BindView(R.id.etSLSignUpEmail)
    EditText signupEmail;

    @BindView(R.id.etSLSignUpPassword)
    EditText signupPassword;

    @BindView(R.id.etSLLoginEmail)
    EditText loginEmail;

    @BindView(R.id.etSLLoginPassword)
    EditText loginPassword;

    @BindView(R.id.llSLLogInLayout)
    LinearLayout loginLayout;

    @BindView(R.id.llSLSignUpLayout)
    LinearLayout signupLayout;

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result back to the Facebook SDK
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void setupFirebaseLogin() {
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
    }

    private void setupFacebookLogin() {
        mCallbackManager = CallbackManager.Factory.create();
        LoginButton loginButton = (LoginButton) findViewById(R.id.flbtnSLfacebookLogin);
        loginButton.setReadPermissions("email", "public_profile");
        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");
                // ...
            }

            @Override
            public void onError(FacebookException error) {

            }


        });
    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(Signup_Login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

    public void setupDagger(){
        DaggerSignup_LoginComponent.create().inject(this);
    }

    private void updateUI(FirebaseUser currentUser) {

        if (currentUser != null){
            Intent intent = new Intent(Signup_Login.this,MainMenu.class);
            startActivity(intent);
        }
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void changeToSignUp() {
        loginLayout.setVisibility(View.GONE);
        signupLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void changeToLogIn() {
        signupLayout.setVisibility(View.GONE);
        loginLayout.setVisibility(View.VISIBLE);
    }

    public void LogIn(View view) {
        String email = "";
        String password = "";
        if (loginEmail.getText() != null) {// used to stop a potintial null tostring exception
            email = loginEmail.getText().toString();
        }
        if (loginPassword.getText() != null){// used to stop a potintial null tostring exception
            password = loginPassword.getText().toString();
        }
        if (!email.equals("") ) { // used to makee sure an email is entered

            if (!password.equals("")){ // used to make sure a password is entered

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());
                                if (task.isSuccessful()){
                                    Intent intent = new Intent(Signup_Login.this, MainMenu.class);
                                    startActivity(intent);
                              }
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Log.w(TAG, "signInWithEmail:failed", task.getException());
                                    Toast.makeText(Signup_Login.this, "failed", Toast.LENGTH_SHORT).show();
                                }

                                // ...
                            }
                        });
                 }else{
                Toast.makeText(this, "You must enter a password", Toast.LENGTH_SHORT).show();
                }
            }else{
            Toast.makeText(this, "You Must enter an email address", Toast.LENGTH_SHORT).show();
        }
    }

    public void StartSignUp(View view) {
        changeToSignUp();
    }

    public void CreateAccount(View view) {
        String email = "";
        String password = "";
        if (signupEmail.getText() != null) {// used to stop a potintial null tostring exception
            email = signupEmail.getText().toString();
        }
        if (signupPassword.getText() != null){// used to stop a potintial null tostring exception
            password = signupPassword.getText().toString();
        }


        if (!email.equals("")) { // used to makee sure an email is entered

            if (!password.equals("")){ // used to make sure a password is entered
                if (password.length() > 6) { // lets the user know they need a longer password
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
                                    if (task.isSuccessful()) {
                                        changeToLogIn();
                                    }
                                    // If sign in fails, display a message to the user. If sign in succeeds
                                    // the auth state listener will be notified and logic to handle the
                                    // signed in user can be handled in the listener.
                                    if (!task.isSuccessful()) {
                                        Toast.makeText(Signup_Login.this, "failed",
                                                Toast.LENGTH_SHORT).show();
                                    }

                                    // ...
                                }
                            });
                    }else {
                    Toast.makeText(this, "Your password is to short", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "You must enter a password", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "You Must enter an email address", Toast.LENGTH_SHORT).show();
        }
    }

    //TODO delete this before release
    public void shortcut(View view) {
        Intent shortcut = new Intent(Signup_Login.this, MainMenu.class);
        startActivity(shortcut);
    }
}

