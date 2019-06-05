package com.doit.kriolnews_aplicacaodenoticias.account;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.doit.kriolnews_aplicacaodenoticias.R;

public class SignUpActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button btn_registrar;
    private TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        email = findViewById(R.id.email_input);
        password = findViewById(R.id.password_input);
        btn_registrar = findViewById(R.id.btn_registrar);
        login = findViewById(R.id.tv_login);
    }



    private void openLoginView(){
        Intent loginIntent = new Intent(getApplicationContext(),SignUpActivity.class);
        startActivity(loginIntent);
    }
}
