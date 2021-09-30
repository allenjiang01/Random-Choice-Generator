package model;

import java.util.HashMap;

public class ItemList {

    public HashMap<String, Item> list;
    public Item item;

    // MODIFIES: this
    // EFFECTS: create a new item list
    public ItemList() {
        list = new HashMap<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a new item
    public void addItem(String name) {
        item = new Item(name);
        list.put(String.valueOf(list.size()), item);
    }

    // MODIFIES: this
    // EFFECTS: deletes an item
    public void deleteItem(String name) {
            list.remove(name);
                int index = Integer.parseInt(name);


                String finalItem = "";

                for (int i = index + 1; i < list.size(); i++) {
                    String key = String.valueOf(i);
                    finalItem = key;
                    list.put(String.valueOf(i - 1), list.get(key));
                }

                list.remove(finalItem);

    }

    // EFFECTS: returns the list of items
    public HashMap<String, Item> getList() {
        return list;
    }
}
