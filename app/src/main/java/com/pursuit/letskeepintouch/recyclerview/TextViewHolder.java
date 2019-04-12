package com.pursuit.letskeepintouch.recyclerview;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.pursuit.letskeepintouch.R;
import com.pursuit.letskeepintouch.fragments.FragmentInterface;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TextViewHolder extends RecyclerView.ViewHolder {
    private TextView textView;
    private FragmentInterface fragmentInterface;

    public TextViewHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.cropped_textView);

    }
    public void onBind(String textList, FragmentInterface fragmentInterface) {
        textView.setText(textList);
        this.fragmentInterface = fragmentInterface;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("You clicked", getAdapterPosition() + textList);
                fragmentInterface.moveToDetailFragment(textList);
            }
        });
    }

}