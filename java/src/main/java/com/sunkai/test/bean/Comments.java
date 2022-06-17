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
 * @since 2022-06-17
 */
@Getter
@Setter
public class Comments implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private String content;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer articleId;

    private String createTime;

    private Integer zanNum;


}
