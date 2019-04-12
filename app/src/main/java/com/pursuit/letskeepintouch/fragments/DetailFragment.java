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
import android.widget.TextView;
import android.widget.Toolbar;

import com.pursuit.letskeepintouch.R;

public class DetailFragment extends Fragment {

    private static final String GET_TEXT_FROM_CLICK = "getText";


    private Toolbar toolbarBar;
    private TextView textView;
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

        toolbarBar = view.findViewById(R.id.toolbar_scan);
        textView = view.findViewById(R.id.chosen_textView);
        textView.setText(getArguments().getString(GET_TEXT_FROM_CLICK));



    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_edits, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.edit_text) {
            editingItem();
        }
        if (id == R.id.delete_text) {
            deleteItem();
        }
        return super.onOptionsItemSelected(item);
    }


    private void showEditTextDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        dialog.setTitle(getResources().getString(R.string.select_image));

        String[] editItems = {"Edit", "Delete"};
        dialog.setItems(editItems, (DialogInterface dialog1, int which) -> {
            if (which == 0) { //edit
                editingItem();
            } else {
                if (which == 1) { //delete
                    deleteItem();
                }
            }
        });
        dialog.create().show();
    }

    private void editingItem(){
//        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
//            @Override
//            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//                return false;
//            }
//
//            @Override
//            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//                removeItem((long)viewHolder.itemView.getTag());
//            }
//        }).attachToRecyclerView(recyclerView);
    }

    private void deleteItem() {
    }

}
