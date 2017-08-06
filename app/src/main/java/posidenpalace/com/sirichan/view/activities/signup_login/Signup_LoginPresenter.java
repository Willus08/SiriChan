package posidenpalace.com.sirichan.view.activities.signup_login;



public class Signup_LoginPresenter implements Signup_LoginContract.Presenter {
    Signup_LoginContract.View view;

    @Override
    public void addView(Signup_LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void removeView() {
        this.view = null;
    }
}
