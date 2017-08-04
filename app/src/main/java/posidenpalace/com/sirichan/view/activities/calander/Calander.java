package posidenpalace.com.sirichan.view.activities.calander;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import javax.inject.Inject;

import posidenpalace.com.sirichan.R;
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
    public void setupDagger(){
        DaggerCalanderComponent.create().inject(this);
    }

    @Override
    public void showError(String error) {

    }
}
