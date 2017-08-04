package posidenpalace.com.sirichan.view.activities.main_menu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import javax.inject.Inject;

import posidenpalace.com.sirichan.R;
import posidenpalace.com.sirichan.view.injection.main_menu.DaggerMainMenuComponent;

public class MainMenu extends AppCompatActivity implements MainMenuContract.View {
    @Inject MainMenuPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        setupDagger();
        presenter.addView(this);
    }

    public void setupDagger(){
        DaggerMainMenuComponent.create().inject(this);
    }

    @Override
    public void showError(String error) {

    }
}
