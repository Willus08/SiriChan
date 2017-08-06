package posidenpalace.com.sirichan.view.activities.signup_login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
}
