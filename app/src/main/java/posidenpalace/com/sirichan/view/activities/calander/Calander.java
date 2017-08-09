package posidenpalace.com.sirichan.view.activities.calander;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.Date;

import javax.inject.Inject;

import posidenpalace.com.sirichan.R;
import posidenpalace.com.sirichan.model.DaoSession;
import posidenpalace.com.sirichan.view.activities.selected_date.SelectedDate;
import posidenpalace.com.sirichan.view.injection.calander.DaggerCalanderComponent;



public class Calander extends AppCompatActivity implements CalanderContract.View{
    private static final String TAG = "Calender";
    @Inject CalanderPresenter presenter;

    // Calendar
    private MaterialCalendarView calendar;
    private Date Date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calander);
        this.calendar = (MaterialCalendarView)findViewById(R.id.calendarView);
        setupDagger();
        presenter.addView(this);
        presenter.setCalendarRange(this.calendar);
        presenter.SetCalendarDates(this.calendar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setShowHideAnimationEnabled(true);

        this.calendar.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected)
            {
                Date = date.getDate();
            }
        });
    }


    public void setupDagger(){
        DaggerCalanderComponent.create().inject(this);
    }

    @Override
    public void showError(String error) {

    }



    // Button CLicks
    public void onListEvets(View view)
    {
        Intent intent = new Intent(Calander.this, SelectedDate.class);
        startActivity(intent);
    }

    //Button clicks
    public void onAddEvent(View view) {
       // Intent intent = new Intent(Calander.this,)
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==android.R.id.home)
        {
            Log.d(TAG, "onOptionsItemSelected: Home selected");
            finish();

        }
        return super.onOptionsItemSelected(item);
    }
}
