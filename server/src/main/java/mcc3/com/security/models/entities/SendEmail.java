package mcc3.com.security.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author firmanzega
 */
@Data
@ToString
public class SendEmail {
    
    private String to;
    private String subject;
    private String text;
    
}
