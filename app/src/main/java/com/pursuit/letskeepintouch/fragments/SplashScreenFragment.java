package com.pursuit.letskeepintouch.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pursuit.letskeepintouch.R;

public class SplashScreenFragment extends Fragment {

    private FragmentInterface fragmentInterface;
    private static int SCREEN_TIME = 3000;


    public SplashScreenFragment() {
        // Required empty public constructor
    }

    public static SplashScreenFragment newInstance() {
        SplashScreenFragment fragment = new SplashScreenFragment();
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
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                fragmentInterface.moveToScanningFragment();
            }
        }, SCREEN_TIME);


    }



    @Override
    public void onDetach() {
        super.onDetach();
        fragmentInterface = null;
    }

}
