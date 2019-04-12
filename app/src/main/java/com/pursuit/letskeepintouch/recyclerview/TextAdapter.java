package com.pursuit.letskeepintouch.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.Toolbar;

import com.pursuit.letskeepintouch.R;
import com.pursuit.letskeepintouch.fragments.FragmentInterface;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TextAdapter extends RecyclerView.Adapter<TextViewHolder> {
    private List<String> textList;
    private FragmentInterface fragmentInterface;

    public TextAdapter(List<String> textLists, FragmentInterface fragmentInterface) {
        this.textList = textLists;
        this.fragmentInterface = fragmentInterface;
    }

    @NonNull
    @Override
    public TextViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TextViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.text_itemviews, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TextViewHolder holder, int position) {
        holder.onBind(textList.get(position), fragmentInterface);
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

