package com.laba7;
import java.util.*;
class ECommercePlatform {
    private Map<Integer, User> users;
    private Map<Integer, Product> products;
    private Map<Integer, Order> orders;

    public ECommercePlatform() {
        this.users = new HashMap<>();
        this.products = new HashMap<>();
        this.orders = new HashMap<>();
    }

    // Методи для додавання користувачів, товарів, створення замовлень та інших операцій

    public void addUser(User user) {
        users.put(user.getId(), user);
    }

    public void addProduct(Product product) {
        products.put(product.getId(), product);
    }

    public void createOrder(User user) {
        Order order = new Order(orders.size() + 1, user.getId());
        Map<Product, Integer> cartItems = user.getCart();

        for (Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            // Перевірка чи є достатньо товару на складі перед створенням замовлення
            if (product.getStock() < quantity) {
                System.out.println("Недостатньо товару " + product.getName() + " на складі.");
                return;
            }

            // Додаємо товар до деталей замовлення та зменшуємо запас на складі
            order.getOrderDetails().put(product, quantity);
            product.setStock(product.getStock() - quantity);
        }

        // Розрахунок загальної вартості та додавання замовлення
        order.calculateTotalPrice();
        orders.put(order.getId(), order);

        // Очищення кошика користувача після створення замовлення
        cartItems.clear();

        System.out.println("Створено замовлення #" + order.getId() + " для користувача " + user.getUsername() +
                ". Загальна вартість: $" + order.getTotalPrice());
    }

    // Інші методи, такі як виведення списків користувачів, товарів, замовлень і т.д.

    public void listUsers() {
        System.out.println("Список користувачів:");
        users.values().forEach(user -> System.out.println(user.getId() + ": " + user.getUsername()));
    }

    public void listAvailableProducts() {
        System.out.println("Список доступних товарів:");
        products.values().forEach(product -> System.out.println(product.getId() + ": " +
                product.getName() + " - Запас: " + product.getStock()));
    }

    public void listOrders() {
        System.out.println("Список замовлень:");
        orders.values().forEach(order -> System.out.println("Замовлення #" + order.getId() +
                " для користувача #" + order.getUserId() + " - Загальна вартість: $" + order.getTotalPrice()));
    }

    // Методи для сортування та фільтрації товарів, рекомендацій та інших функцій

    // Сортування товарів за назвою
    public List<Product> sortProductsByName() {
        List<Product> sortedProducts = new ArrayList<>(products.values());
        Collections.sort(sortedProducts, new ProductNameComparator());
        return sortedProducts;
    }

    // Сортування товарів за ціною (використовує Comparable)
    public List<Product> sortProductsByPrice() {
        List<Product> sortedProducts = new ArrayList<>(products.values());
        Collections.sort(sortedProducts);
        return sortedProducts;
    }

    // Сортування товарів за запасами (використовує Comparator)
    public List<Product> sortProductsByStock() {
        List<Product> sortedProducts = new ArrayList<>(products.values());
        Collections.sort(sortedProducts, new ProductStockComparator());
        return sortedProducts;
    }

    // Фільтрація товарів за наявністю на складі
    public List<Product> filterProductsByStock(int minStock) {
        List<Product> filteredProducts = new ArrayList<>();
        for (Product product : products.values()) {
            if (product.getStock() >= minStock) {
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }

    // Рекомендації для користувача на основі товарів у кошику та історії замовлень
    public List<Product> recommendProductsForUser(User user) {
        List<Product> recommendedProducts = new ArrayList<>();

        // Отримати товари з кошика користувача
        Map<Product, Integer> userCart = user.getCart();
        for (Product product : userCart.keySet()) {
            if (!recommendedProducts.contains(product)) {
                recommendedProducts.add(product);
            }
        }

        // Отримати товари з історії замовлень користувача
        for (Order order : orders.values()) {
            if (order.getUserId().equals(user.getId())) {
                for (Product product : order.getOrderDetails().keySet()) {
                    if (!recommendedProducts.contains(product)) {
                        recommendedProducts.add(product);
                    }
                }
            }
        }

        return recommendedProducts;
    }
}
