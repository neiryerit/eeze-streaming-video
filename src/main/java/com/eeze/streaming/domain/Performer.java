package com.eeze.streaming.domain;

import com.eeze.streaming.util.ActorRole;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Performer {
    
    @NotBlank(message = "is is missing")
    private String name;

    private String character;

    @NotBlank(message = "is is missing")
    @Pattern(regexp = "LEAD|SUPPORT|CAMEO", message = "allowed values are: LEAD, SUPPORT or CAMEO")
    private String role;

}
