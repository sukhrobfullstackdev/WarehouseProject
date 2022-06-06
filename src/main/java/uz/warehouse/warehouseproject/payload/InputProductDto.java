package uz.warehouse.warehouseproject.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class InputProductDto {
    private Integer productId;
    private double amount;
    private double price;
    private Date expireDate;
    private Integer inputId;
}
