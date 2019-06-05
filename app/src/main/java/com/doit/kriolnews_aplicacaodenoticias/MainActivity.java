package com.doit.kriolnews_aplicacaodenoticias;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.doit.kriolnews_aplicacaodenoticias.account.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private TextView logedInUSer;
    private Button logout;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logedInUSer = findViewById(R.id.login_user);
        logout = findViewById(R.id.btn_logout);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        logedInUSer.setText(currentUser.getDisplayName());

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Intent loginIntent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(loginIntent);
            }
        });


    }
}
