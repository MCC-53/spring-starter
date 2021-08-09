package mcc53.app.client.clientapp.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author firmanzega
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Department {
    
    private Long id;
    private String name; 
    
}
