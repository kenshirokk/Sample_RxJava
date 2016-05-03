package com.kenshiro.sample_rxjava;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

public class FlatMap {

	public static void main(String[] args) {
//		query1();
//		queryMap();
//		queryFlatMap();
		String url = "http://www.douyutv.com/room/follow";
		getTitle(url).subscribe(new Action1<String>() {

			@Override
			public void call(String s) {
				System.out.println(s);
			}
		});
	}

	private static void query1() {
		query("")
			.subscribe(new Action1<List<String>>() {
				@Override
				public void call(List<String> list) {
					for (String s : list) {
						System.out.println(s);
					}
				}
			});
	}
	
	public static void queryMap() {
		query("")
			.subscribe(new Action1<List<String>>() {

				@Override
				public void call(List<String> list) {
					Observable.from(list)
						.map(new Func1<String, String>() {
							@Override
							public String call(String s) {
								return "map " + s;
							}
							
						})
						.subscribe(new Action1<String>() {
							@Override
							public void call(String s) {
								System.out.println(s);
							}
						});
				}
			});
	}
	
	public static void queryFlatMap() {
		query("")
			.flatMap(new Func1<List<String>, Observable<? extends String>>() {
				@Override
				public Observable<? extends String> call(List<String> list) {
					return Observable.from(list);
				}
			})
			.subscribe(new Action1<String>() {
				@Override
				public void call(String s) {
					System.out.println(s);
				}
			});
	}
	
	public static void flatMap(String...names) {
		Observable.from(names)
			.flatMap(new Func1<String, Observable<? extends String>>() {

				@Override
				public Observable<? extends String> call(String s) {
					return null;
				}
			});
	}
	
	public static Observable<List<String>> query(String text) {
		return Observable.create(new OnSubscribe<List<String>>() {
			@Override
			public void call(Subscriber<? super List<String>> sub) {
				List<String> list = new ArrayList<>();
				list.add("kenshiro");
				list.add("lijunhe");
				list.add("ken");
				sub.onNext(list);
			}
		});
	}
	
	public static Observable<String> getTitle(String url) {
		return Observable.create(new OnSubscribe<String>() {
			@Override
			public void call(Subscriber<? super String> sub) {
				String title = null;
				try {
					Document doc = Jsoup.connect(url).get();
					title = doc.title();
				} catch (IOException e) {
					e.printStackTrace();
				}
				sub.onNext(title);
			}
		});
	}
}
