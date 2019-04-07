package com.pursuit.letskeepintouch.fragments;

import androidx.fragment.app.Fragment;

public interface FragmentInterface {
    //void showSplashScreen();
    void moveToScanningFragment();

    void moveToDisplayFragment();

    void finishSplashScreen(Fragment fragment);

    void closeFragment(Fragment fragment);
}
