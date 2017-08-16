package posidenpalace.com.sirichan.model.weatherpojos;

/**
 * Created by Android on 8/16/2017.
 */

public class MultipleWeatherPojo {

    String weatherdate;
    String weatherPicture;
    String weatherType;
    double temp;


    public MultipleWeatherPojo(String date,String weatherPicture, String weatherType, double temp) {
        this.weatherPicture = weatherPicture;
        this.weatherType = weatherType;
        this.temp = temp;
        this.weatherdate=date;
    }

    public String getWeatherPicture() {
        return weatherPicture;
    }

    public String getWeatherType() {
        return weatherType;
    }

    public double getTemp() {
        return temp;
    }

    public String getWeatherdate() {
        return weatherdate;
    }
}
