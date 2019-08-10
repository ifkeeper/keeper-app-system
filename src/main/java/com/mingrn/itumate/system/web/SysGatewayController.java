package com.mingrn.itumate.system.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mingrn.itumate.base.enums.DeletedEnum;
import com.mingrn.itumate.core.PaginationUtils;
import com.mingrn.itumate.global.annotation.Checked;
import com.mingrn.itumate.global.annotation.ParamsIsNotNull;
import com.mingrn.itumate.global.result.ResponseMsgUtil;
import com.mingrn.itumate.global.result.Result;
import com.mingrn.itumate.system.domain.SysGateway;
import com.mingrn.itumate.system.service.SysGatewayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 系统网关 API 操作类
 *
 * @author MinGRn <br > MinGRn97@gmail.com
 * @date 19/11/2018 19:31
 */
@RestController
@RequestMapping("/sys/gateway")
@Api(description = "System Gateway Operate")
public class SysGatewayController {

    @Resource
    private SysGatewayService sysGatewayService;

    @SuppressWarnings("unchecked")
    @GetMapping("/findList")
    @ApiOperation(value = "列表查询")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataTypeClass = Integer.class, required = true, defaultValue = "1", name = "pageNumber", value = "页码"),
            @ApiImplicitParam(paramType = "query", dataTypeClass = Integer.class, required = true, defaultValue = "10", name = "pageSize", value = "每页数量")
    })
    public Result findList(@RequestParam(defaultValue = "1") Integer pageNumber,
                           @RequestParam(defaultValue = "10") Integer pageSize) {

        PageHelper.startPage(pageNumber, pageSize);
        List<SysGateway> sysGatewayList = sysGatewayService.find();
        return ResponseMsgUtil.success(PaginationUtils.newSingleTablePagination(sysGatewayList));
    }

    @Checked
    @PutMapping("/put")
    @ApiOperation(value = "新增路由")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", required = true, name = "appName", value = "网关应用名称"),
            @ApiImplicitParam(paramType = "query", dataType = "String", required = true, name = "path", value = "路由规则。如:/hello-api/**"),
            @ApiImplicitParam(paramType = "query", dataType = "String", required = true, name = "serviceId", value = "服务id。如：hello-service。与 url 只能存在一个。"),
            @ApiImplicitParam(paramType = "query", dataType = "String", required = false, name = "url", value = "服务地址。如：http://localhost:8080。与 服务ID 只能存在一个"),
            @ApiImplicitParam(paramType = "query", dataType = "Boolean", required = true, name = "retryable", value = "是否开启重试机制"),
            @ApiImplicitParam(paramType = "query", dataType = "Boolean", required = true, name = "stripPrefix", value = "是否剥离路由前缀，统一剥离"),
            @ApiImplicitParam(paramType = "query", dataType = "Boolean", required = true, name = "customSensitiveHeaders", value = "是否开启敏感头信息"),
            @ApiImplicitParam(paramType = "query", dataType = "Boolean", required = true, name = "applyEnable", value = "是否立即启用路由"),
            @ApiImplicitParam(paramType = "query", dataType = "String", required = true, name = "status", value = "状态。1：启用，2：禁用"),
            @ApiImplicitParam(paramType = "query", dataType = "String", required = true, name = "description", value = "说明")

    })
    public Result put(@ParamsIsNotNull @RequestParam String path,
                      @ParamsIsNotNull @RequestParam String appName,
                      @ParamsIsNotNull @RequestParam Integer status,
                      @ParamsIsNotNull @RequestParam String serviceId,
                      @ParamsIsNotNull @RequestParam Boolean retryable,
                      @ParamsIsNotNull @RequestParam String description,
                      @ParamsIsNotNull @RequestParam Boolean stripPrefix,
                      @ParamsIsNotNull @RequestParam Boolean applyEnable,
                      @ParamsIsNotNull @RequestParam Boolean customSensitiveHeaders,
                      @RequestParam(required = false) String url) {

        SysGateway sysGateway = new SysGateway();
        sysGateway.setAppName(appName);
        sysGateway.setPath(path);
        sysGateway.setServiceId(serviceId.toUpperCase());
        sysGateway.setUrl(url);
        sysGateway.setRetryable(retryable);
        sysGateway.setStripPrefix(stripPrefix);
        sysGateway.setCustomSensitiveHeaders(customSensitiveHeaders);
        sysGateway.setApplyEnable(applyEnable);
        sysGateway.setStatus(status);
        sysGateway.setCreateTime(new Date());
        sysGateway.setDescription(description);
        sysGateway.setDeleted(DeletedEnum.NORMAL.getCode());

        sysGatewayService.insert(sysGateway);
        return ResponseMsgUtil.success();
    }


    @Checked
    @DeleteMapping("/remove")
    @ApiOperation(value = "主键删除", notes = "支持批量删除")
    @ApiImplicitParam(paramType = "query", dataTypeClass = String.class, required = true, name = "ids", value = "主键：id 或 id1,id2...")
    public Result remove(@ParamsIsNotNull String ids) {

        sysGatewayService.removeByIds(ids);
        return ResponseMsgUtil.success();
    }


    @Checked
    @PostMapping("/post")
    @ApiOperation(value = "更新网关", notes = "通过主键更新网关")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", required = true, name = "id", value = "主键"),
            @ApiImplicitParam(paramType = "query", dataType = "String", required = true, name = "appName", value = "网关应用名称"),
            @ApiImplicitParam(paramType = "query", dataType = "String", required = true, name = "path", value = "路由规则。如:/hello-api/**"),
            @ApiImplicitParam(paramType = "query", dataType = "String", required = true, name = "serviceId", value = "服务id。如：hello-service。与 url 只能存在一个。"),
            @ApiImplicitParam(paramType = "query", dataType = "String", required = false, name = "url", value = "服务地址。如：http://localhost:8080。与 服务ID 只能存在一个"),
            @ApiImplicitParam(paramType = "query", dataType = "Boolean", required = true, name = "retryable", value = "是否开启重试机制"),
            @ApiImplicitParam(paramType = "query", dataType = "Boolean", required = true, name = "stripPrefix", value = "是否剥离路由前缀，统一剥离"),
            @ApiImplicitParam(paramType = "query", dataType = "Boolean", required = true, name = "customSensitiveHeaders", value = "是否开启敏感头信息"),
            @ApiImplicitParam(paramType = "query", dataType = "Boolean", required = true, name = "applyEnable", value = "是否立即启用路由"),
            @ApiImplicitParam(paramType = "query", dataType = "String", required = true, name = "status", value = "状态。1：启用，2：禁用"),
            @ApiImplicitParam(paramType = "query", dataType = "String", required = true, name = "description", value = "说明")

    })
    public Result post(@ParamsIsNotNull @RequestParam String id,
                       @ParamsIsNotNull @RequestParam String path,
                       @ParamsIsNotNull @RequestParam String appName,
                       @ParamsIsNotNull @RequestParam String serviceId,
                       @ParamsIsNotNull @RequestParam Boolean retryable,
                       @ParamsIsNotNull @RequestParam Boolean applyEnable,
                       @ParamsIsNotNull @RequestParam Boolean stripPrefix,
                       @ParamsIsNotNull @RequestParam Boolean customSensitiveHeaders,
                       @ParamsIsNotNull @RequestParam Integer status, @RequestParam(required = false) String url,
                       @ParamsIsNotNull @RequestParam String description) throws NoSuchFieldException, IllegalAccessException {

        SysGateway sysGateway = new SysGateway();
        sysGateway.setAppName(appName);
        sysGateway.setPath(path);
        sysGateway.setServiceId(serviceId.toUpperCase());
        sysGateway.setUrl(url);
        sysGateway.setRetryable(retryable);
        sysGateway.setStripPrefix(stripPrefix);
        sysGateway.setCustomSensitiveHeaders(customSensitiveHeaders);
        sysGateway.setApplyEnable(applyEnable);
        sysGateway.setStatus(status);
        sysGateway.setDescription(description);

        sysGatewayService.update(sysGateway, id);
        return ResponseMsgUtil.success();
    }
}
