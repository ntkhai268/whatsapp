package com.threek.whatsapp.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.threek.whatsapp.R;
import com.threek.whatsapp.model.Message;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_SENT = 1;
    private static final int VIEW_TYPE_RECEIVED = 2;

    private List<Message> messages = new ArrayList<>();
    private String currentUserId = "u0"; // Mock current user

    public void setMessages(List<Message> messages) {
        this.messages = messages;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        Message message = messages.get(position);
        return message.getSenderId().equals(currentUserId) ? VIEW_TYPE_SENT : VIEW_TYPE_RECEIVED;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_SENT) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message_sent, parent, false);
            return new SentViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message_received, parent, false);
            return new ReceivedViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Message message = messages.get(position);

        if (holder instanceof SentViewHolder) {
            SentViewHolder sentHolder = (SentViewHolder) holder;
            sentHolder.content.setText(message.getContent());
            sentHolder.time.setText(message.getTimestamp());

            if (message.isEdited()) {
                sentHolder.edited.setVisibility(View.VISIBLE);
            } else {
                sentHolder.edited.setVisibility(View.GONE);
            }

            if (message.getStatus() != null) {
                sentHolder.status.setVisibility(View.VISIBLE);
                switch (message.getStatus()) {
                    case SENT: sentHolder.status.setText("✓"); break;
                    case DELIVERED: sentHolder.status.setText("✓✓"); break;
                    case READ: sentHolder.status.setText("✓✓"); break;
                }
            }
        } else if (holder instanceof ReceivedViewHolder) {
            ReceivedViewHolder receivedHolder = (ReceivedViewHolder) holder;
            receivedHolder.content.setText(message.getContent());
            receivedHolder.time.setText(message.getTimestamp());
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    static class SentViewHolder extends RecyclerView.ViewHolder {
        TextView content, time, edited, status;

        SentViewHolder(@NonNull View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.message_content);
            time = itemView.findViewById(R.id.message_time);
            edited = itemView.findViewById(R.id.message_edited);
            status = itemView.findViewById(R.id.message_status);
        }
    }

    static class ReceivedViewHolder extends RecyclerView.ViewHolder {
        TextView content, time;

        ReceivedViewHolder(@NonNull View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.message_content);
            time = itemView.findViewById(R.id.message_time);
        }
    }
}
