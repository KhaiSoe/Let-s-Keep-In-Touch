package com.pursuit.letskeepintouch.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
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
public class DisplayFragment extends Fragment {

    private RecyclerView recyclerView;
    private TextView textView;
    private Toolbar toolbarBar;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

//    private OnFragmentInteractionListener mListener;

    public DisplayFragment() {
        // Required empty public constructor
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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_display, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        recyclerView = view.findViewById(R.id.text_recyclerview);
        toolbarBar = view.findViewById(R.id.toolbar_scan);
//        toolbarBar.inflateMenu(R.menu.menu_display);
//        toolbarBar.setTitle(getResources().getString(R.string.click_button_to_add_image));
//
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

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
    }
//
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }


//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

}
