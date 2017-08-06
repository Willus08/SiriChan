package posidenpalace.com.sirichan.view.activities.gps;


public class GPSPresenter implements GPSContract.Presenter{
    GPSContract.View view;
    @Override
    public void addView(GPSContract.View view) {
        this.view = view;
    }

    @Override
    public void removeView() {
        this.view = null;
    }
}
