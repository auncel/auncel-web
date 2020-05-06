package dev.yidafu.auncel.web.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TagDto {
    private int id;

    private String value;

    private Date createdAt;

    private Date updatedAt;
}
