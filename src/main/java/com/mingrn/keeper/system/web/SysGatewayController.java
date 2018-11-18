package com.mingrn.keeper.system.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mingrn.keeper.base.enums.DeletedEnum;
import com.mingrn.keeper.global.annotation.Checked;
import com.mingrn.keeper.global.annotation.ParamsIsNotNull;
import com.mingrn.keeper.global.result.ResponseMsgUtil;
import com.mingrn.keeper.global.result.Result;
import com.mingrn.keeper.system.domain.SysGateway;
import com.mingrn.keeper.system.service.SysGatewayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/sysGatewayController")
@Api(description = "System Gateway Operate")
public class SysGatewayController {

	@Resource
	private SysGatewayService sysGatewayService;

	@GetMapping("/findList")
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "列表查询")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", dataTypeClass = Integer.class, required = true, defaultValue = "1", name = "pageNumber", value = "页码"),
			@ApiImplicitParam(paramType = "query", dataTypeClass = Integer.class, required = true, defaultValue = "10", name = "sizeSize", value = "每页数量")
	})
	public Result findList(@RequestParam(defaultValue = "1") Integer pageNumber,
						   @RequestParam(defaultValue = "10") Integer sizeSize) throws InterruptedException {

		Integer time = new Random().nextInt(5000);
		Thread.sleep(Long.valueOf(time));
		PageHelper.startPage(pageNumber, sizeSize);
		List<SysGateway> sysGatewayList = sysGatewayService.find();
		PageInfo pageInfo = new PageInfo(sysGatewayList);
		return ResponseMsgUtil.success(pageInfo);
	}

	@Checked
	@PutMapping("/putSysGateway")
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
	public Result put(@ParamsIsNotNull @RequestParam String appName,
					  @ParamsIsNotNull @RequestParam String path,
					  @ParamsIsNotNull @RequestParam String serviceId,
					  @RequestParam(required = false) String url,
					  @ParamsIsNotNull @RequestParam(defaultValue = "false") Boolean retryable,
					  @ParamsIsNotNull @RequestParam(defaultValue = "true") Boolean stripPrefix,
					  @ParamsIsNotNull @RequestParam(defaultValue = "false") Boolean customSensitiveHeaders,
					  @ParamsIsNotNull @RequestParam(defaultValue = "false") Boolean applyEnable,
					  @ParamsIsNotNull @RequestParam(defaultValue = "true") Integer status,
					  @ParamsIsNotNull @RequestParam(defaultValue = "") String description) {


		SysGateway sysGateway = new SysGateway();
		sysGateway.setAppName(appName);
		sysGateway.setPath(path);
		sysGateway.setServiceId(serviceId);
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
		return ResponseMsgUtil.success(null);
	}


	@Checked
	@DeleteMapping("/remove")
	@ApiOperation(value = "主键删除", notes = "支持批量删除")
	@ApiImplicitParam(paramType = "query", dataTypeClass = String.class, required = true, name = "ids", value = "主键：id 或 id1,id2...")
	public Result remove(@ParamsIsNotNull @RequestParam String ids) {
		sysGatewayService.removeByIds(ids);
		return ResponseMsgUtil.success(null);
	}
}
