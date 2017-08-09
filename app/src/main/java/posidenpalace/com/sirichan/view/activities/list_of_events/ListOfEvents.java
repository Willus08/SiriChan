package posidenpalace.com.sirichan.view.activities.list_of_events;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import posidenpalace.com.sirichan.R;
import posidenpalace.com.sirichan.model.RealmDB.RealmHelper;
import posidenpalace.com.sirichan.view.activities.list_of_events.listAdapterPackage.TimeListAdapter;
import posidenpalace.com.sirichan.view.injection.list_of_events.DaggerListOfEventsComponent;

public class ListOfEvents extends AppCompatActivity implements ListOfEventsContract.View {
    @Inject ListOfEventsPresenter presenter;
    private RecyclerView recyclerView;
    private TimeListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_events);
        setupDagger();

        String dateString = savedInstanceState.getString("DATESTRING");
        RealmHelper helper = new RealmHelper();
        this.recyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.adapter = new TimeListAdapter(this,helper.gettime(dateString));
        this.recyclerView.setAdapter(this.adapter);
        presenter.addView(this);
    }

    public void setupDagger(){
        DaggerListOfEventsComponent.create().inject(this);
    }

    @Override
    public void showError(String error) {

    }
}
