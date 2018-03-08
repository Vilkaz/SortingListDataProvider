package de.vilkas.customSortingComboBox;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.UI;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by Vilkas on 28/02/2018.
 */
@SpringUI
public class MainView extends UI {
    @Override
    protected void init(final VaadinRequest vaadinRequest) {
        Item item1 = new Item(11L, "item", "131211");
        Item item2 = new Item(12L, "next item", "131211");
        Item item3 = new Item(13L, "another one", "11121314");
        Item item4 = new Item(14L, "who cares", "11131214");
        Collection<Item> items = Arrays.asList(item1, item2, item3, item4);
        ComboBox<Item> box = new ComboBox<>();
        box.setDataProvider(new SortingDataProvider<Item>(box, items,
                (( item, string) ->  item == null ? false : string.equals(String.valueOf(item.getId())))));
        box.setItemCaptionGenerator(i -> i.getId() + ":" + "name" + " " + i.getDescription());

//
//
//
//        box.addValueChangeListener(e -> {
//            System.out.println(e);
//            try {
//                final Field currentFilterText = box.getClass().getDeclaredField("currentFilterText");
//                currentFilterText.setAccessible(true);
//                final Object wat = currentFilterText.get(box);
//                System.out.println(currentFilterText);
//                int a = 1;
//            } catch (NoSuchFieldException ex) {
//                ex.printStackTrace();
//            } catch (IllegalAccessException e1) {
//                e1.printStackTrace();
//            }
//        });
        setContent(box);

    }
}
