package posidenpalace.com.sirichan.model.weatherpojos;

/**
 * Created by Android on 8/17/2017.
 */

public class RecyclerWeatherPojoHelper {

    MultipleWeatherPojo first;
    MultipleWeatherPojo second;
    MultipleWeatherPojo third;
    MultipleWeatherPojo fourth;


    public RecyclerWeatherPojoHelper(MultipleWeatherPojo first, MultipleWeatherPojo second, MultipleWeatherPojo third, MultipleWeatherPojo fourth) {
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
    }

    public MultipleWeatherPojo getFirst() {
        return first;
    }

    public MultipleWeatherPojo getSecond() {
        return second;
    }

    public MultipleWeatherPojo getThird() {
        return third;
    }

    public MultipleWeatherPojo getFourth() {
        return fourth;
    }
}
