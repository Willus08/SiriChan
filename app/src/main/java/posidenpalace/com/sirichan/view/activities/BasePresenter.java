package posidenpalace.com.sirichan.view.activities;

/**
 * Created by Android on 7/13/2017.
 */

public interface BasePresenter<V extends BaseView> {
    void addView(V view);
    void removeView();
}
