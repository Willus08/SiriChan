package posidenpalace.com.sirichan.view.activities.selected_date;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import posidenpalace.com.sirichan.R;
import posidenpalace.com.sirichan.view.injection.selected_date.DaggerSelectedDateComponent;

public class SelectedDate extends AppCompatActivity implements SelectedDateContract.View{
    @Inject SelectedDatePresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_date);
        setupDagger();
        presenter.addView(this);
    }

    public void setupDagger(){
        DaggerSelectedDateComponent.create().inject(this);
    }

    @Override
    public void showError(String error) {

    }
}
