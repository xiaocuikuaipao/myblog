package com.usst.myblog.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 *
 * @author 小崔
 * @since 2021-04-03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TUser {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private String nickname;

    private String username;
    private String password;
    private String email;

    private String avatar;
    private int type;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
