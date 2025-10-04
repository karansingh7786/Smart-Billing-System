package Billing;

import java.util.ArrayList;

public class CartManager {
    private ArrayList<CartItem> items = new ArrayList<>();

    public void addItem(Product product, int qty) {
        for (CartItem item : items) {
            if (item.getProduct().getId() == product.getId()) {
                item.setQuantity(item.getQuantity() + qty);
                return;
            }
        }
        items.add(new CartItem(product, qty));
    }

    public void removeItem(Product product) {
        items.removeIf(item -> item.getProduct().getId() == product.getId());
    }

    public void clearCart() {
        items.clear();
    }

    public ArrayList<CartItem> getItems() {
        return items;
    }

    public double getTotal() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getSubtotal();
        }
        return total;
    }
}
