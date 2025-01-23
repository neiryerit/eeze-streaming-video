package com.eeze.streaming.domain;

import com.eeze.streaming.util.ActorRole;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Performer {
    
    @NotBlank(message = "performer.name is is missing")
    private String name;

    private String character;

    @NotBlank(message = "performer.role is is missing")
    @Pattern(regexp = "LEAD|SUPPORT|CAMEO", message = "allowed values for actor role are: LEAD, SUPPORT or CAMEO")
    private ActorRole role;

}
