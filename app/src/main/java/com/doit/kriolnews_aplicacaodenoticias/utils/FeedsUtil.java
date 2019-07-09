package com.doit.kriolnews_aplicacaodenoticias.utils;

public class FeedsUtil {

    private String text;

    public FeedsUtil(String text){
        this.text = text;
    }

    public String removeHtmlTags(){

        String newText = android.text.Html.fromHtml(text).toString();
        return newText;
    }
}
