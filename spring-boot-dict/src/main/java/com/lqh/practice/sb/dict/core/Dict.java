package com.lqh.practice.sb.dict.core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * <p> Dict Entity
 *
 * @author liqh
 * @version 1.0
 * @date 2021/6/3 0003 9:38
 * @since 2021/6/3 0003 9:38
 */
@Entity
@Table(name = "dict")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dict {
    @Id
//    @GeneratedValue
    private Long dicId;

    private String category;

    private String categoryName;

    private String type;

    private String typeName;

    private String enumName;

    private int enumValue;

    private String enumDesc;

    private int status;

    private String creator;

    private String updater;

    @Column(updatable = false)
    @CreationTimestamp
    private Date createTime;

    @UpdateTimestamp
    private Date updateTime;
}
