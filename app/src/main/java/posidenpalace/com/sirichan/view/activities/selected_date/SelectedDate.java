package posidenpalace.com.sirichan.view.activities.selected_date;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import posidenpalace.com.sirichan.R;
import posidenpalace.com.sirichan.view.injection.selected_date.DaggerSelectedDateComponent;

public class SelectedDate extends AppCompatActivity implements SelectedDateContract.View{
    @Inject SelectedDatePresenter presenter;
    @BindView(R.id.tvSDDate)
    TextView date;
    @BindView(R.id.tvSDnoEvents)
    TextView message;

    RecyclerView daysEvents;
    //TODO replace this with the actual adapter for this activity
    RecyclerView.Adapter adapt;
    RecyclerView.ItemAnimator itemAnimator;
    RecyclerView.LayoutManager layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_date);
        setupDagger();
        presenter.addView(this);
        ButterKnife.bind(this);
        setupDate();

    }

    private void setupDate() {
        String importedDate = getIntent().getStringExtra("DATESELECTED");
        date.setText("Date: "+importedDate);
        setupRecycler();
    }

    private void setupRecycler() {
        //TODO Replace this later
//        adapt = new RecyclerView.Adapter() {
//            @Override
//            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//                return null;
//            }
//
//            @Override
//            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//
//            }
//
//            @Override
//            public int getItemCount() {
//                return 0;
//            }
//        };
   //     layout = new LinearLayoutManager(this);
     //   itemAnimator = new DefaultItemAnimator();

       // if (true)
        //{
            message.setVisibility(View.VISIBLE);

        //}else{
          //  message.setVisibility(View.GONE);
            //daysEvents.setItemAnimator(itemAnimator);
            //daysEvents.setLayoutManager(layout);
            //daysEvents.setAdapter(adapt);
        //}

    }

    public void setupDagger(){
        DaggerSelectedDateComponent.create().inject(this);
    }

    @Override
    public void showError(String error) {

    }

    public void addEvent(View view) {
    }
}
