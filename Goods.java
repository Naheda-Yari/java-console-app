// Src is the name of the folder where Goods.java file are inside
package Src;

public class Goods {
    // These variables store the Goods data.
    private int id;
    private String name;
    private String category;
    private double price;
    private int stock;

    // Constructor
    // Used this to create a Goods object
    // when adding or loading data.
    public Goods(int id, String name, String category, double price, int stock) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
    }

    // GETTERS
    // Used to safely access and update goods data
    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    // Display goods info
    public String toString() {
        return "Goods ID: " + id +
                ", Name: " + name +
                ", Category: " + category +
                ", Price: " + price +
                ", Stock: " + stock;
    }

    // saving data to CSV
    public String toCSV() {
        return id + "," + name + "," + category + "," + price + "," + stock;
    }
}