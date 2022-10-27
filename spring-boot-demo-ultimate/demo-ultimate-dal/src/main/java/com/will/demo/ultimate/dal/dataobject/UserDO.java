package com.will.demo.ultimate.dal.dataobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Will.WT
 * @date 2022/10/18 21:51
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDO {

    private Long id;
    private Date gmtCreate;
    private Date gmtModified;
    private String name;
    private Integer age;
    private String address;

}
