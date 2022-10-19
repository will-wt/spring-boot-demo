package com.will.demo.simpleweb.dal.dataobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Will.WT
 * @date 2022/10/18 21:51
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDO {

    private static final Logger logger = LoggerFactory.getLogger(UserDO.class);
    private Long id;
    private String name;
    private Integer age;
    private String address;

}
