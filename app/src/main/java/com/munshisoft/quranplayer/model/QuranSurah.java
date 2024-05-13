package com.munshisoft.quranplayer.model;
import com.google.gson.annotations.SerializedName;

public class QuranSurah {
    @SerializedName("id")
    private int id;

    @SerializedName("revelation_place")
    private String revelationPlace;

    @SerializedName("revelation_order")
    private int revelationOrder;

    @SerializedName("bismillah_pre")
    private boolean bismillahPre;

    @SerializedName("name_simple")
    private String nameSimple;

    @SerializedName("name_complex")
    private String nameComplex;

    @SerializedName("name_arabic")
    private String nameArabic;

    @SerializedName("verses_count")
    private int versesCount;

    @SerializedName("pages")
    private int[] pages;

    @SerializedName("translated_name")
    private TranslatedName translatedName;

    // Getter and Setter methods
    // You can generate them using your IDE or write them manually
    // For brevity, I'm omitting them here

    public static class TranslatedName {
        @SerializedName("language_name")
        private String languageName;

        @SerializedName("name")
        private String name;

        // Getter and Setter methods
        // You can generate them using your IDE or write them manually
        // For brevity, I'm omitting them here
    }
}
