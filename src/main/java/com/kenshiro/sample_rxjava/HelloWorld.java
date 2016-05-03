package com.kenshiro.sample_rxjava;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

public class HelloWorld {

	public static void main(String[] args) {
		fromHello("kenshiro", "ken");
		fromHello("kk");
		fromHello("lijunhe");
		System.out.println("============");
		justHello("kenshiro");	
		justHello("lijunhe");	
		System.out.println("============");
		createHello("kenshiro");
	}
	
	public static void fromHello(String...names) {
		Observable.from(names)
		.map(new Func1<String, String>() {
			@Override
			public String call(String s) {
				return "from_hello " + s + "!";
			}
		})
		.subscribe(new Action1<String>() {
			@Override
			public void call(String s) {
				System.out.println(s);
			}
		});
	}
	
	public static void justHello(String name) {
		Observable.just(name)
		.map(new Func1<String, String>() {
			@Override
			public String call(String s) {
				return "just_hello " + s + "!";
			}
		})
		.subscribe(new Action1<String>() {
			@Override
			public void call(String s) {
				System.out.println(s);
			}
		});
	}
	
	public static void createHello(String name) {
		Observable<String> observable = Observable.create(new OnSubscribe<String>() {
			@Override
			public void call(Subscriber<? super String> sub) {
				sub.onNext(name);
				sub.onCompleted();
			}
		});
		
		observable.map(new Func1<String, String>() {
			@Override
			public String call(String s) {
				return "create_hello " + s + "!";
			}
		}).subscribe(new Subscriber<String>() {
			@Override
			public void onCompleted() {
				System.out.println("create_hello ===onCompleted");
			}

			@Override
			public void onError(Throwable e) {
				System.out.println("create_hello ===onError");
			}

			@Override
			public void onNext(String s) {
				System.out.println(s);
			}
		});
	}
}
