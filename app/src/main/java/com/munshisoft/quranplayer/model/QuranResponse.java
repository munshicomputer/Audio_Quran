package com.munshisoft.quranplayer.model;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuranResponse {
    @SerializedName("chapters")
    private List<QuranChapter> chapters;

    public List<QuranChapter> getChapters() {
        return chapters;
    }

    public static class QuranChapter {
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

        public int getId() {
            return id;
        }

        public String getRevelationPlace() {
            return revelationPlace;
        }

        public int getRevelationOrder() {
            return revelationOrder;
        }

        public boolean isBismillahPre() {
            return bismillahPre;
        }

        public String getNameSimple() {
            return nameSimple;
        }

        public String getNameComplex() {
            return nameComplex;
        }

        public String getNameArabic() {
            return nameArabic;
        }

        public int getVersesCount() {
            return versesCount;
        }

        public int[] getPages() {
            return pages;
        }

        public TranslatedName getTranslatedName() {
            return translatedName;
        }

        public static class TranslatedName {
            @SerializedName("language_name")
            private String languageName;

            @SerializedName("name")
            private String name;

            public String getLanguageName() {
                return languageName;
            }

            public String getName() {
                return name;
            }
        }
    }
}