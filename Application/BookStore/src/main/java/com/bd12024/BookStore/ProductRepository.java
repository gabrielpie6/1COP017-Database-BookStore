package com.bd12024.BookStore;

import com.bd12024.BookStore.entities.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> findAll();
}
