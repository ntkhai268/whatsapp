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

public class GroupMemberAdapter extends RecyclerView.Adapter<GroupMemberAdapter.ViewHolder> {

    private List<User> members = new ArrayList<>();
    private String adminId = ""; // First member is admin by default

    public void setMembers(List<User> members) {
        this.members = members;
        if (!members.isEmpty()) {
            this.adminId = members.get(0).getId();
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_group_member, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User member = members.get(position);

        holder.name.setText(member.getFullName());

        if (member.getId().equals(adminId)) {
            holder.role.setText("Admin");
            holder.role.setVisibility(View.VISIBLE);
        } else {
            holder.role.setText("Member");
            holder.role.setVisibility(View.VISIBLE);
        }

        if (member.getAvatarResId() != 0) {
            holder.avatar.setImageResource(member.getAvatarResId());
        }
    }

    @Override
    public int getItemCount() {
        return members.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView avatar;
        TextView name, role;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.member_avatar);
            name = itemView.findViewById(R.id.member_name);
            role = itemView.findViewById(R.id.member_role);
        }
    }
}
