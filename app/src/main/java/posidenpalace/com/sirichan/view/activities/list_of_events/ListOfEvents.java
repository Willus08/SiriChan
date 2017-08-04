package posidenpalace.com.sirichan.view.activities.list_of_events;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import javax.inject.Inject;

import posidenpalace.com.sirichan.R;
import posidenpalace.com.sirichan.view.injection.list_of_events.DaggerListOfEventsComponent;

public class ListOfEvents extends AppCompatActivity implements ListOfEventsContract.View {
    @Inject ListOfEventsPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_events);
        setupDagger();
        presenter.addView(this);
    }

    public void setupDagger(){
        DaggerListOfEventsComponent.create().inject(this);
    }

    @Override
    public void showError(String error) {

    }
}
