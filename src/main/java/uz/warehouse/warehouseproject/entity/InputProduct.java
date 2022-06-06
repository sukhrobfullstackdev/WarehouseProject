package uz.warehouse.warehouseproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class InputProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Product product;
    @Column(nullable = false)
    private double amount;
    @Column(nullable = false)
    private double price;
    @Column(nullable = false)
    private Date expireDate;
    @ManyToOne
    private Input input;

    public InputProduct(Product product, double amount, double price, Date expireDate, Input input) {
        this.product = product;
        this.amount = amount;
        this.price = price;
        this.expireDate = expireDate;
        this.input = input;
    }
}
