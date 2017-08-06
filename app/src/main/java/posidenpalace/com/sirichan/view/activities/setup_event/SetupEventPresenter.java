package posidenpalace.com.sirichan.view.activities.setup_event;


public class SetupEventPresenter implements SetupEventContract.Presenter{
    SetupEventContract.View view;

    @Override
    public void addView(SetupEventContract.View view) {
        this.view = view;
    }

    @Override
    public void removeView() {
        this.view = null;
    }
}
