package com.home.ubbs.photodiary.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.home.ubbs.photodiary.R;
import com.squareup.picasso.Picasso;

/**
 * Created by udyatbhanu-mac on 4/28/16.
 */
public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.ViewHolder> {

    Context context;
    public AlbumsAdapter() {
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_view_album_item, parent, false);


        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Context context = holder.itemView.getContext();
        Picasso.with(context).load(R.drawable.place_holder_image).fit().into(holder.albumCover);


    }

    @Override
    public int getItemCount() {
        return 45;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView albumCover;
        public TextView albumTitle;


        public ViewHolder(View itemView) {
            super(itemView);
            albumCover = (ImageView) itemView.findViewById(R.id.album_image_view);
            albumTitle = (TextView) itemView.findViewById(R.id.title_text_view);

        }
    }
}
