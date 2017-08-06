package posidenpalace.com.sirichan.view.injection.signup_login;


import dagger.Module;
import dagger.Provides;
import posidenpalace.com.sirichan.view.activities.signup_login.Signup_LoginPresenter;

@Module
public class Signup_LoginModule {
    @Provides
    public Signup_LoginPresenter provideSignup_loginPresenter(){
        return new Signup_LoginPresenter();
    }
}
