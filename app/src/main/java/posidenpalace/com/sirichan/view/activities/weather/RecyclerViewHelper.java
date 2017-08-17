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
import posidenpalace.com.sirichan.model.weatherpojos.MultipleWeatherPojo;
import posidenpalace.com.sirichan.model.weatherpojos.RecyclerWeatherPojoHelper;

/**
 * Created by Android on 8/17/2017.
 */

public class RecyclerViewHelper extends RecyclerView.Adapter<RecyclerViewHelper.ViewHolder> {


    private List<RecyclerWeatherPojoHelper> multipleWeatherPojos=new ArrayList<>();

    public RecyclerViewHelper(List<RecyclerWeatherPojoHelper> multipleWeatherPojos) {
        this.multipleWeatherPojos = multipleWeatherPojos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.weater_recycle_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RecyclerWeatherPojoHelper recyclerWeatherPojoHelper=multipleWeatherPojos.get(position);
        holder.dateAM12.setText(recyclerWeatherPojoHelper.getAm12().getWeatherdate());
        holder.dateAM3.setText(recyclerWeatherPojoHelper.getAm3().getWeatherdate());
        holder.dateAM6.setText(recyclerWeatherPojoHelper.getAm6().getWeatherdate());
        holder.dateAM9.setText(recyclerWeatherPojoHelper.getAm9().getWeatherdate());
        holder.datePM12.setText(recyclerWeatherPojoHelper.getPm12().getWeatherdate());
        holder.datePM3.setText(recyclerWeatherPojoHelper.getPm3().getWeatherdate());
        holder.datePM6.setText(recyclerWeatherPojoHelper.getPm6().getWeatherdate());
        holder.datePM9.setText(recyclerWeatherPojoHelper.getPm9().getWeatherdate());

        Glide.with(holder.itemView.getContext())
                .load("http://openweathermap.org/img/w/" + recyclerWeatherPojoHelper.getAm12().getWeatherPicture() + ".png")
                .into(holder.weatherImageAM12);

        Glide.with(holder.itemView.getContext())
                .load("http://openweathermap.org/img/w/" + recyclerWeatherPojoHelper.getAm3().getWeatherPicture() + ".png")
                .into(holder.weatherImageAM3);

        Glide.with(holder.itemView.getContext())
                .load("http://openweathermap.org/img/w/" + recyclerWeatherPojoHelper.getAm6().getWeatherPicture() + ".png")
                .into(holder.weatherImageAM6);

        Glide.with(holder.itemView.getContext())
                .load("http://openweathermap.org/img/w/" + recyclerWeatherPojoHelper.getAm9().getWeatherPicture() + ".png")
                .into(holder.weatherImageAM9);

        Glide.with(holder.itemView.getContext())
                .load("http://openweathermap.org/img/w/" + recyclerWeatherPojoHelper.getPm12().getWeatherPicture() + ".png")
                .into(holder.weatherImagePM12);

        Glide.with(holder.itemView.getContext())
                .load("http://openweathermap.org/img/w/" + recyclerWeatherPojoHelper.getPm3().getWeatherPicture() + ".png")
                .into(holder.weatherImagePM3);


        Glide.with(holder.itemView.getContext())
                .load("http://openweathermap.org/img/w/" + recyclerWeatherPojoHelper.getPm6().getWeatherPicture() + ".png")
                .into(holder.weatherImagePM6);

        Glide.with(holder.itemView.getContext())
                .load("http://openweathermap.org/img/w/" + recyclerWeatherPojoHelper.getPm9().getWeatherPicture() + ".png")
                .into(holder.weatherImagePM9);


    }

    @Override
    public int getItemCount() {
        return multipleWeatherPojos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView weatherImageAM12;
        ImageView weatherImageAM3;
        ImageView weatherImageAM6;
        ImageView weatherImageAM9;
        ImageView weatherImagePM12;
        ImageView weatherImagePM3;
        ImageView weatherImagePM6;
        ImageView weatherImagePM9;

        TextView timeAM12;
        TextView timeAM3;
        TextView timeAM6;
        TextView timeAM9;
        TextView timePM12;
        TextView timePM3;
        TextView timePM6;
        TextView timePM9;

        TextView dateAM12;
        TextView dateAM3;
        TextView dateAM6;
        TextView dateAM9;
        TextView datePM12;
        TextView datePM3;
        TextView datePM6;
        TextView datePM9;

        TextView tempAM12;
        TextView tempAM3;
        TextView tempAM6;
        TextView tempAM9;
        TextView tempPM12;
        TextView tempPM3;
        TextView tempPM6;
        TextView tempPM9;

        TextView typeAM12;
        TextView typeAM3;
        TextView typeAM6;
        TextView typeAM9;
        TextView typePM12;
        TextView typePM3;
        TextView typePM6;
        TextView typePM9;


        public ViewHolder(View itemView) {
            super(itemView);
            weatherImageAM12=itemView.findViewById(R.id.ivWR12amImage);
            weatherImageAM3=itemView.findViewById(R.id.ivWR3amImage);
            weatherImageAM6=itemView.findViewById(R.id.ivWR6amImage);
            weatherImageAM9=itemView.findViewById(R.id.ivWR9amImage);
            weatherImagePM12=itemView.findViewById(R.id.ivWR12pmImage);
            weatherImagePM3=itemView.findViewById(R.id.ivWR3pmImage);
            weatherImagePM6=itemView.findViewById(R.id.ivWR6pmImage);
            weatherImagePM9=itemView.findViewById(R.id.ivWR9pmImage);

            timeAM12=itemView.findViewById(R.id.tvWR12amTime);
            timeAM3=itemView.findViewById(R.id.tvWR3amTime);
            timeAM6=itemView.findViewById(R.id.tvWR6amTime);
            timeAM9=itemView.findViewById(R.id.tvWR9amTime);
            timePM12=itemView.findViewById(R.id.tvWR12pmTime);
            timePM3=itemView.findViewById(R.id.tvWR3pmTime);
            timePM6=itemView.findViewById(R.id.tvWR6pmTime);
            timePM9=itemView.findViewById(R.id.tvWR9pmTime);

            dateAM12=itemView.findViewById(R.id.tvWR12amDate);
            dateAM3=itemView.findViewById(R.id.tvWR3amDate);
            dateAM6=itemView.findViewById(R.id.tvWR6amDate);
            dateAM9=itemView.findViewById(R.id.tvWR9amDate);
            datePM12=itemView.findViewById(R.id.tvWR12pmDate);
            datePM3=itemView.findViewById(R.id.tvWR3pmDate);
            datePM6=itemView.findViewById(R.id.tvWR6pmDate);
            datePM9=itemView.findViewById(R.id.tvWR9pmDate);

            tempAM12=itemView.findViewById(R.id.tvWR12amTemp);
            tempAM3=itemView.findViewById(R.id.tvWR3amTemp);
            tempAM6=itemView.findViewById(R.id.tvWR6amTemp);
            tempAM9=itemView.findViewById(R.id.tvWR9amTemp);
            tempPM12=itemView.findViewById(R.id.tvWR12pmTemp);
            tempPM3=itemView.findViewById(R.id.tvWR3pmTemp);
            tempPM6=itemView.findViewById(R.id.tvWR6pmTemp);
            tempPM9=itemView.findViewById(R.id.tvWR9pmTemp);

            typeAM12=itemView.findViewById(R.id.tvWR12amType);
            typeAM3=itemView.findViewById(R.id.tvWR3amType);
            typeAM6=itemView.findViewById(R.id.tvWR6amType);
            typeAM9=itemView.findViewById(R.id.tvWR9amType);
            typePM12=itemView.findViewById(R.id.tvWR12pmType);
            typePM3=itemView.findViewById(R.id.tvWR3pmType);
            typePM6=itemView.findViewById(R.id.tvWR6pmType);
            typePM9=itemView.findViewById(R.id.tvWR9pmType);

        }
    }
}
