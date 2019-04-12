package com.pursuit.letskeepintouch.fragments;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toolbar;

import com.pursuit.letskeepintouch.R;
import com.pursuit.letskeepintouch.database.TextDatabase;

public class DetailFragment extends Fragment {

    private static final String GET_TEXT_FROM_CLICK = "getText";


    private EditText editText;
    private Button saveButton;
    private Button deleteButton;
    private FragmentInterface fragmentInterface;

    private String getText;

    public DetailFragment() {
        // Required empty public constructor
    }

    public static DetailFragment newInstance(String getText) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString(GET_TEXT_FROM_CLICK, getText);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            getText = getArguments().getString(GET_TEXT_FROM_CLICK);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof FragmentInterface) {
            fragmentInterface = (FragmentInterface) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);

        editText = view.findViewById(R.id.chosen_textView);
        saveButton = view.findViewById(R.id.save_editText);
        deleteButton = view.findViewById(R.id.delete_text);

        editText.setText(getArguments().getString(GET_TEXT_FROM_CLICK));

        editingItem();
        deleteItem();
    }

    private void editingItem(){
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String newText = editText.getText().toString();
                TextDatabase.getInstance().addText(newText);
            }
        });
    }

    private void deleteItem() {
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String thisText = editText.getText().toString();
                TextDatabase.getInstance().deleteText(thisText);
            }
        });
    }

}
