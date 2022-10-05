package model;

import java.util.ArrayList;

public class Product {

    public int orderID;
    public String shopperName;
    public String shopperEmail;
    public ArrayList<Content> contents;
    public boolean orderCompleted;
    public static class Content {
        public int productID;
        public String productName;
        public int quantity;
    }
}
