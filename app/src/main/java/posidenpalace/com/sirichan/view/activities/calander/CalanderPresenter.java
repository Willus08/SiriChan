package posidenpalace.com.sirichan.view.activities.calander;


import android.content.Context;



public class CalanderPresenter implements CalanderContract.Presenter{
    CalanderContract.View view;
    Context context;
    @Override
    public void addView(CalanderContract.View view) {

        this.view = view;
        this.context = (Context)view;
    }

    @Override
    public void removeView() {
        this.view = null;
    }
}
