package com.example.application.views.exercise2;


import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class DataProviderHelper {

	private final static int numItems = 50;

	public static ListDataProvider<StockItem> createProductDataProvider() {

		final ProductBeanCreator creator = new ProductBeanCreator();
		final List<StockItem> list = new ArrayList<>();
		for (int i = 0; i < numItems; i++) {
			list.add(creator.createItem());
		}

		return DataProvider.ofCollection(list);
	}

	private abstract static class BeanCreator<T> {
		public abstract T createItem();
	}

	private static class ProductBeanCreator extends BeanCreator<StockItem> {

		private final Random rand = new Random(1001L);
		private final static String NAMES = "Knife Scythe Sickle Hammer Maul Screwdriver Saw Pliers Wrench";
		private final String[] productNames;

		public ProductBeanCreator() {
			productNames = NAMES.split(" ");
		}

		@Override
		public StockItem createItem() {
			final StockItem product = new StockItem();
			final LocalDate date = LocalDate.now().plusYears(rand.nextInt(2)).plusMonths(rand.nextInt(12) + 1).withDayOfMonth(rand.nextInt(25) + 1);
			product.setAvailable(date);
			product.setName(productNames[rand.nextInt(productNames.length - 1)]);
			product.setPrice(rand.nextInt(40));
			return product;
		}
	}
}
