package com.nieceoftimes.serverside.model.entity;

import com.nieceoftimes.serverside.enums.PrivilegeAuthority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "privilege")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Privilege {
    @Id
    @GeneratedValue(generator = "uuid-generator")
    @GenericGenerator(name = "uuid-generator", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(length = 36)
    private String id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PrivilegeAuthority name;
}
