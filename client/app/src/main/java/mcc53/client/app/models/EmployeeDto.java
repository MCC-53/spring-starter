/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.client.app.models;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 *
 * @author ACER
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class EmployeeDto {
    private String email, firstName, lastName, phoneNumber, username, password;
    private Long departmentId;
    private List<ProjectData> projects;
    private List<RoleData> roles;
}