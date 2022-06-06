package uz.warehouse.warehouseproject.entity;

import lombok.*;
import uz.warehouse.warehouseproject.entity.template.AbstractEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Measurement extends AbstractEntity {}
