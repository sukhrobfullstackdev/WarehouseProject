package uz.warehouse.warehouseproject.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class OutputDto {
    private Integer warehouseId;
    private Integer currencyId;
    private String facture_number;
    private Integer clientId;
}
