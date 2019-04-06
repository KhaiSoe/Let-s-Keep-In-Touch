package com.pursuit.letskeepintouch;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.pursuit.letskeepintouch.fragments.DisplayFragment;
import com.pursuit.letskeepintouch.fragments.FragmentInterface;
import com.pursuit.letskeepintouch.fragments.ScanningFragment;
import com.pursuit.letskeepintouch.fragments.SplashScreenFragment;

public class MainActivity extends AppCompatActivity implements FragmentInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moveToScanningFragment();
    }

//    @Override
//    public void showSplashScreen() {
//        SplashScreenFragment splashScreenFragment = SplashScreenFragment.newInstance();
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.fragment_container, splashScreenFragment)
//                .addToBackStack(null)
//                .commit();
//    }

    @Override
    public void moveToScanningFragment() {
        ScanningFragment scanningFragment = ScanningFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, scanningFragment)
                .addToBackStack(null)
                .commit();

    }


    @Override
    public void moveToDisplayFragment() {
        DisplayFragment displayFragment = DisplayFragment.newInstance("a", "b");
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, displayFragment)
                .addToBackStack(null)
                .commit();
    }
}
