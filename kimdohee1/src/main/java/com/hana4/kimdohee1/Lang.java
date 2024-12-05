package com.hana4.kimdohee1;

import lombok.Getter;

import java.util.Locale;

@Getter
public enum Lang {
    DEFULT("한국어", Locale.KOREAN),
    ENGLISH("English", Locale.ENGLISH),
    CHINESE("中國", Locale.CHINESE),
    GERMAN("Deutsch", Locale.GERMAN),
    ITALIAN("Italiano", Locale.ITALIAN),
    FRENCH("Français", Locale.FRENCH);

    private String lang;
    private Locale locale;
    Lang(String lang, Locale locale){
        this.lang = lang;
        this.locale = locale;
    }
}
