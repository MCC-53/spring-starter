/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.com.frontend.models;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author aceng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private List<String> authorities;
}
