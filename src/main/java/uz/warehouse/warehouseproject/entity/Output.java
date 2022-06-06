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
public class Output {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private Timestamp date;
    @ManyToOne(optional = false)
    private Warehouse warehouse;
    @ManyToOne
    private Currency currency;
    @Column(nullable = false)
    private String facture_number;
    @Column(nullable = false, unique = true)
    private String code;
    @ManyToOne
    private Client client;

    public Output(Timestamp date, Warehouse warehouse, Currency currency, String facture_number, String code, Client client) {
        this.date = date;
        this.warehouse = warehouse;
        this.currency = currency;
        this.facture_number = facture_number;
        this.code = code;
        this.client = client;
    }
}
