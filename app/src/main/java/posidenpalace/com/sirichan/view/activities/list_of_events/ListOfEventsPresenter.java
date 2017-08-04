package posidenpalace.com.sirichan.view.activities.list_of_events;


import posidenpalace.com.sirichan.view.activities.BaseView;

public class ListOfEventsPresenter implements ListOfEventsContract.Presenter {
    BaseView view;
    @Override
    public void addView(ListOfEventsContract.View view) {
        this.view = view;
    }

    @Override
    public void removeView() {
        this.view = null;
    }
}
