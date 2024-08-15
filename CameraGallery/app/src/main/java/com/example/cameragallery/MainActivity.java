package com.example.cameragallery;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.OrientationEventListener;
import android.view.Surface;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private static final String[] REQUIRED_PERMISSIONS = {
            android.Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO
    };

    private Database database = new Database();
    private Map<String, String> documentData = new HashMap<>();
    private ExecutorService cameraExecutor;
    private PreviewView viewFinder;
    private ImageView photo;

    private ImageCapture imageCapture;
    private CameraSelector cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA;

    // Launchers
    private final ActivityResultLauncher<String[]> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.RequestMultiplePermissions(),
            permissions -> {
                // Handle Permission granted/rejected
                boolean permissionGranted = true;
                for (Map.Entry<String, Boolean> entry : permissions.entrySet()) {
                    if (Arrays.asList(REQUIRED_PERMISSIONS).contains(entry.getKey()) && !entry.getValue()) {
                        permissionGranted = false;
                        break;
                    }
                }

                if (!permissionGranted) {
                    Toast.makeText(getApplicationContext(),"Permissão NEGADA",Toast.LENGTH_SHORT).show();
                } else {
                    startCamera();
                }
            });

    private final ActivityResultLauncher<Intent> resultLauncherGallery = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                Uri imageUri = result.getData().getData();

                if (imageUri != null) {
                    photo.setVisibility(View.VISIBLE);
                    photo.setImageURI(imageUri);
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Binding das Views
        cameraExecutor = Executors.newSingleThreadExecutor();
        viewFinder = findViewById(R.id.viewFinder);
        photo = findViewById(R.id.foto);
        
        // Request da permissão
        if (allPermissionsGranted()) {
            startCamera();
        } else {
            requestPermissions();
        }

        ImageButton bt = findViewById(R.id.lente);
        bt.setOnClickListener(v -> {
            if (cameraSelector == CameraSelector.DEFAULT_FRONT_CAMERA) {
                cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA;
            } else {
                cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA;
            }

            startCamera();
        });

        Button bt1 = findViewById(R.id.image_capture_button);
        bt1.setOnClickListener(v -> takePhoto());

        ImageButton buttonGalerry = findViewById(R.id.gallery);
        buttonGalerry.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            resultLauncherGallery.launch(intent);
        });

        Button bt2 = findViewById(R.id.video_capture_button);
        bt2.setOnClickListener(v -> {
            photo.setVisibility(View.VISIBLE);
            database.downloadPhoto(photo, Uri.parse(documentData.get("url")));
        });
    }

    private void takePhoto() {
        if (imageCapture == null) {
            return;
        }

        // definir nome e caminho para a imagem
        String imageName = "IMG_" + System.currentTimeMillis();
        ContentValues values = new ContentValues();

        // nome, tipo e local onde vamos salvar a imagem
        values.put(MediaStore.MediaColumns.DISPLAY_NAME, imageName);
        values.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg");
        values.put(MediaStore.MediaColumns.RELATIVE_PATH, "Pictures/CameraSalaF");

        // carregar imagem com as configurações
        ImageCapture.OutputFileOptions outputFileOptions = new ImageCapture.OutputFileOptions.Builder(
                getContentResolver(),
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                values
        ).build();

        // Orientação da imagem
        OrientationEventListener orientationEventListener = new OrientationEventListener(this) {
            @Override
            public void onOrientationChanged(int orientation) {
                int rotation;

                if (orientation >= 45 && orientation < 135) {
                    rotation = Surface.ROTATION_270;
                } else if (orientation >= 135 && orientation <= 224) {
                    rotation = Surface.ROTATION_180;
                } else if (orientation >= 225 && orientation <= 314) {
                    rotation = Surface.ROTATION_90;
                } else {
                    rotation = Surface.ROTATION_0;
                }

                imageCapture.setTargetRotation(rotation);

            }
        };
        orientationEventListener.enable();

        // salvar imagem
        imageCapture.takePicture(outputFileOptions, ContextCompat.getMainExecutor(this), new ImageCapture.OnImageSavedCallback() {
            @Override
            public void onImageSaved(@NonNull ImageCapture.OutputFileResults outputFileResults) {
                photo.setVisibility(View.VISIBLE);
                photo.setImageURI(outputFileResults.getSavedUri());
                Toast.makeText(MainActivity.this, "DEU GREEN", Toast.LENGTH_SHORT).show();
                database.uploadPhoto(MainActivity.this, photo, documentData);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        photo.setVisibility(View.GONE);
                    }
                }, 2000);
            }

            @Override
            public void onError(@NonNull ImageCaptureException exception) {
                Log.e("log", "deu ruim " + exception.getMessage());
            }
        });
    }

    private void startCamera() {
        ListenableFuture<ProcessCameraProvider> cameraProviderFuture = ProcessCameraProvider.getInstance(this);

        cameraProviderFuture.addListener(() -> {
            try {
                // Used to bind the lifecycle of cameras to the lifecycle owner
                ProcessCameraProvider cameraProvider = cameraProviderFuture.get();

                // Preview
                Preview preview = new Preview.Builder().build();
                preview.setSurfaceProvider(viewFinder.getSurfaceProvider());

                // ImageCapture
                imageCapture = new ImageCapture.Builder().build();

                try {
                    // Unbind use cases before rebinding
                    cameraProvider.unbindAll();

                    // Bind use cases to camera
                    cameraProvider.bindToLifecycle(
                            this,
                            cameraSelector,
                            preview,
                            imageCapture
                    );
                } catch (Exception exc) {
                    Log.e("camera", "Camera binding failed", exc);
                }

            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }, ContextCompat.getMainExecutor(this));
    }

    private boolean allPermissionsGranted() {
        for (String permission : REQUIRED_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }

        return true;
    }

    private void requestPermissions() {
        activityResultLauncher.launch(REQUIRED_PERMISSIONS);
    }
}