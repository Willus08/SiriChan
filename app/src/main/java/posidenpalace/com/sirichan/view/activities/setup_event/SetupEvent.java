package posidenpalace.com.sirichan.view.activities.setup_event;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import javax.inject.Inject;

import posidenpalace.com.sirichan.R;
import posidenpalace.com.sirichan.view.injection.setup_event.DaggerSetupEventComponent;

public class SetupEvent extends AppCompatActivity implements SetupEventContract.View{
    @Inject SetupEventPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_event);
        setupDagger();
        presenter.addView(this);
    }

    public void setupDagger(){
        DaggerSetupEventComponent.create().inject(this);
    }

    @Override
    public void showError(String error) {

    }
}
