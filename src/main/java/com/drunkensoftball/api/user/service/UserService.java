package com.drunkensoftball.api.user.service;

import com.drunkensoftball.api.user.domain.User;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

@Validated
public interface UserService {

    User createUser(@NotBlank(message = "Username is required") String username,
                    String displayName,
                    String email);


}
