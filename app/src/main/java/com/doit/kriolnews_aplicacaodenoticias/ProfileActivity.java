package com.doit.kriolnews_aplicacaodenoticias;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.doit.kriolnews_aplicacaodenoticias.model.Posts;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    private TextView username, email, created_at, postsNum;
    private Button btnEdit;
    private ListView myPostsListView;
    private List<String> posts;
    private ArrayAdapter<String> arrayAdapter;
    List<String> listTitle;


    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setTitle("Perfil");

        username = findViewById(R.id.profile_username);
        email = findViewById(R.id.profile_email);
        created_at = findViewById(R.id.profile_created);
        btnEdit = findViewById(R.id.profile_btn_edit);
        myPostsListView = findViewById(R.id.my_posts_list);
        postsNum = findViewById(R.id.pub_quantity);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        loadUserData();
        getMyPosts();
    }

    public void getMyPosts(){

        listTitle = new ArrayList<>();

        databaseReference.child("posts").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot obj : dataSnapshot.getChildren()){
                    Posts p = obj.getValue(Posts.class);

                    if(p.getUserId().equalsIgnoreCase(mUser.getUid())) {
                        listTitle.add(p.getTitle());
                    }
                }

                if(!listTitle.isEmpty()) {
                    arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, listTitle);
                    myPostsListView.setAdapter(arrayAdapter);
                }

                postsNum.setText(String.valueOf(listTitle.size()));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void loadUserData(){

        username.setText(mUser.getDisplayName());
        email.setText(mUser.getEmail());
        created_at.setText(String.valueOf(mUser.getMetadata().getCreationTimestamp()));
    }
}
