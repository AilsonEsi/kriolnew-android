package com.doit.kriolnews_aplicacaodenoticias.enumeracao;

public enum FeedsProviders {

    EXPRE("https://expressodasilhas.cv/rss"), ANACAO("https://anacao.cv/feed/"), CVM(""), OPAIS("https://opais.cv/feed/");

    private String text;

    FeedsProviders(String text){
        this.text = text;
    }

    public String getText(){
        return text;
    }

}
