package com.java.ds.concurrency.reactivejava;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;

public class ObserverExample{

	public static void main(String[] args) {
		Observable<Stock> observable = getObserver();

		observable
		.filter(each -> each.getStockname().equalsIgnoreCase("google"))
		.map(each -> {
			each.setStockValue(each.getStockValue() + 1234);
			return each;
		})
		.subscribe(stock -> System.out.println(stock.toString()),
				System.out::println);
	}

	private static Observable<Stock> getObserver() {
		List<String> listOfCompanies = Arrays.asList("google", "facebook", "amazon");
		return Observable.create(emitter -> getStockValues(emitter, listOfCompanies));
	}

	private static void getStockValues(ObservableEmitter<Stock> emitter, List<String> listOfCompanies) {
		listOfCompanies.stream()
		.map(each -> new Stock(each, 1234))
		.forEach(emitter::onNext);
		emitter.onComplete();
	}

	private static void sleep(long i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			System.out.println("error occured");
		}
	}
}

class Stock {
	private String stockname;
	private int stockValue;

	public Stock(String stockname, int stockValue) {
		this.stockname = stockname;
		this.stockname = stockname;
		this.stockValue = stockValue;
		//throw new RuntimeException("ooopps!!");
	}

	public String getStockname() {
		return stockname;
	}

	public void setStockname(String stockname) {
		this.stockname = stockname;
	}

	public int getStockValue() {
		return stockValue;
	}

	public void setStockValue(int stockValue) {
		this.stockValue = stockValue;
	}

	@Override
	public String toString() {
		return "Stock{" +
				"stockname='" + stockname + '\'' +
				", stockValue=" + stockValue +
				'}';
	}
}