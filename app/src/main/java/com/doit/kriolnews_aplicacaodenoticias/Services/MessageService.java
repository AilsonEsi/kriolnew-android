package com.doit.kriolnews_aplicacaodenoticias.Services;

import android.content.Context;
import android.widget.Toast;

public class MessageService {

    public static void toast(Context context, String msg){

        Toast.makeText(context, msg,Toast.LENGTH_SHORT).show();

    }
}
