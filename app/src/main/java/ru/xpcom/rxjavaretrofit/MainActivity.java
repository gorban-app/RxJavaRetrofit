package ru.xpcom.rxjavaretrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import ru.xpcom.rxjavaretrofit.pojo.Entries;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerViewAdapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(recyclerViewAdapter);
        retrofit = RetrofitFactory.getRetrofit();
    }

    private  void getEntries() {
        ServiceAPI serviceAPI = retrofit.create(ServiceAPI.class);

        Observable<List<Entries>> btcObservable = serviceAPI.getEntries()
                .map(result -> Observable.fromIterable(result.ticker))
                .flatMap(x -> x).filter(y -> {
                    y.coinName = "btc";
                    return true;
                }).toList().toObservable();

    }
}