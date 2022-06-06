package uz.warehouse.warehouseproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Input {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private Timestamp date;
    @ManyToOne(optional = false)
    private Warehouse warehouse;
    @ManyToOne(optional = false)
    private Supplier supplier;
    @ManyToOne
    private Currency currency;
    @Column(nullable = false)
    private String facture_number;
    @Column(nullable = false, unique = true)
    private String code;


    public Input(Timestamp date, Warehouse warehouse, Supplier supplier, Currency currency, String facture_number, String code) {
        this.date = date;
        this.warehouse = warehouse;
        this.supplier = supplier;
        this.currency = currency;
        this.facture_number = facture_number;
        this.code = code;
    }
}
