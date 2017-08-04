package posidenpalace.com.sirichan.view.activities.calander;


import posidenpalace.com.sirichan.view.activities.BaseView;

public class CalanderPresenter implements CalanderContract.Presenter{
    BaseView view;
    @Override
    public void addView(CalanderContract.View view) {
        this.view = view;
    }

    @Override
    public void removeView() {
        this.view = null;
    }
}
