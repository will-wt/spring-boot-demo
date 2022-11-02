package com.will.demo.ultimate.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Will.WT
 * @date 2022/11/02 10:45
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO implements Serializable {

    private static final long serialVersionUID = -7427177262188297425L;

    private Date gmtCreate;
    private Date gmtModified;
    private Long userId;
    private String name;
    private Integer age;
    private String address;

}
