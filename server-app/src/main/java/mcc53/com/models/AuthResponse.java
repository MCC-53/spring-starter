package mcc53.com.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class AuthResponse {
    private List<String> authoritiesResponse;
}
