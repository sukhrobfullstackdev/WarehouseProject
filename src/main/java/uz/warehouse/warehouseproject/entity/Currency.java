package uz.warehouse.warehouseproject.entity;

import lombok.*;
import uz.warehouse.warehouseproject.entity.template.AbstractEntity;
import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
public class Currency extends AbstractEntity {
    public Currency(String name, boolean isActive) {
        super(name, isActive);
    }

    public Currency(String name) {
        super(name);
    }
}
