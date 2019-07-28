package com.digitalent.simplenotes.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.digitalent.simplenotes.R;
import com.digitalent.simplenotes.data.Catatan;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MyViewHolder> {
    List<Catatan> catatanList = new ArrayList<>();

    public NoteAdapter(List<Catatan> catatanList) {
        this.catatanList = catatanList;
    }


    // Untuk kebutuhan onClick pada item recycler view
    private onCatatanClickListener listener;

    public interface onCatatanClickListener{
        public void onClick(View view, int position);
    }

    public void setListener(onCatatanClickListener listener){
        this.listener = listener;
    }
    // Ending Code - Untuk kebutuhan onClick pada item recycler view

    @NonNull
    @Override
    public NoteAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_note, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.MyViewHolder holder, int position) {
        Catatan catatan = catatanList.get(position);

        holder.titleCatatan.setText(catatan.getJudul());

        // dapatkan id catatan, namun tidak di tampilkan dalam tampilan
        holder.idCatatan.setText(String.valueOf(catatan.getId()));
    }

    @Override
    public int getItemCount() {
        return catatanList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView titleCatatan;
        public TextView idCatatan;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            titleCatatan = itemView.findViewById(R.id.tv_item_title);
            idCatatan = itemView.findViewById(R.id.tv_item_id);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(view, getAdapterPosition());
                }
            });
        }
    }
}
