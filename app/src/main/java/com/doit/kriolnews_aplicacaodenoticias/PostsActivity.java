package com.doit.kriolnews_aplicacaodenoticias;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.doit.kriolnews_aplicacaodenoticias.model.Posts;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class PostsActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;


    private Button btnLoader, btnPublish;
    private ImageView imgThumb;
    private EditText title;
    private EditText description;
    private EditText category;
    private EditText content;



    private Uri uriImagem;

    private DatabaseReference databaseReference;
    private StorageReference storageReference;
    private FirebaseAuth mAuth;
    private StorageTask     mUploadTask;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);
        setTitle("Criar Conteudos");

        btnLoader = findViewById(R.id.btn_loader);
        imgThumb = findViewById(R.id.img_thumb);
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        category = findViewById(R.id.category);
        content = findViewById(R.id.content);
        btnPublish = findViewById(R.id.btn_publish);

        storageReference = FirebaseStorage.getInstance().getReference("posts");
        //databaseReference = FirebaseDatabase.getInstance().getReference("posts");


        btnLoader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });

        btnPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                publish();
            }
        });
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            uriImagem = data.getData();
            Picasso.with(getApplicationContext()).load(uriImagem).into(imgThumb);
        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver contentResolver = getApplicationContext().getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private void publish() {

        if (uriImagem != null) {
            StorageReference fileReference = storageReference.child(System.currentTimeMillis()
                    + "." + getFileExtension(uriImagem));

            mUploadTask = fileReference.putFile(uriImagem)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            //String postNewsId = databaseReference.push().getKey();

                           /* databaseReference.child(postNewsId).setValue(
                                    new Posts(title.getText().toString(),
                                            description.getText().toString(),
                                            category.getText().toString(),
                                            content.getText().toString(),
                                            storageReference.getDownloadUrl().toString())
                            );
                            */
                            Toast.makeText(getApplicationContext().getApplicationContext(), "Upload successful", Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext().getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

        }else {
            Toast.makeText(getApplicationContext().getApplicationContext(), "Imagem não selecionada", Toast.LENGTH_SHORT).show();
        }
    }


}
