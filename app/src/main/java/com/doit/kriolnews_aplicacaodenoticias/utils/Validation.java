package com.doit.kriolnews_aplicacaodenoticias.utils;

import android.text.TextUtils;
import android.widget.EditText;

public class Validation {


    public boolean validateLoginForm(EditText e, EditText p){

        boolean valid = true;

        String email = e.getText().toString();
        if(TextUtils.isEmpty(email)){
            e.setError("Por favor informe o seu email.");
            valid = false;
        }else{
            e.setError(null);
        }

        String password = p.getText().toString();
        if(TextUtils.isEmpty(password)){
            p.setError("Por favor informe a sua senha.");
            valid = false;
        }

        return valid;

    }
}
