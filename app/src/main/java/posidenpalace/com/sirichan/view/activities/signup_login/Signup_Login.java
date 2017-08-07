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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup__login);
        mAuth = FirebaseAuth.getInstance();
        ButterKnife.bind(this);
        setupFirebaseLogin();
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
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
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

    public void setupDagger(){
        DaggerSignup_LoginComponent.create().inject(this);
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
        if (loginEmail.getText() != null) { // used to stop a potential null tostring exception
            String email = loginEmail.getText().toString();
            if (loginPassword.getText() != null) { // used to stop a potintial null tostring exception
                String password = loginPassword.getText().toString();
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());
                                Intent intent = new Intent(Signup_Login.this, MainMenu.class);
                                startActivity(intent);
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Log.w(TAG, "signInWithEmail:failed", task.getException());
                                    Toast.makeText(Signup_Login.this, "Failled to login", Toast.LENGTH_SHORT).show();
                                }

                                // ...
                            }
                        });
            }
        }
    }

    public void StartSignUp(View view) {
        changeToSignUp();
    }

    public void CreateAccount(View view) {
        if (signupEmail.getText() != null) { // used to stop a potential null tostring exception
            String email = signupEmail.getText().toString();
            if (signupPassword.getText() != null){ // used to stop a potintial null tostring exception
                String password = signupPassword.getText().toString();
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
                                changeToLogIn();
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(Signup_Login.this, "failed to make account",
                                            Toast.LENGTH_SHORT).show();
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


}

