package com.example.effectivejava01.chapter01.item01;

public class Application01 {

    public static void main(String[] args) {

        Order primeOrder = Order.primeOrder(new Product());
        Order urgentOrder = Order.urgentOrder(new Product());

        System.out.println(primeOrder.toString());
        System.out.println(urgentOrder.toString());
    }
}
