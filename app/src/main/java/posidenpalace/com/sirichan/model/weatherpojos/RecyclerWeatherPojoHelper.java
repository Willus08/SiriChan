package posidenpalace.com.sirichan.model.weatherpojos;

/**
 * Created by Android on 8/17/2017.
 */

public class RecyclerWeatherPojoHelper {

    MultipleWeatherPojo am12;
    MultipleWeatherPojo am3;
    MultipleWeatherPojo am6;
    MultipleWeatherPojo am9;
    MultipleWeatherPojo pm12;
    MultipleWeatherPojo pm3;
    MultipleWeatherPojo pm6;
    MultipleWeatherPojo pm9;

    public RecyclerWeatherPojoHelper(MultipleWeatherPojo am12, MultipleWeatherPojo am3, MultipleWeatherPojo am6, MultipleWeatherPojo am9, MultipleWeatherPojo pm12, MultipleWeatherPojo pm3, MultipleWeatherPojo pm6, MultipleWeatherPojo pm9) {
        this.am12 = am12;
        this.am3 = am3;
        this.am6 = am6;
        this.am9 = am9;
        this.pm12 = pm12;
        this.pm3 = pm3;
        this.pm6 = pm6;
        this.pm9 = pm9;
    }

    public MultipleWeatherPojo getAm12() {
        return am12;
    }

    public MultipleWeatherPojo getAm3() {
        return am3;
    }

    public MultipleWeatherPojo getAm6() {
        return am6;
    }

    public MultipleWeatherPojo getAm9() {
        return am9;
    }

    public MultipleWeatherPojo getPm12() {
        return pm12;
    }

    public MultipleWeatherPojo getPm3() {
        return pm3;
    }

    public MultipleWeatherPojo getPm6() {
        return pm6;
    }

    public MultipleWeatherPojo getPm9() {
        return pm9;
    }
}
