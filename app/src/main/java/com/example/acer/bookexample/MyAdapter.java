package com.example.acer.bookexample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    ArrayList<BookData> bookData;

    public MyAdapter(MainActivity mainActivity, ArrayList<BookData> books) {
        this.context=mainActivity;
        this.bookData=books;

    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(context).inflate(R.layout.listitem,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        BookData data =bookData.get(position);
        holder.ittitle.setText(data.title);
        holder.itauthor.setText(data.author);
        Picasso.with(context).load(data.image).into(holder.itimage);
        holder.itdes.setText(data.description);
    }

    @Override
    public int getItemCount() {
        return (bookData==null) ? 0: bookData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView itimage;
        TextView itauthor,ittitle,itdes;
        public MyViewHolder(View itemView) {
            super(itemView);
           ittitle = itemView.findViewById(R.id.texttitle);
          itauthor=itemView.findViewById(R.id.textauthor);
           itdes=itemView.findViewById(R.id.txtdesc);
           itimage=itemView.findViewById(R.id.bookimage);
        }
    }
}
