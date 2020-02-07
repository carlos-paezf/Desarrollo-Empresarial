package model;

/**
 *
 * @author Estudiante
 */
public class Product {
    
    private int barcode;
    private String name;
    private String description;
    private double sellPrice;
    private ProductType productType;

    public Product() {
    }

    public Product(int barcode, String name, String description, double sellPrice, ProductType productType) {
        this.barcode = barcode;
        this.name = name;
        this.description = description;
        this.sellPrice = sellPrice;
        this.productType = productType;
    }
    
    public int getBarcode() {
        return barcode;
    }

    public void setBarcode(int barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    @Override
    public String toString() {
        return "Product{" + "barcode=" + barcode + ", name=" + name + ", description=" + description + ", sellPrice=" + sellPrice + ", productType=" + productType + '}';
    }
    
}
