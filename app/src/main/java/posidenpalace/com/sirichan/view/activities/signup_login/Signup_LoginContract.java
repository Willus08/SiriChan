package posidenpalace.com.sirichan.view.activities.signup_login;


import posidenpalace.com.sirichan.view.activities.BasePresenter;
import posidenpalace.com.sirichan.view.activities.BaseView;

public interface Signup_LoginContract {
    interface View extends BaseView{
        void changeToSignUp();
        void changeToLogIn();
    }
    interface Presenter extends BasePresenter<View>{

    }
}
