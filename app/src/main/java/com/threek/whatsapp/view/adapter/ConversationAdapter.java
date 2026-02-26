package com.threek.whatsapp.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.threek.whatsapp.R;
import com.threek.whatsapp.model.Conversation;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ConversationAdapter extends RecyclerView.Adapter<ConversationAdapter.ViewHolder> {

    private List<Conversation> conversations = new ArrayList<>();
    private OnConversationClickListener listener;

    public interface OnConversationClickListener {
        void onConversationClick(Conversation conversation);
    }

    public void setOnConversationClickListener(OnConversationClickListener listener) {
        this.listener = listener;
    }

    public void setConversations(List<Conversation> conversations) {
        this.conversations = conversations;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_conversation, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Conversation conversation = conversations.get(position);

        holder.name.setText(conversation.getDisplayName());
        holder.lastMessage.setText(conversation.getLastMessage());
        holder.time.setText(conversation.getLastMessageTime());

        if (conversation.getUser() != null && conversation.getUser().getAvatarResId() != 0) {
            holder.avatar.setImageResource(conversation.getUser().getAvatarResId());
        }

        if (conversation.getUser() != null && conversation.getUser().isOnline()) {
            holder.onlineIndicator.setVisibility(View.VISIBLE);
        } else {
            holder.onlineIndicator.setVisibility(View.GONE);
        }

        if (conversation.getUnreadCount() > 0) {
            holder.unreadBadge.setVisibility(View.VISIBLE);
            holder.unreadBadge.setText(String.valueOf(conversation.getUnreadCount()));
        } else {
            holder.unreadBadge.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) listener.onConversationClick(conversation);
        });
    }

    @Override
    public int getItemCount() {
        return conversations.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView avatar;
        View onlineIndicator;
        TextView name, lastMessage, time, unreadBadge;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.conversation_avatar);
            onlineIndicator = itemView.findViewById(R.id.online_indicator);
            name = itemView.findViewById(R.id.conversation_name);
            lastMessage = itemView.findViewById(R.id.conversation_last_message);
            time = itemView.findViewById(R.id.conversation_time);
            unreadBadge = itemView.findViewById(R.id.conversation_unread_badge);
        }
    }
}
