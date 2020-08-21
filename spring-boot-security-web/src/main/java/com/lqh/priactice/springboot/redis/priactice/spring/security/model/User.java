package com.lqh.priactice.springboot.redis.priactice.spring.security.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.lqh.priactice.springboot.redis.priactice.spring.security.validate.GroupAdd;
import com.lqh.priactice.springboot.redis.priactice.spring.security.validate.GroupUpdate;
import com.lqh.priactice.springboot.redis.priactice.spring.security.validate.ValueIn;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

/**
 * <p> 类描述: User
 *
 * @author qhlee
 * @version 1.0
 * @date 2020/07/08 07:56
 * @since 2020/07/08 07:56
 */
@Entity
@Table(name="t_user")
public class User {
    public interface UserSimpleView {};
    public interface UserDetailView extends UserSimpleView{};

    @Id
    @Column(columnDefinition = "bigint comment '主键'")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "更新时id不能为空", groups = GroupUpdate.class)
    private Long id;

    @Column(columnDefinition = "varchar(100) not null comment '姓名'")
    @NotBlank(message = "姓名不能为空", groups = {GroupAdd.class})
    private String name;

    @Column(columnDefinition = "varchar(20) comment '密码'")
    @NotBlank(message = "密码不能为空", groups = {GroupAdd.class})
    private String password;

    @ValueIn(value={"23", "45"}, message = "不是一个有效cityId", groups = {GroupAdd.class})
    private int cityId;

    @Column(name = "birth_date", columnDefinition = "datetime comment '生日'")
    @Past
    private Date birthDate;

    @Column(updatable = false, name="create_time", columnDefinition = "datetime comment '创建时间'")
    @CreationTimestamp
    private Date createTime;

    @UpdateTimestamp
    @Column(name="update_time", columnDefinition = "datetime comment '更新时间'")
    private Date updateTime;


    @JsonView(UserSimpleView.class)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonView(UserSimpleView.class)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @JsonView(UserSimpleView.class)
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @JsonView(UserSimpleView.class)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonView(UserDetailView.class)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonView(UserSimpleView.class)
    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    @JsonView(UserSimpleView.class)
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
