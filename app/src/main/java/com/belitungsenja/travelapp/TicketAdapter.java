/*
 * Created by Triono Hidayat on 9/13/19 10:00 PM
 * Copyright © 2019 . All rights reserved.
 * Last modified 9/13/19 4:41 PM
 */

package com.belitungsenja.travelapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.MyViewHolder> {

    Context context;
    ArrayList<MyTicket> myTicket;

    public TicketAdapter (Context c, ArrayList<MyTicket> p){
        context = c;
        myTicket = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.item_ticket, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.xnama_paket.setText(myTicket.get(i).getNama_paket());
        myViewHolder.xdurasi_paket.setText(myTicket.get(i).getDurasi());
        myViewHolder.xjumlah_tamu.setText(myTicket.get(i).getJumlah_tamu());

        final String getNamaPaket = myTicket.get(i).getNama_paket();

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gototicketdetail = new Intent(context, Tiket.class);
                gototicketdetail.putExtra("nama_paket",getNamaPaket);
                context.startActivity(gototicketdetail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myTicket.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView xnama_paket, xdurasi_paket, xjumlah_tamu;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            xnama_paket = itemView.findViewById(R.id.xnama_paket);
            xdurasi_paket = itemView.findViewById(R.id.xdurasi_paket);
            xjumlah_tamu = itemView.findViewById(R.id.xjumlah_tamu);
        }
    }
}
