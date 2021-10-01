package model;

import java.util.HashMap;

public class ItemList {

    public HashMap<String, Item> list;

    // MODIFIES: this
    // EFFECTS: create a new item list
    public ItemList() {
        list = new HashMap<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a new item
    public void addItem(String name) {
        Item i = new Item(name);
        list.put(String.valueOf(list.size()), i);
    }

    // MODIFIES: this
    // EFFECTS: deletes an item
    public void deleteItem(String name) {
        list.remove(name);
        int index = Integer.parseInt(name);
//        System.out.println("Index is: " + index);
//        System.out.println("Size of list: " + list.size());



        for (int i = index; i < list.size(); i++) {
            String nextKey = String.valueOf(i+1);
            list.put(String.valueOf(i), list.get(nextKey));
            list.remove(nextKey);
        }

    }

    // EFFECTS: returns the list of items
    public HashMap<String, Item> getList() {
        return list;
    }
}
