package posidenpalace.com.sirichan.view.activities.calander;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import posidenpalace.com.sirichan.R;
import posidenpalace.com.sirichan.model.RealmDB.RealmHelper;
import posidenpalace.com.sirichan.view.activities.selected_date.SelectedDate;
import posidenpalace.com.sirichan.view.injection.calander.DaggerCalanderComponent;



public class Calander extends AppCompatActivity implements CalanderContract.View{
    private static final String TAG = "Calender";
    @Inject CalanderPresenter presenter;

    // Calendar
    private MaterialCalendarView calendar;
    private Date _Date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calander);
        this.calendar = (MaterialCalendarView)findViewById(R.id.calendarView);
        setupDagger();
        presenter.addView(this);
        presenter.setCalendarRange(this.calendar);
        RealmHelper helper = new RealmHelper(this);
        presenter.SetCalendarDates(this.calendar,helper);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setShowHideAnimationEnabled(true);

        this.calendar.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected)
            {
                if(_Date == date.getDate()){
                    Intent intent = new Intent(Calander.this, SelectedDate.class);
                    DateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
                    intent.putExtra("DATESELECTED", df.format(_Date));
                    startActivity(intent);

                }

                _Date = date.getDate();
            }
        });
    }


    public void setupDagger(){
        DaggerCalanderComponent.create().inject(this);
    }

    @Override
    public void showError(String error) {

    }



//    // Button CLicks
//    public void onListEvets(View view)
//    {
//        if (_Date != null) {
//            Intent intent = new Intent(Calander.this, SelectedDate.class);
//            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//            intent.putExtra("DATESELECTED", df.format(_Date).toString());
//            startActivity(intent);
//        }else
//        {
//            this.showAlert();
//        }
//    }
//
//    private void showAlert()
//    {
//        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
//        builder1.setMessage("Please Select A Date");
//        builder1.setCancelable(true);
//
//        builder1.setPositiveButton(
//                "Ok",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        dialog.cancel();
//                    }
//                });
//
//        AlertDialog alert11 = builder1.create();
//        alert11.show();
//    }
//
//    //Button clicks
//    public void onAddEvent(View view)
//    {
//        if (_Date != null) {
//            Intent intent = new Intent(Calander.this, SetupEvent.class);
//            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//            intent.putExtra("DATESELECTED", df.format(_Date).toString());
//            startActivity(intent);
//        }else
//        {
//           this.showAlert();
//        }
//    }
//
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
