package posidenpalace.com.sirichan.view.activities.internet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;

import javax.inject.Inject;

import butterknife.BindView;
import posidenpalace.com.sirichan.R;
import posidenpalace.com.sirichan.view.injection.internet.DaggerInternetComponent;

public class Internet extends AppCompatActivity implements InternetContract.View {
    @BindView(R.id.etIsearchBar)
    EditText search;

    @BindView(R.id.wvIinternet)
    WebView internet;

    @Inject InternetPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet);
        setupDagger();
        presenter.addView(this);
        String voicecmnd = getIntent().getStringExtra("voice");
        if (voicecmnd != null){
            search.setText(voicecmnd);
            internet.loadUrl(voicecmnd);

        }

    }



    public void setupDagger(){
        DaggerInternetComponent.create().inject(this);
    }

    @Override
    public void showError(String error) {

    }

    public void startSearch(View view) {
        if(search.getText() != null){
            internet.loadUrl(search.getText().toString());
        }
    }
}
