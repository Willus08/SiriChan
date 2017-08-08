package posidenpalace.com.sirichan.view.activities.calander;


import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.Calendar;
import java.util.List;

import posidenpalace.com.sirichan.model.dbHelper;
import posidenpalace.com.sirichan.model.userEvent;

public class CalanderPresenter implements CalanderContract.Presenter{

    CalanderContract.View view;

    @Override
    public void addView(CalanderContract.View view) {
        this.view = view;
    }

    @Override
    public void removeView() {
        this.view = null;
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
    public void SetCalendarDates(MaterialCalendarView calendar) {
        List<userEvent> Events = new dbHelper().queryEvents();

        for(int i = 0; i < Events.size(); i++)
        {
            calendar.setDateSelected(Events.get(i).getDate(),true);
        }
    }
}
