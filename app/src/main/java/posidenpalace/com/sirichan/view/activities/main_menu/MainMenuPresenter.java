package posidenpalace.com.sirichan.view.activities.main_menu;


public class MainMenuPresenter implements MainMenuContract.Presenter{

    MainMenuContract.View view;
    @Override
    public void addView(MainMenuContract.View view) {
        this.view = view;
    }

    @Override
    public void removeView() {
        this.view = null;
    }
}
