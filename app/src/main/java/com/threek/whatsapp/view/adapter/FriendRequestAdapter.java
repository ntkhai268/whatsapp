package com.threek.whatsapp.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.threek.whatsapp.R;
import com.threek.whatsapp.model.FriendRequest;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FriendRequestAdapter extends RecyclerView.Adapter<FriendRequestAdapter.ViewHolder> {

    private List<FriendRequest> requests = new ArrayList<>();
    private OnRequestActionListener listener;

    public interface OnRequestActionListener {
        void onAccept(FriendRequest request);
        void onDecline(FriendRequest request);
    }

    public void setOnRequestActionListener(OnRequestActionListener listener) {
        this.listener = listener;
    }

    public void setRequests(List<FriendRequest> requests) {
        this.requests = requests;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_friend_request, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FriendRequest request = requests.get(position);

        holder.name.setText(request.getUser().getFullName());
        holder.username.setText("@" + request.getUser().getUsername());
        holder.mutual.setText(request.getMutualConnections() + " mutual connections");

        if (request.getUser().getAvatarResId() != 0) {
            holder.avatar.setImageResource(request.getUser().getAvatarResId());
        }

        holder.acceptBtn.setOnClickListener(v -> {
            if (listener != null) listener.onAccept(request);
        });

        holder.declineBtn.setOnClickListener(v -> {
            if (listener != null) listener.onDecline(request);
        });
    }

    @Override
    public int getItemCount() {
        return requests.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView avatar;
        TextView name, username, mutual, acceptBtn, declineBtn;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.request_avatar);
            name = itemView.findViewById(R.id.request_name);
            username = itemView.findViewById(R.id.request_username);
            mutual = itemView.findViewById(R.id.request_mutual);
            acceptBtn = itemView.findViewById(R.id.btn_accept);
            declineBtn = itemView.findViewById(R.id.btn_decline);
        }
    }
}
