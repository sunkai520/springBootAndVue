package com.sunkai.test.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author sunkai
 * @since 2021-12-31
 */
@Getter
@Setter
public class Bklist implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;

    private String des;

    private String code;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer userid;

    private String imageUrl;


}
