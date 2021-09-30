package model;

import java.util.HashMap;

public class ItemList {

    public HashMap<String, Item> list;
    public Item item;

    public ItemList() {
        list = new HashMap<>();
    }

    public void addItem(String name) {
        item = new Item(name);
        list.put(String.valueOf(list.size()), item);
    }

    public HashMap<String, Item> getList() {
        return list;
    }
}
