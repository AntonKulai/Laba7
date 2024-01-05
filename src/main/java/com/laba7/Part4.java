package com.laba7;
import java.util.*;

// Частина 4: Симуляція

class ECommerceDemo {

    public static void main(String[] args) {
        // Ініціалізація платформи
        ECommercePlatform platform = new ECommercePlatform();

        // Додавання користувачів
        User user1 = new User(1, "user1");
        User user2 = new User(2, "user2");
        User user3 = new User(3, "user3");
        User user4 = new User(4, "user4");
        User user5 = new User(5, "user5");

        platform.addUser(user1);
        platform.addUser(user2);
        platform.addUser(user3);
        platform.addUser(user4);
        platform.addUser(user5);

        // Додавання товарів
        Product product1 = new Product(1, "Smartphone", 599.99, 50);
        Product product2 = new Product(2, "Laptop", 1299.99, 30);
        Product product3 = new Product(3, "Wireless Earbuds", 99.99, 40);
        Product product4 = new Product(4, "Fitness Tracker", 49.99, 20);
        Product product5 = new Product(5, "Bluetooth Speaker", 79.99, 35);

        platform.addProduct(product1);
        platform.addProduct(product2);
        platform.addProduct(product3);
        platform.addProduct(product4);
        platform.addProduct(product5);

        // Симуляція додавання товарів до кошика користувача
        user1.addToCart(product1, 3);
        user1.addToCart(product2, 2);
        user1.addToCart(product5, 1);

        user2.addToCart(product3, 4);
        user2.addToCart(product4, 2);

        user3.addToCart(product1, 1);
        user3.addToCart(product3, 3);

        user4.addToCart(product2, 2);
        user4.addToCart(product4, 1);
        user4.addToCart(product5, 1);

        user5.addToCart(product1, 2);
        user5.addToCart(product2, 1);
        user5.addToCart(product4, 2);

        // Симуляція створення та обробки замовлення
        platform.createOrder(user1);
        platform.createOrder(user2);
        platform.createOrder(user3);
        platform.createOrder(user4);
        platform.createOrder(user5);

        // Симуляція використання рекомендацій
        List<Product> recommendationsForUser1 = platform.recommendProductsForUser(user1);
        List<Product> recommendationsForUser2 = platform.recommendProductsForUser(user2);
        List<Product> recommendationsForUser3 = platform.recommendProductsForUser(user3);
        List<Product> recommendationsForUser4 = platform.recommendProductsForUser(user4);
        List<Product> recommendationsForUser5 = platform.recommendProductsForUser(user5);

        // Виведення результатів
        System.out.println("Список товарів, відсортованих за назвою:");
        platform.sortProductsByName().forEach(System.out::println);

        System.out.println("\nСписок товарів, відсортованих за ціною:");
        platform.sortProductsByPrice().forEach(System.out::println);

        System.out.println("\nСписок товарів, відсортованих за запасами:");
        platform.sortProductsByStock().forEach(System.out::println);

        System.out.println("\nРекомендації для користувача 1:");
        recommendationsForUser1.forEach(System.out::println);

        System.out.println("\nРекомендації для користувача 2:");
        recommendationsForUser2.forEach(System.out::println);

        System.out.println("\nРекомендації для користувача 3:");
        recommendationsForUser3.forEach(System.out::println);

        System.out.println("\nРекомендації для користувача 4:");
        recommendationsForUser4.forEach(System.out::println);

        System.out.println("\nРекомендації для користувача 5:");
        recommendationsForUser5.forEach(System.out::println);

        System.out.println("\nКінцевий стан користувачів:");
        platform.listUsers();

        System.out.println("\nКінцевий стан товарів:");
        platform.listAvailableProducts();

        System.out.println("\nКінцевий стан замовлень:");
        platform.listOrders();
    }
}