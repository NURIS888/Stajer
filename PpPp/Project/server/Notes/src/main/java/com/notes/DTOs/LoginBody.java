package com.notes.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginBody {
    private String email;
    private String password;
}
