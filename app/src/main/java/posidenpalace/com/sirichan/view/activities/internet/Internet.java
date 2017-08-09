package posidenpalace.com.sirichan.view.activities.internet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.Toast;

import javax.inject.Inject;

import posidenpalace.com.sirichan.R;
import posidenpalace.com.sirichan.view.injection.internet.DaggerInternetComponent;

public class Internet extends AppCompatActivity implements InternetContract.View {
    private static final String TAG = "internet" ;


    WebView internet;
    String searchText;
    @Inject InternetPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet);
        setupDagger();
        presenter.addView(this);
        internet = (WebView) findViewById(R.id.wvIinternet);
        internet.getSettings().setJavaScriptEnabled(true);
        searchText = getIntent().getStringExtra("voice");
        if (searchText != null ){
            Toast.makeText(this, "Seraching: " + searchText, Toast.LENGTH_SHORT).show();
            internet.loadUrl("https://www.google.com/search?q="+searchText);

        }else {
            internet.loadUrl("https://www.google.com");
        }

    }

    public void setupDagger(){
        DaggerInternetComponent.create().inject(this);
    }

    @Override
    public void showError(String error) {

    }

}
