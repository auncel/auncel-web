package dev.yidafu.auncel.web.domain.dto;

import dev.yidafu.auncel.web.domain.UserRoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable {

    private  Long id;

    private String username;

    private  String realname;

    private String avatar;

    private String status;

    private UserRoleType role;

    private String registerIp;

    private String slogan;

    private String school;

    private Date createdAt;

    private Date updatedAt;
}
