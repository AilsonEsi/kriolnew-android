package com.doit.kriolnews_aplicacaodenoticias.account;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.doit.kriolnews_aplicacaodenoticias.R;
import com.doit.kriolnews_aplicacaodenoticias.Services.Alert;

public class LoginActivity extends AppCompatActivity {

    private Button btn_entrar;

    public LoginActivity(){
        this.btn_entrar = findViewById(R.id.btn_entrar);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
