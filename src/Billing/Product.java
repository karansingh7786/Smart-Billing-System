package Billing;

public class Product {
    private int id;
    private String name;
    private double price;
    private String unit;   // like "pcs", "kg", "litre"

    // 🔹 4-parameter constructor
    public Product(int id, String name, double price, String unit) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.unit = unit;
    }

    // 🔹 Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getUnit() { return unit; }

    // 🔹 Setters (optional, agar chahiye)
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setUnit(String unit) { this.unit = unit; }

    @Override
    public String toString() {
        return id + " - " + name + " (" + price + " per " + unit + ")";
    }
}
