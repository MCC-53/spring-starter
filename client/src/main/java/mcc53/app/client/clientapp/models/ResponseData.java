package mcc53.app.client.clientapp.models;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author firmanzega
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseData<E> {
    
    private Boolean status;
    
    private String message;
    
    private List<E> payLoad;
}
