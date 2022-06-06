package uz.warehouse.warehouseproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.warehouse.warehouseproject.entity.template.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Supplier extends AbstractEntity {
    @Column(nullable = false, unique = true)
    private String phoneNumber;

    public Supplier(String name, boolean isActive, String phoneNumber) {
        super(name, isActive);
        this.phoneNumber = phoneNumber;
    }

}
