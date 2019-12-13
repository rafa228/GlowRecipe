package com.raflyjr.recipe;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;


class ListNewsAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<HashMap<String, String>> data;

    public ListNewsAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
        activity = a;
        data=d;
    }
    public int getCount() {
        return data.size();
    }
    public Object getItem(int position) {
        return position;
    }
    public long getItemId(int position) {
        return position;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        ListNewsViewHolder holder = null;
        if (convertView == null) {
            holder = new ListNewsViewHolder();
            convertView = LayoutInflater.from(activity).inflate(
                    R.layout.list_meals, parent, false);
            holder.strMealThumb = (ImageView) convertView.findViewById(R.id.galleryImage);
            holder.strMeal = (TextView) convertView.findViewById(R.id.meal);
            holder.strCategory = (TextView) convertView.findViewById(R.id.category);
            holder.strInstructions = (TextView) convertView.findViewById(R.id.instructions);
            convertView.setTag(holder);
        } else {
                holder = (ListNewsViewHolder) convertView.getTag();
            }
            holder.strMealThumb.setId(position);
            holder.strMeal.setId(position);
            holder.strCategory.setId(position);
            holder.strInstructions.setId(position);

            HashMap<String, String> song = new HashMap<String, String>();
            song = data.get(position);

            try{
                holder.strMeal.setText(song.get(Recipes.KEY_STRMEAL));
                holder.strCategory.setText(song.get(Recipes.KEY_STRCATEGORY));
                holder.strInstructions.setText(song.get(Recipes.KEY_STRINSTRUCTIONS));

                if(song.get(Recipes.KEY_STRMEALTHUMB).toString().length() < 5)
                {
                    holder.strMealThumb.setVisibility(View.GONE);
                }else{
                    Picasso.with(activity)
                            .load(song.get(Recipes.KEY_STRMEALTHUMB))
                            .resize(800, 500)
                            .centerCrop()
                            .into(holder.strMealThumb);
            }
        }catch(Exception e) {}
        return convertView;
    }
}

class ListNewsViewHolder {
    ImageView strMealThumb;
    TextView strMeal, strCategory, strInstructions;
}