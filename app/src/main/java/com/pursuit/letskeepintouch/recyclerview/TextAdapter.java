package com.pursuit.letskeepintouch.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pursuit.letskeepintouch.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TextAdapter extends RecyclerView.Adapter<TextViewHolder> {
    private List<String> textList;

    public TextAdapter(List<String> textLists) {
        this.textList = textLists;
    }

    @NonNull
    @Override
    public TextViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TextViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.text_itemviews, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TextViewHolder holder, int position) {
        holder.onBind(textList.get(position));

    }

    @Override
    public int getItemCount() {
        return textList.size();
    }


    public void setData(List<String> newTextLists) {
        this.textList = newTextLists;
        notifyDataSetChanged();
    }


}

