package com.mingrn.itumate.system.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * 系统菜单表
 *
 * @author MinGRn <br > MinGRn97@gmail.com
 * @date 2020/1/23 14:12
 */
@Setter
@Getter
@ToString
@Table(name = "sys_menu")
public class SysMenu implements Serializable {
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
     * 菜单名称
     */
    private String label;

    /**
     * 父级菜单ID
     */
    @Column(name = "parent_id")
    private String parentId;

    /**
     * 父级菜单IDS
     */
    private String parents;

    /**
     * 是否为叶子节点：1叶子节点 2目录节点
     */
    @Column(name = "is_leaf")
    private Boolean isLeaf;

    /**
     * icon来源：1ElementUI，0FontAwesome
     */
    @Column(name = "icon_from")
    private Boolean iconFrom;

    /**
     * 菜单ICON样式
     */
    @Column(name = "icon_class")
    private String iconClass;

    /**
     * 菜单地址
     */
    @Column(name = "menu_url")
    private String menuUrl;

    /**
     * 菜单状态：1启用 0禁用
     */
    private Integer status;

    /**
     * 删除状态：1：删除 未删除
     */
    private Boolean deleted;

    /**
     * 排序
     */
    private Integer sort;

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
     * 创建用户ID
     */
    @Column(name = "create_user")
    private String createUser;

    /**
     * 修改用户ID
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