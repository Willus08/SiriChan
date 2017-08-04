package posidenpalace.com.sirichan.view.activities.main_menu;


import posidenpalace.com.sirichan.view.activities.BaseView;

public class MainMenuPresenter implements MainMenuContract.Presenter{

    BaseView view;
    @Override
    public void addView(MainMenuContract.View view) {
        this.view = view;
    }

    @Override
    public void removeView() {
        this.view = null;
    }
}
