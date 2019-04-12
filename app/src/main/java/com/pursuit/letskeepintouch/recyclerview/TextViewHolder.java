package com.pursuit.letskeepintouch.recyclerview;

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
    void onBind(String textList) {
        textView.setText(textList);
        itemView.setOnClickListener(v -> fragmentInterface.moveToDetailFragment(textView.getText().toString()));
    }

}