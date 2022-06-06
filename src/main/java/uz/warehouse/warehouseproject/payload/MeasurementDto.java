package uz.warehouse.warehouseproject.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MeasurementDto {
    private String name;
    private boolean active;

    public MeasurementDto(String name) {
        this.name = name;
    }
}
