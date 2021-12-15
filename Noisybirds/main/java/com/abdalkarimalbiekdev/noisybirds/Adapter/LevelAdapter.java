package com.abdalkarimalbiekdev.noisybirds.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.abdalkarimalbiekdev.noisybirds.GameActivity;
import com.abdalkarimalbiekdev.noisybirds.GameActivity2;
import com.abdalkarimalbiekdev.noisybirds.GameActivity3;
import com.abdalkarimalbiekdev.noisybirds.GameActivity4;
import com.abdalkarimalbiekdev.noisybirds.GameActivity5;
import com.abdalkarimalbiekdev.noisybirds.GameView2;
import com.abdalkarimalbiekdev.noisybirds.GameView3;
import com.abdalkarimalbiekdev.noisybirds.GameView4;
import com.abdalkarimalbiekdev.noisybirds.GameView5;
import com.abdalkarimalbiekdev.noisybirds.Interface.IClickListener;
import com.abdalkarimalbiekdev.noisybirds.MainActivity;
import com.abdalkarimalbiekdev.noisybirds.Model.Level;
import com.abdalkarimalbiekdev.noisybirds.R;
import com.abdalkarimalbiekdev.noisybirds.ViewHolder.LevelViewHolder;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LevelAdapter extends RecyclerView.Adapter<LevelViewHolder> {

    Activity activity;
    List<Level> levels;

    public LevelAdapter(Activity activity, List<Level> levels) {
        this.activity = activity;
        this.levels = levels;
    }

    @NonNull
    @Override
    public LevelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.level_item , parent , false);
        return new LevelViewHolder(view , activity);
    }

    @Override
    public void onBindViewHolder(@NonNull final LevelViewHolder holder, int position) {

        holder.txtLevelName.setText(levels.get(position).getLevelName());

        if (levels.get(position).isOpen()){
            holder.card1.setCardBackgroundColor(activity.getResources().getColor(android.R.color.holo_red_light));
            holder.card2.setCardBackgroundColor(activity.getResources().getColor(android.R.color.holo_blue_dark));
            holder.card3.setCardBackgroundColor(activity.getResources().getColor(android.R.color.holo_green_dark));
        }
        else {
            holder.card1.setCardBackgroundColor(Color.parseColor("#D5000000"));
            holder.card2.setCardBackgroundColor(Color.parseColor("#D51E1E1E"));
            holder.card3.setCardBackgroundColor(Color.parseColor("#D5555555"));
        }

        holder.setClickListener(new IClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {

                switch (levels.get(position).getLevelNo()){
                    case 0:{
                        if (levels.get(position).isOpen())
                            activity.startActivity(new Intent(activity, GameActivity.class));
                        else
                            Toast.makeText(activity, "Oops , there is problem !", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case 1:{
                        if (levels.get(position).isOpen())
                            activity.startActivity(new Intent(activity, GameActivity2.class));
                        else
                            Toast.makeText(activity, "You need 50 score and 50 chicken !", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case 2:{
                        if (levels.get(position).isOpen())
                            activity.startActivity(new Intent(activity, GameActivity3.class));
                        else
                            Toast.makeText(activity, "You need 100 score and 100 chicken !", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case 3:{
                        if (levels.get(position).isOpen())
                            activity.startActivity(new Intent(activity, GameActivity4.class));
                        else
                            Toast.makeText(activity, "You need 150 score and 150 chicken !", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case 4:{
                        if (levels.get(position).isOpen())
                            activity.startActivity(new Intent(activity, GameActivity5.class));
                        else
                            Toast.makeText(activity, "You need 200 score and 200 chicken !", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }



            }
        });
    }

    @Override
    public int getItemCount() {
        return levels.size();
    }
}
