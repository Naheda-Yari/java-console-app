// Src is the name of the folder where selling.java file are inside
package Src;

public class Selling {
    // These variables store the selling data.
    private int id;
    private int clientId;
    private int goodsId;
    private int quantity;
    private double unitPrice;
    private double totalPrice;

    // Constructor
    // Automatically calculates total price
    public Selling(int id, int clientId, int goodsId, int quantity, double unitPrice) {
        this.id = id;
        this.clientId = clientId;
        this.goodsId = goodsId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;

        this.totalPrice = quantity * unitPrice;
    }

    // GETTERS
    // Used for reports and calculations
    public int getClientId() {
        return clientId;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    // selling info
    public String toString() {
        return "Selling ID: " + id +
                ", ClientID: " + clientId +
                ", GoodsID: " + goodsId +
                ", Quantity: " + quantity +
                ", Unit Price: " + unitPrice +
                ", Total Price: " + totalPrice;
    }

    // saving data to CSV
    public String toCSV() {
        return id + "," + clientId + "," + goodsId + "," +
                quantity + "," + unitPrice + "," + totalPrice;
    }
}