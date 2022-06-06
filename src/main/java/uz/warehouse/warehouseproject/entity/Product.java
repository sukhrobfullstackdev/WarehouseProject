package uz.warehouse.warehouseproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.warehouse.warehouseproject.entity.template.AbstractEntity;
import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product extends AbstractEntity {
    @ManyToOne(optional = false)
    private Category category;
    @OneToOne
    private Attachment attachment;
    @Column(nullable = false,unique = true)
    private String code;
    @ManyToOne(optional = false)
    private Measurement measure;
}
