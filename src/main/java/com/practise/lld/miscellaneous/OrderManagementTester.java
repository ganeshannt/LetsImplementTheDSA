package com.practise.lld.miscellaneous;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

interface IOrder {
    String getName();

    void setName(String name);

    int getPrice();

    void setPrice(int price);
}

interface IOrderSystem {
    void addToCart(IOrder order);

    void removeFromCart(IOrder order);

    int calculateTotalAmount();

    Map<String, Integer> categoryDiscounts();

    Map<String, Integer> cartItems();
}


class Order implements IOrder {
    private String name;
    private int price;

    public Order(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public Order() {
        this.name = null;
        this.price = 0;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getPrice() {
        return this.price;
    }

    @Override
    public void setPrice(int price) {
        this.price = price;
    }

}

class OrderSystem implements IOrderSystem {
    // Constants for categories and discounts
    private static final String CHEAP = "Cheap";
    private static final String MODERATE = "Moderate";
    private static final String EXPENSIVE = "Expensive";
    private static final int CHEAP_DISCOUNT_RATE = 10; // percent
    private static final int MODERATE_DISCOUNT_RATE = 20; // percent
    private static final int EXPENSIVE_DISCOUNT_RATE = 30; // percent
    private final List<IOrder> cart;

    OrderSystem() {
        cart = new ArrayList<>();
    }

    @Override
    public void addToCart(IOrder order) {
        if (order != null) {
            cart.add(order);
        }
    }

    @Override
    public void removeFromCart(IOrder order) {
        if (order != null) {
            cart.remove(order);
        }
    }

    @Override
    public int calculateTotalAmount() {
        int totalDiscount = categoryDiscounts().values().stream().mapToInt(Integer::intValue).sum();
        int totalWithoutDiscount = 0;
        for (IOrder order : cart) {
            totalWithoutDiscount += order.getPrice();
        }
        return totalWithoutDiscount - totalDiscount;
    }

    @Override
    public Map<String, Integer> categoryDiscounts() {
        Map<String, Integer> categoryAndDiscountMap = new HashMap<>();
        for (IOrder order : cart) {
            String category = getCategory(order.getPrice());
            double discountPrice = order.getPrice() * ((double) getDiscountPercentage(category) / 100);
            categoryAndDiscountMap.put(category, categoryAndDiscountMap.getOrDefault(category, 0) + (int) discountPrice);
        }
        return categoryAndDiscountMap;
    }


    String getCategory(int price) {
        if (price <= 10) return CHEAP;
        if (price <= 20) return MODERATE;
        return EXPENSIVE;
    }

    Integer getDiscountPercentage(String category) {
        return switch (category) {
            case CHEAP -> CHEAP_DISCOUNT_RATE;
            case MODERATE -> MODERATE_DISCOUNT_RATE;
            case EXPENSIVE -> EXPENSIVE_DISCOUNT_RATE;
            default -> 0;
        };
    }

    @Override
    public Map<String, Integer> cartItems() {
        Map<String, Integer> productAndCountMap = new LinkedHashMap<>();
        for (IOrder o : cart) {
            productAndCountMap.put(o.getName(), productAndCountMap.getOrDefault(o.getName(), 0) + 1);
        }
        return productAndCountMap;
    }
}

public class OrderManagementTester {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter textWriter = new PrintWriter(System.out);

        IOrderSystem orderSystem = new OrderSystem();
        int oCount = Integer.parseInt(br.readLine().trim());
        for (int i = 1; i <= oCount; i++) {
            String[] a = br.readLine().trim().split(" ");
            IOrder e = new Order();
            e.setName(a[0]);
            e.setPrice(Integer.parseInt(a[1]));
            orderSystem.addToCart(e);
        }
        int totalAmount = orderSystem.calculateTotalAmount();
        textWriter.println("Total Amount: " + totalAmount);

        Map<String, Integer> categoryDiscounts = orderSystem.categoryDiscounts();
        for (Map.Entry<String, Integer> entry : categoryDiscounts.entrySet()) {
            if (entry.getValue() > 0) {
                textWriter.println(entry.getKey() + " Category Discount: " + entry.getValue());
            }
        }

        Map<String, Integer> cartItems = orderSystem.cartItems();
        for (Map.Entry<String, Integer> entry : cartItems.entrySet()) {
            textWriter.println(entry.getKey() + " (" + entry.getValue() + " items)");
        }

        textWriter.flush();
        textWriter.close();
    }
}
