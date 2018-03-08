package de.vilkas.customSortingComboBox;

/**
 * Created by Vilkas on 28/02/2018.
 */
public class Item {
    private Long id;
    private String name;
    private String description;

    public Item() {
    }

    public Item(final Long id, final String name, final String description) {
        this();
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return id + ":" + description;
    }
}
