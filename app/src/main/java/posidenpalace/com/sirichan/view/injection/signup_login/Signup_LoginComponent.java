package posidenpalace.com.sirichan.view.injection.signup_login;


import dagger.Component;
import posidenpalace.com.sirichan.view.activities.signup_login.Signup_Login;

@Component(modules = Signup_LoginModule.class)
public interface Signup_LoginComponent {
    void inject(Signup_Login signup_login);
}
