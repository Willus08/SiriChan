package posidenpalace.com.sirichan.view.activities.list_of_events.listAdapterPackage;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import posidenpalace.com.sirichan.R;
import posidenpalace.com.sirichan.model.RealmDB.UserEvent;
import posidenpalace.com.sirichan.view.activities.selected_date.SelectedDate;

/**
 * Created by michaeliverson on 8/9/17.
 */

public class TimeListAdapter extends RecyclerView.Adapter<TimeListAdapter.ViewHolder>
{
    private Context context;
    private ArrayList<UserEvent> events;
    public TimeListAdapter(Context context, ArrayList<UserEvent> Events)
    {
        this.context = context;
        this.events = Events;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        holder.tvTime.setText(df.format(this.events.get(position).getDate()));
        holder.tvEvent.setText(this.events.get(position).getEventtitle());
        holder.event = this.events.get(position);
    }

    @Override
    public int getItemCount() {
        return this.events.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvTime;
        public TextView tvEvent;
        public UserEvent event;

        public ViewHolder(View itemView) {
            super(itemView);

            this.tvEvent = (TextView)itemView.findViewById(R.id.tvEvent);
            this.tvTime = (TextView)itemView.findViewById(R.id.tvTime);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), SelectedDate.class);
                    intent.putExtra("EVENT",event);
                    view.getContext().startActivity(intent);
                }
            });
        }

    }
}
