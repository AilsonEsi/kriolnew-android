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

import com.doit.kriolnews_aplicacaodenoticias.InitActivity;
import com.doit.kriolnews_aplicacaodenoticias.MainActivity;
import com.doit.kriolnews_aplicacaodenoticias.R;
import com.doit.kriolnews_aplicacaodenoticias.Services.MessageService;
import com.doit.kriolnews_aplicacaodenoticias.utils.Validation;
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

    @Override
    public void onStart() {
        super.onStart();

        // Check if user is signed in (non-null) and update UI accordingly.
        //FirebaseUser currentUser = mAuth.getCurrentUser();

    }

    private void signIn(String e, String p){

         if(!new Validation().validateEmailPasswordForm(email,password)){
            return;
        }

        progressDialog = ProgressDialog.show(LoginActivity.this,"Aguarde","A carregar", true);

        mAuth.signInWithEmailAndPassword(e,p).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG,"signInWithEmail:success");
                    MessageService.toast(getApplicationContext(),"Seja Bem Vindo " + mAuth.getCurrentUser().getDisplayName());
                    openMainActivity();
                }else{
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                    MessageService.toast(getApplicationContext(),"Utilizador nao encontrado.");
                }

                progressDialog.hide();

            }
        });
    }

    private void openMainActivity(){
        Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(mainIntent);
    }
    private void openSignUpActivity(){

        Intent intentSignUp = new Intent(getApplicationContext(), SignUpActivity.class);
        startActivity(intentSignUp);
    }


}
