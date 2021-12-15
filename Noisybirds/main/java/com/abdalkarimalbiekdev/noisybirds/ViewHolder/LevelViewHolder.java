package com.abdalkarimalbiekdev.noisybirds.ViewHolder;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import com.abdalkarimalbiekdev.noisybirds.Interface.IClickListener;
import com.abdalkarimalbiekdev.noisybirds.R;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class LevelViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtLevelName;
    public CardView card1;
    public CardView card2;
    public CardView card3;

    private IClickListener clickListener;

    public void setClickListener(IClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public LevelViewHolder(@NonNull View itemView , Activity activity) {
        super(itemView);

        txtLevelName = itemView.findViewById(R.id.txtLevelName);
        card1 = itemView.findViewById(R.id.card1);
        card2 = itemView.findViewById(R.id.card2);
        card3 = itemView.findViewById(R.id.card3);

        Typeface typeface = Typeface.createFromAsset(activity.getAssets() , "fonts/giddyupstd.otf");
        txtLevelName.setTypeface(typeface);

        itemView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        clickListener.onClick(v , getAdapterPosition() , false);
    }
}
