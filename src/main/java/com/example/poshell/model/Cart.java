package com.example.poshell.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Cart {

    private List<Item> items = new ArrayList<>();

    public boolean addItem(Item item) {
        return items.add(item);
    }

    public void emptyCart() {
        items.clear();
    }

    public boolean modifyCart(String productId, int amount) {
        boolean contains = false;
        Item itemToRemove = null;
        for (Item item:items) {
            if (item.getProduct().getId().equals(productId)) {
                contains = true;
                if (amount == 0) {
                    itemToRemove = item;
                } else {
                    item.setAmount(amount);
                }
                break;
            }
        }
        if (itemToRemove != null) {
            items.remove(itemToRemove);
        }
        return contains;
    }

    @Override
    public String toString() {
        if (items.size() ==0){
            return "Empty Cart";
        }
        double total = 0;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Cart -----------------\n"  );

        for (int i = 0; i < items.size(); i++) {
            stringBuilder.append(items.get(i).toString()).append("\n");
            total += items.get(i).getAmount() * items.get(i).getProduct().getPrice();
        }
        stringBuilder.append("----------------------\n"  );

        stringBuilder.append("Total...\t\t\t" + total );

        return stringBuilder.toString();
    }
}
