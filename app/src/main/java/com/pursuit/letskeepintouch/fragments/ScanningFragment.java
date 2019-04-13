package com.pursuit.letskeepintouch.fragments;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.pursuit.letskeepintouch.R;
import com.pursuit.letskeepintouch.database.TextDatabase;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import static android.app.Activity.RESULT_OK;

public class ScanningFragment extends Fragment {

    private EditText editText;
    private TextView resultTextView;
    private TextView imageTextView;
    private ImageView croppedImageView;
    private String croppedTextString;
    private Button saveButton;
    private Button nextButton;
    private Uri imageUri;
    public static final int CAMERA_REQUEST_CODE = 200;
    public static final int STORAGE_REQUEST_CODE = 400;
    public static final int IMAGE_PICK_GALLERY_CODE = 1000;
    public static final int IMAGE_PICK_CAMERA_CODE = 1001;
    String cameraPermission[];
    String storagePermission[];
    private Toolbar toolbarBar;
    private FragmentInterface fragmentInterface;


    public ScanningFragment() {
    }

    public static ScanningFragment newInstance() {
        ScanningFragment fragment = new ScanningFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof FragmentInterface){
            fragmentInterface = (FragmentInterface) context;
        } else {
            throw new RuntimeException(context.toString() + "must implement FragmentInterface");
        }    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_scanning, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);

        resultTextView = view.findViewById(R.id.show_result);
        editText = view.findViewById(R.id.scanned_result);
        imageTextView = view.findViewById(R.id.show_image);
        croppedImageView = view.findViewById(R.id.scanned_image);
        toolbarBar = view.findViewById(R.id.toolbar_scan);
        saveButton = view.findViewById(R.id.save_editText);
        nextButton = view.findViewById(R.id.next_fragment);

        getPermission();
        addToDatabase();
        toNextFragment();
    }

    private void getPermission() {
        cameraPermission = new String[]{Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermission = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_scan, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.addImage) {

            showImageImportDialog();
        } else if (id == R.id.contact) {
            showContactImportDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    private void showImageImportDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        dialog.setTitle(getResources().getString(R.string.select_image));

        String[] items = {"Camera", "Gallery"};
        dialog.setItems(items, (DialogInterface dialog1, int which) -> {
            if (which == 0) {
                if (!checkCameraPermission()) {
                    requestCameraPermission();
                } else {
                    pickCamera();
                }
            }
            if (which == 1) {
                if (!checkStoragePermission()) {
                    requestStoragePermission();
                } else {
                    pickStorage();
                }
            }
        });
        dialog.create().show();
    }

    private void showContactImportDialog() {
        String[] contactItems = {"GitHub", "LinkedIn"};
        AlertDialog.Builder contactDialog = new AlertDialog.Builder(getContext());
        contactDialog.setTitle(getResources().getString(R.string.select_contact));

        contactDialog.setItems(contactItems, (DialogInterface dialog1, int which) -> {
            if (which == 0) {
                Uri gitUri = Uri.parse("https://github.com/KhaiSoe/CYOA_Pursuit_HW_SOE_KHAING");
                Intent gitIntent = new Intent(Intent.ACTION_VIEW, gitUri);
                startActivity(gitIntent);

            } else {
                if (which == 1) {
                    Uri linkedinUri = Uri.parse("https://www.linkedin.com/in/khaing-m-soe/");
                    Intent linkedinIntent = new Intent(Intent.ACTION_VIEW, linkedinUri);
                    startActivity(linkedinIntent);
                }
            }
        });
        contactDialog.create().show();

    }

    private void pickStorage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(getString(R.string.pickStorage_intent));
        startActivityForResult(intent, IMAGE_PICK_GALLERY_CODE);

    }

    private void pickCamera() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, getString(R.string.pic_title_camera));
        values.put(MediaStore.Images.Media.DESCRIPTION, getString(R.string.pic_descrption_camera));

        imageUri = getActivity().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(cameraIntent, IMAGE_PICK_CAMERA_CODE);
    }

    private void requestStoragePermission() {
        ActivityCompat.requestPermissions(getActivity(), storagePermission, STORAGE_REQUEST_CODE);

    }

    private boolean checkStoragePermission() {
        boolean result = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return result;
    }

    private void requestCameraPermission() {
        ActivityCompat.requestPermissions(getActivity(), cameraPermission, CAMERA_REQUEST_CODE);
    }

    private boolean checkCameraPermission() {
        boolean result = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) == (PackageManager.PERMISSION_GRANTED);
        boolean result1 = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return result && result1;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CAMERA_REQUEST_CODE: {
                if (grantResults.length > 0) {
                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean writeStorageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (cameraAccepted && writeStorageAccepted) {
                        pickCamera();
                    } else {
                        Toast.makeText(getActivity(), "Permission denied!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            break;
            case STORAGE_REQUEST_CODE: {
                if (grantResults.length > 0) {
                    boolean writeStorageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (writeStorageAccepted) {
                        pickStorage();
                    } else {
                        Toast.makeText(getActivity(), "Permission denied!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        startCropImage(requestCode, resultCode, imageUri);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                imageUri = result.getUri();
                croppedImageView.setImageURI(imageUri);
                getTextFromCroppingImages();
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
                Toast.makeText(getContext(), " " + error, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void startCropImage(int requestCode, int resultCode, Uri imageUri) {
        if (resultCode == RESULT_OK) {
            if (requestCode == IMAGE_PICK_GALLERY_CODE) {
                CropImage.activity(imageUri)
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .start(getContext(), this);

            }
            if (requestCode == IMAGE_PICK_CAMERA_CODE) {
                CropImage.activity(imageUri)
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .start(getContext(), this);
            }
        }
    }

    private void getTextFromCroppingImages() {
        BitmapDrawable bitmapDrawable = (BitmapDrawable) croppedImageView.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();
        TextRecognizer textRecognizer = new TextRecognizer.Builder(requireContext()).build();

        if (!textRecognizer.isOperational()) {
            Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
        } else {
            Frame frame = new Frame.Builder().setBitmap(bitmap).build();
            SparseArray<TextBlock> items = textRecognizer.detect(frame);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < items.size(); i++) {
                TextBlock myItems = items.valueAt(i);
                sb.append(myItems.getValue());
            }
            editText.append(sb.toString());
            editText.setText(sb.toString());
        }

    }

    private void addToDatabase() {

        saveButton.setOnClickListener(v -> {
            croppedTextString = editText.getText().toString();
            Log.e("croppedText: ", croppedTextString);
            if (croppedTextString.isEmpty()) {
                Toast.makeText(getActivity(), "Please enter something...", Toast.LENGTH_SHORT).show();
            } else {
                TextDatabase.getInstance().addText(croppedTextString);
                Log.e("TESTING: ", croppedTextString);
               fragmentInterface.moveToDisplayFragment();
            }
        });

    }

    private void toNextFragment(){
        nextButton.setOnClickListener(v -> fragmentInterface.moveToDisplayFragment());
    }


    @Override
    public void onDetach() {
        fragmentInterface = null;
        super.onDetach();
    }

}
