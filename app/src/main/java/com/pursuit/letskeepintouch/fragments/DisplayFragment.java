package com.pursuit.letskeepintouch.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.pursuit.letskeepintouch.R;
import com.pursuit.letskeepintouch.database.TextDatabase;
import com.pursuit.letskeepintouch.recyclerview.TextAdapter;

import java.util.List;

public class DisplayFragment extends Fragment {

    private RecyclerView recyclerView;
    private TextView textView;
    private Toolbar toolbarBar;
    private TextAdapter textAdapter;
    private TextDatabase database;
    private SharedPreferences sharedPreferences;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String seeCroppedText;
    private String mParam2;


    public DisplayFragment() {
    }

    public static DisplayFragment newInstance(String param1, String param2) {
        DisplayFragment fragment = new DisplayFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            seeCroppedText = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_display, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);

        textView = view.findViewById(R.id.cropped_textView);
        toolbarBar = view.findViewById(R.id.toolbar_scan);

//        sharedPreferences.getString(ScanningFragment.EDITTEXT_SHARED_PREFS, )
//        seeCroppedText = getString()
        recyclerView = view.findViewById(R.id.text_recyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        List<String> textLists = TextDatabase.getTextList();

        TextAdapter textAdapter = new TextAdapter(textLists);

        recyclerView.setAdapter(textAdapter);

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
            //edit the text
            Toast.makeText(getContext(), "GitHub", Toast.LENGTH_SHORT).show();

        }
        if (id == R.id.delete_text) {
            //delete the text
            Toast.makeText(getContext(), "LinkedIn", Toast.LENGTH_SHORT).show();
       }
        return super.onOptionsItemSelected(item);
    }

    private void showEditTextDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        dialog.setTitle(getResources().getString(R.string.select_image));

        String[] editItems = {"Edit", "Delete"};
        dialog.setItems(editItems, (DialogInterface dialog1, int which) -> {
            if (which == 0) { //edit
//                Uri gitUri = Uri.parse("https://github.com/KhaiSoe/CYOA_Pursuit_HW_SOE_KHAING");
//                Intent gitIntent = new Intent(Intent.ACTION_VIEW, gitUri);
//                startActivity(gitIntent);

            } else {
                if (which == 1) { //delete
//                    Uri linkedinUri = Uri.parse("https://www.linkedin.com/in/khaing-m-soe/");
//                    Intent linkedinIntent = new Intent(Intent.ACTION_VIEW, linkedinUri);
//                    startActivity(linkedinIntent);
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


    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
    }

}
