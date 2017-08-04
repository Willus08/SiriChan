package posidenpalace.com.sirichan.view.injection.selected_date;


import dagger.Component;
import posidenpalace.com.sirichan.view.activities.selected_date.SelectedDate;

@Component(modules = SelectedDateModule.class)
public interface SelectedDateComponent {
    void inject(SelectedDate selectedDate);
}
