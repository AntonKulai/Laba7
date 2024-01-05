package com.laba7;
import java.util.*;

// Частина 3: Розширені Функції

// Реалізація Comparable для сортування за назвою
class ProductNameComparator implements Comparator<Product> {
    @Override
    public int compare(Product p1, Product p2) {
        return p1.getName().compareTo(p2.getName());
    }
}

// Реалізація Comparator для сортування за запасами
class ProductStockComparator implements Comparator<Product> {
    @Override
    public int compare(Product p1, Product p2) {
        return Integer.compare(p1.getStock(), p2.getStock());
    }
}
