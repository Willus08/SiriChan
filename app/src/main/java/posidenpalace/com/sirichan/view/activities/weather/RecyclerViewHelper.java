package posidenpalace.com.sirichan.view.activities.weather;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import posidenpalace.com.sirichan.R;
import posidenpalace.com.sirichan.model.weatherpojos.RecyclerWeatherPojoHelper;


public class RecyclerViewHelper extends RecyclerView.Adapter<RecyclerViewHelper.ViewHolder> {


    private List<RecyclerWeatherPojoHelper> multipleWeatherPojos=new ArrayList<>();
    private double kelvinTemp;
    private String temp;


    public RecyclerViewHelper(List<RecyclerWeatherPojoHelper> multipleWeatherPojos) {
        this.multipleWeatherPojos = multipleWeatherPojos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_items_alt,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RecyclerWeatherPojoHelper recyclerWeatherPojoHelper=multipleWeatherPojos.get(position);

        holder.theDate.setText("Date: "+recyclerWeatherPojoHelper.getFirst().getWeatherdate().substring(0,10));


        //time
        holder.timeAM12.setText(recyclerWeatherPojoHelper.getFirst().getWeatherdate().substring(11,16));
        holder.timeAM3.setText(recyclerWeatherPojoHelper.getSecond().getWeatherdate().substring(11,16));
        holder.timeAM6.setText(recyclerWeatherPojoHelper.getThird().getWeatherdate().substring(11,16));
        holder.timeAM9.setText(recyclerWeatherPojoHelper.getFourth().getWeatherdate().substring(11,16));


        //picture
        Glide.with(holder.itemView.getContext())
                .load("http://openweathermap.org/img/w/" + recyclerWeatherPojoHelper.getFirst().getWeatherPicture() + ".png")
                .into(holder.weatherImageAM12);

        Glide.with(holder.itemView.getContext())
                .load("http://openweathermap.org/img/w/" + recyclerWeatherPojoHelper.getSecond().getWeatherPicture() + ".png")
                .into(holder.weatherImageAM3);

        Glide.with(holder.itemView.getContext())
                .load("http://openweathermap.org/img/w/" + recyclerWeatherPojoHelper.getThird().getWeatherPicture() + ".png")
                .into(holder.weatherImageAM6);

        Glide.with(holder.itemView.getContext())
                .load("http://openweathermap.org/img/w/" + recyclerWeatherPojoHelper.getFourth().getWeatherPicture() + ".png")
                .into(holder.weatherImageAM9);




        //temperature
        kelvinTemp = recyclerWeatherPojoHelper.getFirst().getTemp();
        kelvinTemp = (kelvinTemp * 9 / 5) - 459.67;
        temp = String.format("%.2f", kelvinTemp);
        holder.tempAM12.setText(temp);

        kelvinTemp = recyclerWeatherPojoHelper.getSecond().getTemp();
        kelvinTemp = (kelvinTemp * 9 / 5) - 459.67;
        temp = String.format("%.2f", kelvinTemp);
        holder.tempAM3.setText(temp);

        kelvinTemp = recyclerWeatherPojoHelper.getThird().getTemp();
        kelvinTemp = (kelvinTemp * 9 / 5) - 459.67;
        temp = String.format("%.2f", kelvinTemp);
        holder.tempAM6.setText(temp);


        kelvinTemp = recyclerWeatherPojoHelper.getFourth().getTemp();
        kelvinTemp = (kelvinTemp * 9 / 5) - 459.67;
        temp = String.format("%.2f", kelvinTemp);
        holder.tempAM9.setText(temp);



    }

    @Override
    public int getItemCount() {
        return multipleWeatherPojos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView theDate;

        ImageView weatherImageAM12;
        ImageView weatherImageAM3;
        ImageView weatherImageAM6;
        ImageView weatherImageAM9;

        TextView timeAM12;
        TextView timeAM3;
        TextView timeAM6;
        TextView timeAM9;

        TextView tempAM12;
        TextView tempAM3;
        TextView tempAM6;
        TextView tempAM9;

        TextView typeAM12;
        TextView typeAM3;
        TextView typeAM6;
        TextView typeAM9;


        public ViewHolder(View itemView) {
            super(itemView);

            theDate=itemView.findViewById(R.id.tvWIDate);

            weatherImageAM12=itemView.findViewById(R.id.ivWIweatherImage12);
            weatherImageAM3=itemView.findViewById(R.id.ivWIweatherImage3);
            weatherImageAM6=itemView.findViewById(R.id.ivWIweatherImage6);
            weatherImageAM9=itemView.findViewById(R.id.ivWIweatherImage9);

            timeAM12=itemView.findViewById(R.id.tvWItime12);
            timeAM3=itemView.findViewById(R.id.tvWItime3);
            timeAM6=itemView.findViewById(R.id.tvWItime6);
            timeAM9=itemView.findViewById(R.id.tvWItime9);

            tempAM12=itemView.findViewById(R.id.tvWItemo12);
            tempAM3=itemView.findViewById(R.id.tvWItemo3);
            tempAM6=itemView.findViewById(R.id.tvWItemo6);
            tempAM9=itemView.findViewById(R.id.tvWItemo9);

            typeAM12=itemView.findViewById(R.id.tvWIweathertype12);
            typeAM3=itemView.findViewById(R.id.tvWIweathertype3);
            typeAM6=itemView.findViewById(R.id.tvWIweathertype6);
            typeAM9=itemView.findViewById(R.id.tvWIweathertype9);

        }
    }
}
