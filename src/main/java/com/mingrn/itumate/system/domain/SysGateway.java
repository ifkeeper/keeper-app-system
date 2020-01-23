package com.mingrn.itumate.system.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * 系统网关表
 *
 * @author MinGRn <br > MinGRn97@gmail.com
 * @date 2020/1/23 14:11
 */
@Setter
@Getter
@ToString
@Table(name = "sys_gateway")
public class SysGateway implements Serializable {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 网关应用名称
     */
    @Column(name = "app_name")
    private String appName;

    /**
     * 路由规则，如：/hello-api/**
     */
    private String path;

    /**
     * 服务id，如：hello-service。与 url 只能存在一个。
     */
    @Column(name = "service_id")
    private String serviceId;

    /**
     * 服务地址，如：http://localhost:8080。与 服务ID 只能存在一个
     */
    private String url;

    /**
     * 是否开启重试机制。0：FALSE，1：TRUE
     */
    private Boolean retryable;

    /**
     * 是否剥离路由前缀。0：FALSE，1：TRUE
     */
    @Column(name = "strip_prefix")
    private Boolean stripPrefix;

    /**
     * 是否开启敏感头信息。0：FALSE，1：TRUE
     */
    @Column(name = "custom_sensitive_headers")
    private Boolean customSensitiveHeaders;

    /**
     * 是否立即启用路由。0：FALSE，1：TRUE
     */
    @Column(name = "apply_enable")
    private Boolean applyEnable;

    /**
     * 状态。1：启用，2：禁用
     */
    private Integer status;

    /**
     * 是否已删除。1：正常，2：删除
     */
    private Integer deleted;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 说明
     */
    private String description;

    private static final long serialVersionUID = 1L;
}