package com.pursuit.letskeepintouch.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pursuit.letskeepintouch.R;
import com.pursuit.letskeepintouch.database.TextDatabase;
import com.pursuit.letskeepintouch.recyclerview.TextAdapter;

import java.util.List;

public class DisplayFragment extends Fragment {

    private RecyclerView recyclerView;
    private TextView textView;
    private TextAdapter textAdapter;
    private FragmentInterface fragmentInterface;


    public DisplayFragment() {
    }

    public static DisplayFragment newInstance() {
        DisplayFragment fragment = new DisplayFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentInterface) {
            fragmentInterface = (FragmentInterface) context;
        } else {
            throw new RuntimeException(context.toString() + "must implement FragmentInterface");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_display, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textView = view.findViewById(R.id.cropped_textView);
        recyclerView = view.findViewById(R.id.text_recyclerview);
        settingRecyclerView(view);
    }

    private void settingRecyclerView(View view) {
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        List<String> textLists = TextDatabase.getTextList();
        textAdapter = new TextAdapter(textLists, fragmentInterface);
        recyclerView.setAdapter(textAdapter);
    }

    @Override
    public void onDetach() {
        fragmentInterface = null;
        super.onDetach();
    }

}
