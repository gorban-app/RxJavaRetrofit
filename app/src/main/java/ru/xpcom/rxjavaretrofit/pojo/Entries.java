package ru.xpcom.rxjavaretrofit.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Entries {

    @SerializedName("entries")
    public List<Entry> entryList;
    public static class Entry {
        @SerializedName("API")
        public String apiTitle;

        @SerializedName("Description")
        public String apiDescription;

        @SerializedName("Link")
        public String apiLink;

        @SerializedName("HTTPS")
        public boolean apiHttps;
    }
}
