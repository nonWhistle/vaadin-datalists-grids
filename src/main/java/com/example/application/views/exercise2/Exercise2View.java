package com.example.application.views.exercise2;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.LocalDateRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.time.LocalDate;

@PageTitle("Exercise 2")
@Route(value = "Exercise-2", layout = MainLayout.class)
public class Exercise2View extends VerticalLayout
{
    Grid<StockItem> grid;
    private final ListDataProvider<StockItem> dataProvider;

    public Exercise2View()
    {
        dataProvider = DataProviderHelper.createProductDataProvider();

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        DatePicker start = new DatePicker("Start");
        DatePicker end = new DatePicker("End");
        Button filter = new Button("Filter");

        filter.addClickListener(e -> {

            // setFilter will clear any previous filter
            dataProvider.setFilter(StockItem::getAvailable, available -> filterProduct(available, start.getValue(), end.getValue()));
        });

        horizontalLayout.add(start, end, filter);
        horizontalLayout.setDefaultVerticalComponentAlignment(Alignment.END);

        grid = new Grid<>();
        grid.addColumn(new LocalDateRenderer<>(StockItem::getAvailable)).setHeader("Available");
        grid.addColumn(StockItem::getName).setHeader("Name");
        grid.addColumn(StockItem::getPrice).setHeader("Price £");
        grid.setWidthFull();
        grid.setDataProvider(dataProvider);

        add(horizontalLayout, grid);
    }

    private boolean filterProduct(LocalDate available, LocalDate start, LocalDate end) {

        //final LocalDate available = product.getAvailable();

        // Null checks
        if (available == null) {

            if (start != null || end != null) {
                // data is null, but user wants to filter; assume false
                return false;
            }
            return true;
        }

        if (start == null && end == null) {

            return true;

        } else if (start == null) {

            return available.isBefore(end) || available.equals(end);

        } else if (end == null) {

            return available.isAfter(start) || available.equals(start);

        } else {

            final boolean atEnds = available.equals(start) || available.equals(end);
            final boolean inbetween = available.isAfter(start) && available.isBefore(end);

            return atEnds || inbetween;
        }
    }

}
