package uz.warehouse.warehouseproject.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ProductDto {
    private String name;
    private Integer categoryId;
    private Integer photoId;
    private Integer measurementId;
}
