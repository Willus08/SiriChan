package posidenpalace.com.sirichan.view.activities.calander;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import javax.inject.Inject;

import posidenpalace.com.sirichan.R;
import posidenpalace.com.sirichan.view.activities.selected_date.SelectedDate;
import posidenpalace.com.sirichan.view.injection.calander.DaggerCalanderComponent;


public class Calander extends AppCompatActivity implements CalanderContract.View{
    @Inject CalanderPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calander);
        setupDagger();
        presenter.addView(this);
    }
    public void setupDagger()
    {
        DaggerCalanderComponent.create().inject(this);
    }

    @Override
    public void showError(String error) {

    }

    public void onAdd(View view) {
        Intent intent = new Intent(Calander.this, SelectedDate.class);
        startActivity(intent);
    }

    public void onGoEvents(View view) {

    }
}
