package com.doit.kriolnews_aplicacaodenoticias.account;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.doit.kriolnews_aplicacaodenoticias.R;
import com.doit.kriolnews_aplicacaodenoticias.Services.MessageService;
import com.doit.kriolnews_aplicacaodenoticias.utils.Validation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class SignUpActivity extends AppCompatActivity {

    private final String TAG = "SignUp EmailAndPassword";
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;

    private EditText username;
    private EditText email;
    private EditText password;
    private Button btn_registrar;
    private TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username = findViewById(R.id.username_input);
        email = findViewById(R.id.email_input);
        password = findViewById(R.id.password_input);
        btn_registrar = findViewById(R.id.btn_registrar);
        login = findViewById(R.id.tv_login);

        mAuth = FirebaseAuth.getInstance();

        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAccount(email.getText().toString(),password.getText().toString());
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLoginActivity();
            }
        });
    }


    private void createAccount(String e, String p){
        Log.d(TAG,"createAccount:"+email);
        if(!new Validation().validateEmailPasswordForm(email, password)){
            return;
        }

        progressDialog = ProgressDialog.show(SignUpActivity.this, "Por favor Aguarde", "Processando");

        mAuth.createUserWithEmailAndPassword(e,p)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Log.d(TAG,"createUserWithEmail:sucess");
                    FirebaseUser user = mAuth.getCurrentUser();
                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                            .setDisplayName(username.getText().toString())
                            .build();
                    user.updateProfile(profileUpdates);
                    MessageService.toast(getApplicationContext(), "Registado com sucesso.");
                }else {
                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                    MessageService.toast(getApplicationContext(),"Nao foi possivel efetuar o registro.");
                }

                progressDialog.dismiss();
            }
        });

    }

    private void openLoginActivity(){
        Intent intentLogin = new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(intentLogin);
    }

}
