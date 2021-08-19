package ru.xpcom.rxjavaretrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;


import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import ru.xpcom.rxjavaretrofit.pojo.Entries;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MAIN";

    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        retrofit = RetrofitFactory.getRetrofit();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAdapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(recyclerViewAdapter);


        getEntries();
    }

    private  void getEntries() {
        ServiceAPI serviceAPI = retrofit.create(ServiceAPI.class);

        //Single call
        Observable<Entries> cryptoObservable = serviceAPI.getEntries();
        cryptoObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(result -> result.entryList)
                .subscribe(this::handleResults, this::handleError);

        // Two call
/*        Observable<List<Entries.Entry>> btcObservable = serviceAPI.getEntries()
                .map(result -> Observable.fromIterable(result.entryList))
                .flatMap(x -> x).filter(y -> {
                    //y.coinName = "btc";
                    return true;
                }).toList().toObservable();

        Observable<List<Entries.Entry>> ethObservable = serviceAPI.getEntries()
                .map(result -> Observable.fromIterable(result.entryList))
                .flatMap(x -> x).filter(y -> {
                    //y.coinName = "eth";
                    return true;
                }).toList().toObservable();

        Observable.merge(btcObservable, ethObservable)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResults, this::handleError);*/
    }
    

    private void handleResults(List<Entries.Entry> marketList) {
        if (marketList != null && marketList.size() != 0) {
            recyclerViewAdapter.setData(marketList);
        } else {
            Toast.makeText(this, "NO RESULTS FOUND",
                    Toast.LENGTH_LONG).show();
        }
    }

    private void handleError(Throwable t) {
        Toast.makeText(this, "ERROR IN FETCHING API RESPONSE. Try again",
                Toast.LENGTH_LONG).show();
    }
}