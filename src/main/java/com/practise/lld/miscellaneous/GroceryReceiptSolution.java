package com.practise.lld.miscellaneous;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

class Grocery {
    String fruit;
    double price, total;

    Grocery(String fruit, double price, double total) {
        this.fruit = fruit;
        this.price = price;
        this.total = total;
    }
}

class Node {
    String fruit;
    int count;

    Node(String fruit, int count) {
        this.fruit = fruit;
        this.count = count;
    }
}

abstract class GroceryReceiptBase {
    private Map<String, Double> prices;
    private Map<String, Integer> discounts;

    public GroceryReceiptBase(Map<String, Double> prices, Map<String, Integer> discounts) {
        this.prices = prices;
        this.discounts = discounts;
    }

    public abstract List<Grocery> Calculate(List<Node> shoppingList);

    public Map<String, Double> getPrices() {
        return prices;
    }

    public Map<String, Integer> getDiscounts() {
        return discounts;
    }
}
//Create the 'GroceryReceipt' class that extends GroceryReceiptBase above.

class GroceryReceipt extends GroceryReceiptBase {
    private Map<String, Double> prices;
    private Map<String, Integer> discounts;

    public GroceryReceipt(Map<String, Double> prices, Map<String, Integer> discounts) {
        super(prices, discounts);
        this.prices = prices;
        this.discounts = discounts;
    }

    public List<Grocery> Calculate(List<Node> shoppingList) {
        List<Grocery> groceries = new ArrayList<>();
        List<Node> uniqueList = new ArrayList<>();

        for (Node n : shoppingList) {
            Optional<Node> existingElement = uniqueList.stream().filter(e -> e.fruit.equals(n.fruit)).findFirst();
            if (existingElement.isPresent()) {
                Node eNode = existingElement.get();
                uniqueList.remove(eNode);
                eNode.count += n.count;
                uniqueList.add(eNode);
            } else {
                uniqueList.add(n);
            }
        }


        for (Node n : uniqueList) {
            Double price = prices.getOrDefault(n.fruit, 0.0);
            Integer discount = discounts.getOrDefault(n.fruit, 0);
            double discountedPrice = price;
            if (discount != 0) {
                discountedPrice = (price) * (discount / 100);
            }
            double total = discountedPrice * n.count;
            Grocery g = new Grocery(n.fruit, discountedPrice, total);
            groceries.add(g);
        }
        groceries.sort(Comparator.comparing(e -> e.fruit));
        return groceries;
    }
}


class GroceryReceiptSolution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.getenv("OUTPUT_PATH"), "UTF-8");

        List<Node> boughtItems = new ArrayList<>();
        Map<String, Double> prices = new HashMap<>();
        Map<String, Integer> discounts = new HashMap<>();

        int n = Integer.parseInt(reader.readLine().trim());
        for (int i = 0; i < n; i++) {
            String[] a = reader.readLine().trim().split(" ");
            prices.put(a[0], Double.parseDouble(a[1]));
        }
        int m = Integer.parseInt(reader.readLine().trim());
        for (int i = 0; i < m; i++) {
            String[] a = reader.readLine().trim().split(" ");
            discounts.put(a[0], Integer.parseInt(a[1]));
        }
        int b = Integer.parseInt(reader.readLine().trim());
        for (int i = 0; i < b; i++) {
            String[] a = reader.readLine().trim().split(" ");
            boughtItems.add(new Node(a[0], Integer.parseInt(a[1])));
        }

        GroceryReceipt g = new GroceryReceipt(prices, discounts);
        List<Grocery> result = g.Calculate(boughtItems);
        for (Grocery x : result) {
            writer.printf("%s %.1f %.1f\n", x.fruit, x.price, x.total);
        }

        writer.flush();
        writer.close();
    }
}
