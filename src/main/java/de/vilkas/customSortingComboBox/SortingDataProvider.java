package de.vilkas.customSortingComboBox;

import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.data.provider.Query;
import com.vaadin.server.SerializablePredicate;
import com.vaadin.ui.ComboBox;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Vilkas on 08/03/2018.
 */
public class SortingDataProvider<T> extends ListDataProvider<T> {
    private ComboBox<T> comboBox;
    private BiPredicate<T, String> exactFit;

    public SortingDataProvider(final ComboBox<T> comboBox, final Collection<T> items, BiPredicate<T, String> exactFit) {
        super(items);
        this.comboBox = comboBox;
        this.exactFit = exactFit;
    }

    @Override
    public Stream<T> fetch(Query<T, SerializablePredicate<T>> query) {
        final List<T> items = super.fetch(query).collect(Collectors.toList());

        final T perfectFit = items.stream()
                .filter(i -> exactFit.test(i, getFilterText()))
                .findAny()
                .orElse(null);

        if (perfectFit != null) {
            items.remove(perfectFit);
            List<T> sorted = new ArrayList<>();
            sorted.add(perfectFit);
            sorted.addAll(items);
            return sorted.stream();
        }
        return items.stream();
    }

    private String getFilterText() {
        String filterText;
        try {
            final Field currentFilterText = comboBox.getClass().getDeclaredField("currentFilterText");
            currentFilterText.setAccessible(true);
            filterText = (String) currentFilterText.get(comboBox);
        } catch (NoSuchFieldException ex) {
            throw new IllegalArgumentException("currentFilterText is no more a Field in Vaadin ComboBox, check the ChangeLog  of Vaadin");
        } catch (IllegalAccessException e1) {
            throw new IllegalArgumentException("Problems with currentFilterText in Vaadin ComboBox, check the ChangeLog of Vaadin");
        }
        return filterText == null ? "" : filterText;
    }

}
