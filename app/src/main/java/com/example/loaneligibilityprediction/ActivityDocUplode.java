package com.example.loaneligibilityprediction;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.net.URI;

public class ActivityDocUplode extends AppCompatActivity {
    private Uri filePath;


    private ImageView panIV;
    private Button pancardBtn;
    private ProgressBar panProgressbar;


    private ImageView aadharIV;
    private Button aadharBtn;
    private ProgressBar aadharProgressbar;

    private ImageView elecIV;
    private Button elecBtn;
    private ProgressBar elecProgressbar;

    private ImageView voterIV;
    private Button voterBtn;
    private ProgressBar voterProgressbar;

    private ImageView passportIV;
    private Button passportBtn;
    private ProgressBar passProgressbar;


    private DatabaseReference root= FirebaseDatabase.getInstance().getReference("Image");
    private StorageReference reference= FirebaseStorage.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityuplode);


        panIV = findViewById(R.id.panIMG);
        pancardBtn = findViewById(R.id.pancardBT);
        panProgressbar = findViewById(R.id.PanProgress);
        panProgressbar.setVisibility(View.INVISIBLE);

        aadharIV = findViewById(R.id.aadharIMG);
        aadharBtn = findViewById(R.id.aadharBT);
        aadharProgressbar = findViewById(R.id.aadharProgress);
        aadharProgressbar.setVisibility(View.INVISIBLE);


        elecIV = findViewById(R.id.electricityIMG);
        elecBtn = findViewById(R.id.electricityBT);
        elecProgressbar = findViewById(R.id.electricityProgress);
        elecProgressbar.setVisibility(View.INVISIBLE);

        voterIV = findViewById(R.id.voterIMG);
        voterBtn = findViewById(R.id.voterBT);
        voterProgressbar = findViewById(R.id.voterProgress);
        voterProgressbar.setVisibility(View.INVISIBLE);

        passportIV = findViewById(R.id.passportIMG);
        passportBtn = findViewById(R.id.passportBT);
        passProgressbar = findViewById(R.id.passportProgress);
        passProgressbar.setVisibility(View.INVISIBLE);

        panIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent panIntent = new Intent();
                panIntent.setAction(Intent.ACTION_GET_CONTENT);
                panIntent.setType("imges/*");
                startActivityForResult(panIntent, 2);

            }
        });

        pancardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (filePath != null) {
                    uplodeToFirebase(filePath);
                } else {
                    Toast.makeText(ActivityDocUplode.this, "please select image", Toast.LENGTH_SHORT).show();
                }
            }
        });


        aadharIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent panIntent = new Intent();
                panIntent.setAction(Intent.ACTION_GET_CONTENT);
                panIntent.setType("imges/*");
                startActivityForResult(panIntent, 2);

            }
        });

        aadharBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (filePath != null) {
                    uplodeToFirebase(filePath);
                } else {
                    Toast.makeText(ActivityDocUplode.this, "please select image", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==2 && resultCode==RESULT_OK && data != null);{
            filePath=data.getData();
            panIV.setImageURI(filePath);

        }
    }

    private void uplodeToFirebase(Uri uri){
     StorageReference fileref= reference.child(System.currentTimeMillis()+ "." + getFileExtension(uri));
     fileref.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
         @Override
         public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
             fileref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                 @Override
                 public void onSuccess(Uri uri) {
                     ImageurlModel model=new ImageurlModel(uri.toString());
                     String modelID=root.push().getKey();
                     root.child(modelID).setValue(model);
                     Toast.makeText(ActivityDocUplode.this, "uplode succesfully", Toast.LENGTH_SHORT).show();
                 }
             });

         }
     }) .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
         @Override
         public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
             panProgressbar.setVisibility(View.VISIBLE);
             aadharProgressbar.setVisibility(View.INVISIBLE);  //////////////////////

         }
     }).addOnFailureListener(new OnFailureListener() {
         @Override
         public void onFailure(@NonNull Exception e) {
             panProgressbar.setVisibility(View.VISIBLE);
             aadharProgressbar.setVisibility(View.VISIBLE);  /////////////////////////////

             Toast.makeText(ActivityDocUplode.this, "uploding failed", Toast.LENGTH_SHORT).show();
         }
     });

    }
    private String   getFileExtension(Uri muri){

        ContentResolver cr =getContentResolver();
        MimeTypeMap mime= MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(muri ));

    }
}





