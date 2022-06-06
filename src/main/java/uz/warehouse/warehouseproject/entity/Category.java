package uz.warehouse.warehouseproject.entity;

import lombok.*;
import uz.warehouse.warehouseproject.entity.template.AbstractEntity;

import javax.persistence.*;
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category extends AbstractEntity {
    @ManyToOne
    private Category categoryParent;
}
