package com.example.application.views.exercise2;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.LocalDateRenderer;

import java.time.LocalDate;

@SuppressWarnings("serial")
public class FilteringDataProvider extends Composite<VerticalLayout> {

	public static final String ROUTE = "ex2";
	public static final String TITLE = "Exercise 2";

	private final ListDataProvider<StockItem> dataProvider;

	public FilteringDataProvider() {
		final VerticalLayout layout = getContent();
		layout.setWidth("100%");

		dataProvider = DataProviderHelper.createProductDataProvider();

		// create layout for DateFields
		final DatePicker fromField = new DatePicker("Start");
		final DatePicker toField = new DatePicker("End");
		final Button filter = new Button("Filter");
		final HorizontalLayout filters = new HorizontalLayout(fromField, toField, filter);
		filters.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.END);

		filter.addClickListener(e -> {

			// setFilter will clear any previous filter
			//dataProvider.setFilter(StockItem::getAvailable, available -> filterProduct(available, fromField.getValue(), toField.getValue()));
		});

		layout.add(filters);

		/* create and populate Grid
		final Grid<StockItem> grid = new Grid<>();
		grid.setWidth("90%");
		grid.setDataProvider(dataProvider);

		grid.addColumn(new LocalDateRenderer<>(StockItem::getAvailable)).setHeader("Available");
		grid.addColumn(StockItem::getName).setHeader("Name");
		grid.addColumn(StockItem::getPrice).setHeader("Price");

		layout.add(grid);

		 */

	}
}