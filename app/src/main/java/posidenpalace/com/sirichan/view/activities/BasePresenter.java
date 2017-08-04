package posidenpalace.com.sirichan.view.activities;


public interface BasePresenter<V extends BaseView> {
    void addView(V view);
    void removeView();
}
