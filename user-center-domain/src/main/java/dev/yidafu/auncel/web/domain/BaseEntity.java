package dev.yidafu.auncel.web.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity  implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false, columnDefinition = "datetime default current_timestamp")
    private Date updatedAt;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, columnDefinition = "datetime default current_timestamp")
    private Date createdAt;

//    @NotNull
//    private Boolean deleted = false;
}
