package posidenpalace.com.sirichan.view.activities.gps;


import android.content.Context;

public class GPSPresenter implements GPSContract.Presenter{
    GPSContract.View view;
    Context context;
    @Override
    public void addView(GPSContract.View view) {
        this.view = view;
        this.context = (Context)view;
    }

    @Override
    public void removeView() {
        this.view = null;
    }
}
