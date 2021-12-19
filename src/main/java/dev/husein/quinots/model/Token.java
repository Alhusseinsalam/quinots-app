package dev.husein.quinots.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Token {
    @JsonProperty("token")
    private String tokenValue;
}
