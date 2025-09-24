package  com.Expenses_traker.Auth_service.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisteCheckMessage {
    String subjet;
    boolean isError;
    String message;  
}
