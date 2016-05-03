package com.kenshiro.sample_rxjava.gankio;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface GankService {

	@GET("福利/{size}/{page}")
	Observable<Data> list(@Path("size") int size, @Path("page") int page);
}
