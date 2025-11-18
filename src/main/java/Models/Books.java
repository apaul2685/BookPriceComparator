package Models;

import java.util.HashMap;
import java.util.Map;

public class Books {
    private String name;
    private int quantity;
    private String isbn;
    private String author;
    private String publisher;
    private Map<String, String> prices = new HashMap<>();
    private Map<String, Boolean> availability = new HashMap<>();

    // Constructor
    public Books(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public Map<String, String> getPrices() {
        return prices;
    }

    public Map<String, Boolean> getAvailability() {
        return availability;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setPrices(Map<String, String> prices) {
        this.prices = prices;
    }

    public void setAvailability(Map<String, Boolean> availability) {
        this.availability = availability;
    }

}

