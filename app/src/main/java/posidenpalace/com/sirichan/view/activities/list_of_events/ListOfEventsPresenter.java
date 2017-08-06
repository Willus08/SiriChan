package posidenpalace.com.sirichan.view.activities.list_of_events;


public class ListOfEventsPresenter implements ListOfEventsContract.Presenter {
    ListOfEventsContract.View view;
    @Override
    public void addView(ListOfEventsContract.View view) {
        this.view = view;
    }

    @Override
    public void removeView() {
        this.view = null;
    }
}
