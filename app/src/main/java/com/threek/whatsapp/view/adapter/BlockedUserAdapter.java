package com.threek.whatsapp.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.threek.whatsapp.R;
import com.threek.whatsapp.model.User;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class BlockedUserAdapter extends RecyclerView.Adapter<BlockedUserAdapter.ViewHolder> {

    private List<User> blockedUsers = new ArrayList<>();

    public void setBlockedUsers(List<User> blockedUsers) {
        this.blockedUsers = blockedUsers;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_blocked_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = blockedUsers.get(position);

        holder.name.setText(user.getFullName());
        holder.username.setText("@" + user.getUsername());

        if (user.getAvatarResId() != 0) {
            holder.avatar.setImageResource(user.getAvatarResId());
        }
    }

    @Override
    public int getItemCount() {
        return blockedUsers.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView avatar;
        TextView name, username;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.blocked_avatar);
            name = itemView.findViewById(R.id.blocked_name);
            username = itemView.findViewById(R.id.blocked_username);
        }
    }
}
