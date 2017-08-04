package posidenpalace.com.sirichan.view.activities.selected_date;


import posidenpalace.com.sirichan.view.activities.BaseView;

public class SelectedDatePresenter implements SelectedDateContract.Presenter {
    BaseView view;
    @Override
    public void addView(SelectedDateContract.View view) {
        this.view = view;
    }

    @Override
    public void removeView() {
        this.view = null;
    }
}
