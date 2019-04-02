package com.pursuit.letskeepintouch.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.pursuit.letskeepintouch.R;

public class ScanningFragment extends Fragment {

    private TextView textView;
    private ImageView imageView;
    private Button button;
    public static final int REQUESTCAMERAPERMISSION = 1001;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ScanningFragment() {
        // Required empty public constructor
    }

    public static ScanningFragment newInstance(String param1, String param2) {
        ScanningFragment fragment = new ScanningFragment();
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
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scanning, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageView = view.findViewById(R.id.card1);
        textView = view.findViewById(R.id.show_text);
        button = view.findViewById(R.id.scanning_button);
        getTextFromImage(view);
//
//        TextRecognizer textRecognizer = new TextRecognizer.Builder(getContext()).build();
//
//        if (!textRecognizer.isOperational()) {
//            Toast.makeText(getContext(), "Could not get the textView: ", Toast.LENGTH_SHORT).show();
//        } else {
//            cameraSource = new CameraSource.Builder(getContext(), textRecognizer)
//                    .setFacing(CameraSource.CAMERA_FACING_BACK)
//                    .setRequestedPreviewSize(1280, 1024)
//                    .setRequestedFps(2.0f)
//                    .setAutoFocusEnabled(true)
//                    .build();
//
//            cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
//                @Override
//                public void surfaceCreated(SurfaceHolder holder) {
//                    try {
//                        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//                            ActivityCompat.requestPermissions(getActivity(),
//                                    new String[]{Manifest.permission.CAMERA},
//                                    REQUESTCAMERAPERMISSION);
//                            return;
//
//                        }
//                        cameraSource.start(cameraView.getHolder());
//
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//
//                @Override
//                public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
//                }
//
//                @Override
//                public void surfaceDestroyed(SurfaceHolder holder) {
//                    cameraSource.stop();
//                }
//            });
//
//            textRecognizer.setProcessor(new Detector.Processor<TextBlock>() {
//                @Override
//                public void release() {
//                }
//
//                @Override
//                public void receiveDetections(Detector.Detections<TextBlock> detections) {
//                    final SparseArray<TextBlock> items = detections.getDetectedItems();
//                    if (items.size() != 0) {
//                        textView.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                StringBuilder stringBuilder = new StringBuilder();
//                                for (int i = 0; i < items.size(); ++i) {
//                                    TextBlock tB = items.valueAt(i);
//                                    stringBuilder.append(tB.getValue());
//                                }
//                                textView.setText(stringBuilder.toString());
//                            }
//                        });
//                    }
//                }
//            });

        }

    public void getTextFromImage(View v) {
        imageView.setImageResource(R.drawable.kitten1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.kitten1);
                TextRecognizer textRecognizer = new TextRecognizer.Builder(getContext()).build();

                if (!textRecognizer.isOperational()) {
                    Toast.makeText(getContext(), "Could not get the text: ", Toast.LENGTH_SHORT).show();
                } else {
                    Frame frame = new Frame.Builder().setBitmap(bitmap).build();
                    SparseArray<TextBlock> items = textRecognizer.detect(frame);
                    StringBuilder sb = new StringBuilder();

                    for (int i = 0; i < items.size(); i++) {
                        TextBlock myItem = items.valueAt(i);
                        sb.append(myItem.getValue());

                    }

                    textView.setText(sb.toString());
                }
            }
        });


    }

/*

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUESTCAMERAPERMISSION: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    try {
                        cameraSource.start(cameraView.getHolder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            break;
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        cameraSource.release();
    }
*/

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
