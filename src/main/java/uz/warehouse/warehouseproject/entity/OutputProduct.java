package uz.warehouse.warehouseproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OutputProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Product product;
    @Column(nullable = false)
    private double amount;
    @Column(nullable = false)
    private double price;
    @ManyToOne
    private Output output;

    public OutputProduct(Product product, double amount, double price, Output output) {
        this.product = product;
        this.amount = amount;
        this.price = price;
        this.output = output;
    }
}
