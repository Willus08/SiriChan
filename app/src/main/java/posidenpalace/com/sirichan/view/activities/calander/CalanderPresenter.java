package posidenpalace.com.sirichan.view.activities.calander;


import android.content.Context;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.ArrayList;
import java.util.Calendar;

import posidenpalace.com.sirichan.model.RealmDB.RealmHelper;
import posidenpalace.com.sirichan.model.RealmDB.UserEvent;

public class CalanderPresenter implements CalanderContract.Presenter{

    private CalanderContract.View view;
    private Context context;

    @Override
    public void addView(CalanderContract.View view) {
        this.view = view;
        this.context = (Context)view;
    }

    @Override
    public void removeView() {
        this.view = null;
        this.context = null;
    }

    @Override
    public void setCalendarRange(MaterialCalendarView calendar)
    {
        calendar.state().edit()
                .setFirstDayOfWeek(Calendar.WEDNESDAY)
                .setMinimumDate(CalendarDay.from(2016, 1, 1))
                .setMaximumDate(CalendarDay.from(2020, 1, 1))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();
    }

    @Override
    public void SetCalendarDates(MaterialCalendarView calendar,RealmHelper helper) {
        ArrayList<UserEvent> events = helper.getEvents();
        if (events.size() > 0) {
            for (UserEvent event : events) {
                calendar.setDateSelected(event.getDate(), true);
            }
        }
    }
}
