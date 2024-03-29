package com.doit.kriolnews_aplicacaodenoticias.account;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.doit.kriolnews_aplicacaodenoticias.HomeActivity;
import com.doit.kriolnews_aplicacaodenoticias.R;
import com.doit.kriolnews_aplicacaodenoticias.Services.MessageService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity{

    private final String TAG = "Login EmailAndPassword";
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;

    private EditText email;
    private EditText password;
    private Button btn_entrar;
    private TextView btn_signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.txt_email);
        password  = findViewById(R.id.pwdf_password);
        btn_entrar = findViewById(R.id.btn_entrar);
        btn_signUp = findViewById(R.id.btn_signUp);

        mAuth = FirebaseAuth.getInstance();

        btn_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn(email.getText().toString(),password.getText().toString());

            }
        });

        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSignUpActivity();
            }
        });

    }

    private void signIn(String e, String p){

         if(!validateForm()){
            return;
        }

        progressDialog = ProgressDialog.show(LoginActivity.this,"Aguarde","A carregar...", true);

        mAuth.signInWithEmailAndPassword(e,p).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG,"signInWithEmail:success");
                    MessageService.toast(getApplicationContext(),"Seja Bem-Vindo " + mAuth.getCurrentUser().getDisplayName());
                    openHomeActivity();
                }else{
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                    MessageService.toast(getApplicationContext(),"Utilizador não encontrado.");
                }

                progressDialog.dismiss();

            }
        });
    }

    private void openHomeActivity(){
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
        this.finish();
    }
    private void openSignUpActivity(){

        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
        this.finish();
    }

    public boolean validateForm(){

        boolean valid = true;

        String email = this.email.getText().toString();
        if(TextUtils.isEmpty(email)){
            this.email.setError("Por favor informe o seu email.");
            valid = false;
        }else{
            this.email.setError(null);
        }

        String password = this.password.getText().toString();
        if(TextUtils.isEmpty(password)){
            this.password.setError("Por favor informe a sua senha.");
            valid = false;
        }

        return valid;

    }

}
