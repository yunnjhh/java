package com.example.effectivejava01.chapter01.item01;

public class Order {

    private boolean prime;

    private boolean urgent;

    private Product product;

    public static Order primeOrder(Product product) {
        Order order = new Order();
        order.prime = true;
        order.product = product;
        return order;
    }

    public static Order urgentOrder(Product product) {
        Order order = new Order();
        order.urgent = true;
        order.product = product;
        return order;
    }

    public String toString() {
        return "prime: " + this.prime + " urgent: " + this.urgent;
    }

}
