package uz.warehouse.warehouseproject.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class OutputProductDto {
    private Integer productId;
    private double amount;
    private double price;
    private Integer outputId;
}
