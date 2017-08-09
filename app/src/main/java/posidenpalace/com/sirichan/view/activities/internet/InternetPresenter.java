package posidenpalace.com.sirichan.view.activities.internet;


public class InternetPresenter implements InternetContract.Presenter{
    InternetContract.View view;
    @Override
    public void addView(InternetContract.View view) {
        this.view = view;
    }

    @Override
    public void removeView() {
        this.view = null;
    }
}
