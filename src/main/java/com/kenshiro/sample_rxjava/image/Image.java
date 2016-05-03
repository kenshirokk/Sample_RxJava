package com.kenshiro.sample_rxjava.image;

import java.io.File;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

public class Image {

	public static final String dir = "E:\\pic\\推女郎";
	
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		ob();
//		normal();
		System.out.println("cost time " + (System.currentTimeMillis() - start) + "ms");

	}
	
	private static void normal() {
		File f = new File(dir);
		File[] list = f.listFiles();

		for (File file : list) {
			File[] ff = file.listFiles();
			for (File f2 : ff) {
				if(f2.getName().endsWith(".jpg")) {
					System.out.println(f2.getName());
				}
			}
		}
	}

	private static void ob() {
		File f = new File(dir);
		File[] list = f.listFiles();

		Observable.from(list)
			.flatMap(new Func1<File, Observable<? extends File>>() {
				@Override
				public Observable<? extends File> call(File f) {
					return Observable.from(f.listFiles());
				}
			})
			.filter(new Func1<File, Boolean>() {
				@Override
				public Boolean call(File f) {
					return f.getName().endsWith(".jpg");
				}
			})
			.map(new Func1<File, String>() {
				@Override
				public String call(File f) {
					return f.getName();
				}
			})
			.subscribe(new Action1<String>() {

				@Override
				public void call(String s) {
					System.out.println(s);
				}
			});
	}
	
}
