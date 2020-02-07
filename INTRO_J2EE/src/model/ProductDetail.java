package model;

/**
 *
 * @author Estudiante
 */
public class ProductDetail {
    
    private Product product;
    private Invoice invoice;

    public ProductDetail() {
    }

    public ProductDetail(Product product, Invoice invoice) {
        this.product = product;
        this.invoice = invoice;
    }  

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    @Override
    public String toString() {
        return "ProductDetail{" + "product=" + product + ", invoice=" + invoice + '}';
    }
    
}
