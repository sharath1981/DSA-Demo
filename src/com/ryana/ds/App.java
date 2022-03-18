package com.ryana.ds;

public class App {
    public static void main(String[] args) throws Exception {
        final var list = new  SinglyLinkedList<String>();
        list.addHead("A");
        list.addHead("B");
        list.addHead("C");
        list.addHead("D");
        list.addHead("E");

        list.printAll();
        list.reverse();
        list.printAll();
              
    }
}
