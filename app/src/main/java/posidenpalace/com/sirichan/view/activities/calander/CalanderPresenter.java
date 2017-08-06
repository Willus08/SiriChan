package posidenpalace.com.sirichan.view.activities.calander;


public class CalanderPresenter implements CalanderContract.Presenter{
    CalanderContract.View view;
    @Override
    public void addView(CalanderContract.View view) {
        this.view = view;
    }

    @Override
    public void removeView() {
        this.view = null;
    }
}
