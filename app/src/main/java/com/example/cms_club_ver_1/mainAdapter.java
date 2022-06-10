package com.example.cms_club_ver_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class mainAdapter extends RecyclerView.Adapter<mainAdapter.MyViewHolder> {

    ArrayList<main> list;
    OnMainBoardRowClickListener listener;

    public mainAdapter(ArrayList<main> arrayList, OnMainBoardRowClickListener listener)
    {
        this.list = arrayList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.main_board_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        main current=list.get(position);
        holder.name.setText(list.get(position).getName());
        holder.position.setText(list.get(position).getPosition());
        Glide.with(holder.img.getContext()).load(current.getPhoto()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void filterList(ArrayList<main> filteredlist) {
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView name,position;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.profile);
            name = itemView.findViewById(R.id.txt_name);
            position = itemView.findViewById(R.id.txt_role);
        }
    }
}
