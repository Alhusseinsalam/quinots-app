package dev.husein.quinots.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Entity
@Table(name="q_role")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

    @Column(name="role_name")
    @JsonProperty("role_name")
    private String roleName;

    @Column(name="description")
    @JsonProperty("description")
    private String description;

    @Override
    public String getAuthority() {
        return roleName;
    }
}