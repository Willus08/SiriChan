package posidenpalace.com.sirichan.view.activities.calander;


import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import posidenpalace.com.sirichan.view.activities.BasePresenter;
import posidenpalace.com.sirichan.view.activities.BaseView;

public interface CalanderContract {
    interface View extends BaseView{

    }
    interface Presenter extends BasePresenter<View>{

        void setCalendarRange(MaterialCalendarView calendar);
        void SetCalendarDates(MaterialCalendarView calendar);

    }
}
