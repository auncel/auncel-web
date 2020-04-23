package dev.yidafu.auncel.web.domain.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import dev.yidafu.auncel.web.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserAuthDto implements Serializable {
    private long id;

    private String identityType;

    private String identifier;

    private String credential;

    private Date createdAt;

    private Date updatedAt;
}
