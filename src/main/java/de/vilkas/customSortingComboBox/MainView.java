package de.vilkas.customSortingComboBox;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by Vilkas on 28/02/2018.
 */
@SpringUI
public class MainView extends UI {
    @Override
    protected void init(final VaadinRequest vaadinRequest) {
        Collection<Item> items = createItems();
        ComboBox<Item> sortedComboBox = createSortingComboBox(items);
        ComboBox<Item> regularComboBox = createRegularComboBox(items);
        HorizontalLayout boxLayout = new HorizontalLayout(regularComboBox, sortedComboBox,  new Label("try entering values 11-14 in bouth boxes"));
        setContent(boxLayout);

    }

    private ComboBox<Item> createRegularComboBox(final Collection<Item> items) {
        ComboBox<Item> regularComboBox = new ComboBox<>();
        regularComboBox.setItems(items);
        regularComboBox.setCaption("regular ComboBox");
        regularComboBox.setItemCaptionGenerator(i -> i.getId() + ":" + i.getDescription());
        return regularComboBox;
    }

    private ComboBox<Item> createSortingComboBox(final Collection<Item> items) {
        ComboBox<Item> sortedComboBox = new ComboBox<>();
        sortedComboBox.setDataProvider(new SortingDataProvider<Item>(sortedComboBox, items,
                (( item, string) ->  item == null ? false : string.equals(String.valueOf(item.getId())))));
        sortedComboBox.setCaption("Sorting ComboBox");
        sortedComboBox.setItemCaptionGenerator(i -> i.getId() + ":" + i.getDescription());
        return sortedComboBox;
    }

    private Collection<Item> createItems() {
        Item item1 = new Item(11L, "nivea(14131211)");
        Item item2 = new Item(12L, "isana(13141211)");
        Item item3 = new Item(13L,  "bebe(11121314)");
        Item item4 = new Item(14L,  "olaz(11131214)");
        return Arrays.asList(item1, item2, item3, item4);
    }
}
