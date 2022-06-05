package com.ryana.ds;

import java.util.Objects;

/* Binary Search Tree */
public class BST<E extends Comparable<E>> {
    public static void main(final String[] args) {
        final var bst = new BST<Integer>(5);
        bst.insert(1);
        bst.insert(9);
        bst.insert(2);
        bst.insert(8);
        bst.insert(3);
        bst.insert(7);
        bst.insert(4);
        bst.insert(6);
        bst.preOrder();
        System.out.println();
        bst.inOrder();
        System.out.println();
        bst.postOrder();

    }

    private Node<E> root;

    public BST(final E data) {
        this.root = new Node<>(data);
    }

    public boolean isEmpty() {
        return Objects.isNull(root);
    }

    public void insert(final E data) {
        if (Objects.isNull(data)) {
            return;
        }
        root = insert(data, root);
    }

    public Node<E> search(final E data) {
        if (isEmpty()) {
            return null;
        }
        for (Node<E> node = root; Objects
                .nonNull(node); node = data.compareTo(node.data) < 0 ? node.left : node.right) {
            if (data.compareTo(node.data) == 0) {
                return node;
            }
        }
        return null;
    }

    private Node<E> insert(final E data, final Node<E> node) {
        if (Objects.isNull(node)) {
            return new Node<>(data);
        }
        if (data.compareTo(node.data) < 0) {
            node.left = insert(data, node.left);
        } else if (data.compareTo(node.data) > 0) {
            node.right = insert(data, node.right);
        }
        return node;
    }

    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(final Node<E> node) {
        if (Objects.nonNull(node)) {
            System.out.printf("%d ", node.data);
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(final Node<E> node) {
        if (Objects.nonNull(node)) {
            inOrder(node.left);
            System.out.printf("%d ", node.data);
            inOrder(node.right);
        }
    }

    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(final Node<E> node) {
        if (Objects.nonNull(node)) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.printf("%d ", node.data);
        }
    }

    private static final class Node<E> {
        private E data;
        private Node<E> left;
        private Node<E> right;

        public Node(final E data) {
            this.data = data;
        }

        public Node(final Node<E> left, final E data) {
            this(data);
            this.left = left;
        }

        public Node(final E data, final Node<E> right) {
            this(data);
            this.right = right;
        }

        public E getData() {
            return data;
        }

        public Node<E> getLeft() {
            return left;
        }

        public Node<E> getRight() {
            return right;
        }

    }
}
