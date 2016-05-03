package com.kenshiro.sample_rxjava.gankio;

import java.io.IOException;

import com.kenshiro.sample_rxjava.gankio.Data.Result;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.functions.Action1;

public class App {
	public static void main(String[] args) throws IOException {
		
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("http://gank.avosapps.com/api/data/")
				.addConverterFactory(GsonConverterFactory.create())
				.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
				.build();
		
		GankService service = retrofit.create(GankService.class);
		
		Observable<Data> observable = service.list(20, 1);
		observable.subscribe(new Action1<Data>() {
			@Override
			public void call(Data t) {
				for (Result data: t.getResults()) {
					System.out.println(data.getUrl());
				}
			}
		});
	}
}
