package posidenpalace.com.sirichan.view.activities.setup_event;


import posidenpalace.com.sirichan.view.activities.BaseView;

public class SetupEventPresenter implements SetupEventContract.Presenter{
    BaseView view;

    @Override
    public void addView(SetupEventContract.View view) {
        this.view = view;
    }

    @Override
    public void removeView() {
        this.view = null;
    }
}
