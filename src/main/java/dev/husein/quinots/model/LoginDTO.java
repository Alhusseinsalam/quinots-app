package dev.husein.quinots.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class LoginDTO {
    @NotNull
    @JsonProperty(value = "username")
    private String username;

    @NotNull
    @JsonProperty(value = "password")
    private String password;
}
