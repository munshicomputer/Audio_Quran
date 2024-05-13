package com.munshisoft.quranplayer.model;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RecitationResponse {
    @SerializedName("recitations")
    private List<Recitation> recitations;

    public List<Recitation> getRecitations() {
        return recitations;
    }

    public class Recitation {
        private int id;
        @SerializedName("reciter_name")
        private String reciterName;
        private String style;
        @SerializedName("translated_name")
        private TranslatedName translatedName;

        public int getId() {
            return id;
        }

        public String getReciterName() {
            return reciterName;
        }

        public String getStyle() {
            return style;
        }

        public TranslatedName getTranslatedName() {
            return translatedName;
        }
    }

    public class TranslatedName {
        private String name;
        @SerializedName("language_name")
        private String languageName;

        public String getName() {
            return name;
        }

        public String getLanguageName() {
            return languageName;
        }
    }
}