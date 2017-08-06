package posidenpalace.com.sirichan.view.activities.selected_date;


public class SelectedDatePresenter implements SelectedDateContract.Presenter {
    SelectedDateContract.View view;
    @Override
    public void addView(SelectedDateContract.View view) {
        this.view = view;
    }

    @Override
    public void removeView() {
        this.view = null;
    }
}
