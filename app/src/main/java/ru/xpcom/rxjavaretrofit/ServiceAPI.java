package ru.xpcom.rxjavaretrofit;

import io.reactivex.Observable;
import retrofit2.http.GET;
import ru.xpcom.rxjavaretrofit.pojo.Entries;

public interface ServiceAPI {

    @GET("entries")
    Observable<Entries> getEntries();
}
