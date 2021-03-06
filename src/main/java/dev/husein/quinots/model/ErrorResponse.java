package dev.husein.quinots.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ErrorResponse {
    @JsonProperty("Error")
    private String errMsg;
}
