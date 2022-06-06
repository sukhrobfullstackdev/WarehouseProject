package uz.warehouse.warehouseproject.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private boolean status;
    private String message;
    private Object object;

    public Message(boolean status, String message) {
        this.status = status;
        this.message = message;
    }
}
