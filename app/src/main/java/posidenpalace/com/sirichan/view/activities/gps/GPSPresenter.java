package posidenpalace.com.sirichan.view.activities.gps;


import posidenpalace.com.sirichan.view.activities.BaseView;

public class GPSPresenter implements GPSContract.Presenter{
    BaseView view;
    @Override
    public void addView(GPSContract.View view) {
        this.view = view;
    }

    @Override
    public void removeView() {
        this.view = null;
    }
}
