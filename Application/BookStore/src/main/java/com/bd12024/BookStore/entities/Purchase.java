package com.bd12024.BookStore.entities;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Purchase {
    public class ProductCart {
        public Product  product;
        public int      checkout_amount;
    }

    public int          invoice_number;
    public Timestamp    date_hour;
    public Customer     customer;
    public ArrayList<ProductCart> products = new ArrayList<ProductCart>();
}
