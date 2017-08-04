package posidenpalace.com.sirichan.view.activities.weather;


import posidenpalace.com.sirichan.view.activities.BaseView;

public class WeatherPresenter implements WeatherContract.Presenter{
    BaseView view;
    @Override
    public void addView(WeatherContract.View view) {
        this.view = view;
    }

    @Override
    public void removeView() {
        this.view = null;
    }
}
