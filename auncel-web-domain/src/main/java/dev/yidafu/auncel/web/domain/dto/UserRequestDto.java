package dev.yidafu.auncel.web.domain.dto;

import dev.yidafu.auncel.web.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto implements Serializable {
    private  Long id;

    private String username;

    private String email;

    private String password;
}
