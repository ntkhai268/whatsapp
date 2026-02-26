package com.threek.whatsapp.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.threek.whatsapp.R;
import com.threek.whatsapp.model.Notification;

import java.util.ArrayList;
import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    private List<Notification> notifications = new ArrayList<>();

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_notification, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Notification notification = notifications.get(position);

        holder.title.setText(notification.getTitle());
        holder.subtitle.setText(notification.getSubtitle());
        holder.time.setText(notification.getTimeAgo());

        if (notification.isRead()) {
            holder.dot.setVisibility(View.INVISIBLE);
            holder.title.setAlpha(0.7f);
        } else {
            holder.dot.setVisibility(View.VISIBLE);
            holder.title.setAlpha(1.0f);
        }
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View dot;
        TextView title, subtitle, time;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            dot = itemView.findViewById(R.id.notification_dot);
            title = itemView.findViewById(R.id.notification_title);
            subtitle = itemView.findViewById(R.id.notification_subtitle);
            time = itemView.findViewById(R.id.notification_time);
        }
    }
}
