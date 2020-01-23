package com.mingrn.itumate.system.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * 菜单功能按钮表
 *
 * @author MinGRn <br > MinGRn97@gmail.com
 * @date 2020/1/23 14:12
 */
@Setter
@Getter
@ToString
@Table(name = "sys_power")
public class SysPower implements Serializable {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 版本号
     */
    private Float recover;

    /**
     * 按钮名称
     */
    private String label;

    /**
     * icon来源：1ElementUI，0FontAwesome
     */
    @Column(name = "icon_from")
    private Boolean iconFrom;

    /**
     * 按钮ICON样式
     */
    @Column(name = "icon_class")
    private String iconClass;

    /**
     * 按钮状态：1启用 0禁用
     */
    private Integer status;

    /**
     * 删除状态：1删除 0未删除
     */
    private Boolean deleted;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "modify_time")
    private Date modifyTime;

    /**
     * 创建用户
     */
    @Column(name = "create_user")
    private String createUser;

    /**
     * 修改用户
     */
    @Column(name = "modify_user")
    private String modifyUser;

    /**
     * 备注
     */
    private String remark;

    /**
     * 描述说明
     */
    private String description;

    private static final long serialVersionUID = 1L;
}