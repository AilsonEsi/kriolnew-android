package com.doit.kriolnews_aplicacaodenoticias.enumeracao;

public enum FeedsProviders {

    EXPRE("https://expressodasilhas.cv/rss"),
    ANACAO("https://anacao.cv/feed/"),
    OPAIS("https://opais.cv/feed/"),
    JE("https://jornaleconomico.sapo.pt/feed"),
    CE("https://www.criolosports.com/index.php?format=feed&type=rss"),
    SMA("https://www.santiagomagazine.cv/index.php?format=feed&type=rss");

    private String text;

    FeedsProviders(String text){
        this.text = text;
    }

    public String getText(){
        return text;
    }

}
