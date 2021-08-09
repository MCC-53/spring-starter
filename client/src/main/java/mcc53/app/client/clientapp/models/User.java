package mcc53.app.client.clientapp.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author firmanzega
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class User {
    
    private Long id;
    private String nameuser;
    private String password;
    
}
