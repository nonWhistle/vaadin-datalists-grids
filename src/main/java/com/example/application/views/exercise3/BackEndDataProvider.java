package com.example.application.views.exercise3;


import com.example.application.views.MainLayout;
import com.example.application.views.exercise1.Person;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.CallbackDataProvider;
import com.vaadin.flow.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

@PageTitle("Exercise 3")
//@Route(value = BackEndDataProvider.ROUTE, layout = MainLayout.class)
@Route(value = "Exercise-3", layout = MainLayout.class)
public class BackEndDataProvider extends VerticalLayout {
	private static final long serialVersionUID = 1L;

	public static final String ROUTE = "ex3";
	public static final String TITLE = "Exercise 3";

	final PersonService service = new PersonService();
	final Grid<Person> grid = new Grid<>();


	public BackEndDataProvider() {
 		setWidth("100%");

		final List<AgeGroup> groups = new ArrayList<>();
		groups.add(new AgeGroup(0, 18));
		groups.add(new AgeGroup(19, 26));
		groups.add(new AgeGroup(27, 40));
		groups.add(new AgeGroup(41, 100));

		HorizontalLayout horizontalLayout = new HorizontalLayout();
		final ComboBox<AgeGroup> filter = new ComboBox<>("Filter", groups);

		horizontalLayout.add(filter);

		// TODO create lazy Data Provider using the PersonService
		final CallbackDataProvider<Person, AgeGroup> dataProvider = DataProvider.fromFilteringCallbacks(
				q -> service.getPersons(q.getOffset(), q.getLimit(), q.getFilter().orElse(null)),
				q -> service.countPersons(q.getOffset(), q.getLimit(), q.getFilter().orElse(null)));

		final ConfigurableFilterDataProvider<Person, Void, AgeGroup> filterProvider = dataProvider
				.withConfigurableFilter();

		grid.setDataProvider(filterProvider);

		// TODO add value change listener to filter and update the DataProvider
		// accordingly
		filter.addValueChangeListener(e -> {
			final AgeGroup value = e.getValue();
			filterProvider.setFilter(value);
		});

		grid.setWidth("90%");
		add(horizontalLayout, grid);


		grid.addColumn(Person::getName).setHeader("Name").setKey("name");
		grid.addColumn(Person::getEmail).setHeader("Email").setKey("email");
		grid.addColumn(Person::getAge).setHeader("Age").setKey("age");
		grid.addColumn(Person::getBirthday).setHeader("Birthday").setKey("birthday");
	}
}
