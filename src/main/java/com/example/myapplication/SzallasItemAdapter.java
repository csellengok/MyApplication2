package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Locale;

public class SzallasItemAdapter extends RecyclerView.Adapter<SzallasItemAdapter.ViewHolder> implements Filterable {
    private ArrayList<SzallasItem> mSzallasItemsData = new ArrayList<>();
    private ArrayList<SzallasItem> mSzallasItemsDataAll = new ArrayList<>();
    private Context mcontext;
    private int lastposition = -1;


    SzallasItemAdapter(Context context, ArrayList<SzallasItem> itemsData){
        this.mSzallasItemsData = itemsData;
        this.mSzallasItemsDataAll = itemsData;
        this.mcontext = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mcontext).inflate(R.layout.list_szallas, parent, false));
    }

    @Override
    public void onBindViewHolder(SzallasItemAdapter.ViewHolder holder, int position) {
        SzallasItem currentItem = mSzallasItemsData.get(position);

        holder.bindTo(currentItem);

        if (holder.getAbsoluteAdapterPosition() > lastposition){
            Animation animation = AnimationUtils.loadAnimation(mcontext, R.anim.slide_in_row);
            holder.itemView.startAnimation(animation);
            lastposition = holder.getAbsoluteAdapterPosition();
        }else {
            Animation animation = AnimationUtils.loadAnimation(mcontext, R.anim.anim2);
            holder.itemView.startAnimation(animation);
            lastposition = holder.getAbsoluteAdapterPosition();
        }

    }


    @Override
    public int getItemCount() {
        return mSzallasItemsData.size();
    }

    @Override
    public Filter getFilter() {
        return szallasFilter;
    }

    private Filter szallasFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<SzallasItem> filteredList = new ArrayList<>();
            FilterResults results = new FilterResults();

            if (charSequence == null || charSequence.length() == 0){
                results.count = mSzallasItemsData.size();
                results.values = mSzallasItemsDataAll;

            } else {
                String filterpattern = charSequence.toString().toLowerCase().trim();

                for (SzallasItem item : mSzallasItemsDataAll){
                    if (item.getName().toLowerCase().contains(filterpattern)){
                        filteredList.add(item);
                    }
                }
                results.count = filteredList.size();
                results.values = filteredList;
            }


            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            mSzallasItemsData = (ArrayList)filterResults.values;
            notifyDataSetChanged();
        }
    };

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTitleText;
        private TextView mInfoText;
        private TextView mPriceText;
        private ImageView mItemImage;

        public ViewHolder(View itemView) {
            super(itemView);

            mTitleText = itemView.findViewById(R.id.itemTitle);
            mInfoText = itemView.findViewById(R.id.subTitle);
            mPriceText = itemView.findViewById(R.id.price);
            mItemImage = itemView.findViewById(R.id.itemImage);

            itemView.findViewById(R.id.add_to_cart).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("Activity", "Kosarhoz adva");
                    ((SzallasokActivity)mcontext).updateAlertIcon();

                }
            });
        }

        void bindTo(SzallasItem currentItem) {
            mTitleText.setText(currentItem.getName());
            mInfoText.setText(currentItem.getInfo());
            mPriceText.setText(currentItem.getPrice());

            Glide.with(mcontext).load(currentItem.getImageResource()).into(mItemImage);
        }
    }

}

