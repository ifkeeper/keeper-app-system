package com.mingrn.itumate.system.web;

import com.github.pagehelper.PageHelper;
import com.mingrn.itumate.global.result.ResponseMsgUtil;
import com.mingrn.itumate.global.result.Result;
import com.mingrn.itumate.system.domain.SysMenu;
import com.mingrn.itumate.system.service.SysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 系统菜单-Controller
 *
 * @author MinGRn <br > MinGRn97@gmail.com
 * @date 2020/1/23 22:45
 */
@RestController
@RequestMapping("/menu")
@Api(description = "系统菜单 Api")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 增加
     */
    @PostMapping(consumes = {
            MediaType.APPLICATION_JSON_UTF8_VALUE,
            MediaType.APPLICATION_FORM_URLENCODED_VALUE
    })
    @ApiOperation(value = "新增菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "FFloat", required = true, name = "recover", value = "版本号"),
            @ApiImplicitParam(paramType = "query", dataType = "String", required = true, name = "label", value = "菜单名称"),
            @ApiImplicitParam(paramType = "query", dataType = "String", required = true, name = "parents", value = "父级菜单PKs"),
            @ApiImplicitParam(paramType = "query", dataType = "Boolean", required = true, name = "isLeaf", value = "是否为叶子节点"),
            @ApiImplicitParam(paramType = "query", dataType = "Boolean", required = true, name = "iconFrom", value = "icon来源：true(ElementUI)，false(FontAwesome)"),
            @ApiImplicitParam(paramType = "query", dataType = "String", required = true, name = "iconClass", value = "菜单ICON样式"),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "menuUrl", value = "菜单地址"),
            @ApiImplicitParam(paramType = "query", dataType = "int", required = true, name = "status", value = "菜单状态：1启用 0禁用"),
            @ApiImplicitParam(paramType = "query", dataType = "int", required = true, name = "sort", value = "排序"),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "remark", value = "菜单备注"),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "description", value = "菜单描述信息"),
    })
    public Result add(SysMenu sysMenu) {

        if (sysMenu.getIsLeaf() && StringUtils.isBlank(sysMenu.getMenuUrl())) {
            return ResponseMsgUtil.failure("菜单为叶子节点状态时地址不能为空");
        }

        String parents = sysMenu.getParents().replace(",", "|");
        String parentId = parents.contains("|") ? parents.substring(parents.lastIndexOf("|")) : parents;

        sysMenu.setDeleted(false);
        sysMenu.setParents(parents);
        sysMenu.setParentId(parentId);
        sysMenu.setCreateUser("SYS");
        sysMenu.setCreateTime(new Date());

        sysMenuService.insert(sysMenu);

        return ResponseMsgUtil.success("新增菜单成功");
    }

    /**
     * 修改
     */
    @PutMapping(
            value = "/{id}",
            consumes = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_FORM_URLENCODED_VALUE
            }
    )
    @ApiOperation(value = "通过PK主键修改菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", dataType = "String", required = true, name = "id", value = "PK主键"),
            @ApiImplicitParam(paramType = "query", dataType = "Float", required = true, name = "recover", value = "版本号"),
            @ApiImplicitParam(paramType = "query", dataType = "String", required = true, name = "label", value = "菜单名称"),
            @ApiImplicitParam(paramType = "query", dataType = "String", required = true, name = "parents", value = "父级菜单PKs"),
            @ApiImplicitParam(paramType = "query", dataType = "Boolean", required = true, name = "isLeaf", value = "是否为叶子节点"),
            @ApiImplicitParam(paramType = "query", dataType = "Boolean", required = true, name = "iconFrom", value = "icon来源：true(ElementUI)，false(FontAwesome)"),
            @ApiImplicitParam(paramType = "query", dataType = "String", required = true, name = "iconClass", value = "菜单ICON样式"),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "menuUrl", value = "菜单地址"),
            @ApiImplicitParam(paramType = "query", dataType = "int", required = true, name = "status", value = "菜单状态：1启用 0禁用"),
            @ApiImplicitParam(paramType = "query", dataType = "int", required = true, name = "sort", value = "排序"),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "remark", value = "菜单备注"),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "description", value = "菜单描述信息"),
    })
    public Result update(@PathVariable String id,
                         SysMenu sysMenu) {

        if (sysMenu.getIsLeaf() && StringUtils.isBlank(sysMenu.getMenuUrl())) {
            return ResponseMsgUtil.failure("菜单为叶子节点状态时地址不能为空");
        }

        SysMenu temp = sysMenuService.getById(id);
        if (Objects.isNull(temp)) {
            return ResponseMsgUtil.failure("菜单不存在,无法进行修改操作");
        }

        String parents = sysMenu.getParents().replace(",", "|");
        String parentId = parents.contains("|") ? parents.substring(parents.lastIndexOf("|")) : parents;

        sysMenu.setParents(parents);
        sysMenu.setParentId(parentId);
        sysMenu.setModifyUser("SYS");
        sysMenu.setModifyTime(new Date());
        sysMenu.setRemark(Objects.isNull(sysMenu.getRemark()) ? temp.getRemark() : sysMenu.getRemark());
        sysMenu.setDescription(Objects.isNull(sysMenu.getDescription()) ? temp.getDescription() : sysMenu.getDescription());

        sysMenuService.update(sysMenu, id);

        return ResponseMsgUtil.success("菜单修改成功");
    }

    /**
     * 通过PK主键删除
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "通过PK主键删除")
    @ApiImplicitParam(paramType = "path", dataType = "String", required = true, name = "id", value = "PK主键")
    public Result delete(@PathVariable String id) throws NoSuchFieldException, IllegalAccessException {
        SysMenu sysMenu = sysMenuService.getById(id);
        if (Objects.nonNull(sysMenu)) {
            sysMenu.setDeleted(true);
            sysMenu.setModifyUser("SYS");
            sysMenu.setModifyTime(new Date());
            sysMenuService.update(sysMenu, id);
        }
        return ResponseMsgUtil.success(null, "删除成功");
    }

    /**
     * 通过PK主键删除
     */
    @DeleteMapping("/batch")
    @ApiOperation(value = "通过PK主键批量删除")
    @ApiImplicitParam(paramType = "query", dataType = "String", required = true, allowMultiple = true, name = "pks", value = "PKs主键")
    public Result batchDelete(@RequestParam String[] pks) {
        sysMenuService.batchDelete(pks);
        return ResponseMsgUtil.success(null, "批量删除成功");
    }

    /**
     * 通过PK获取单条数据
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "通过PK获取单条数据")
    @ApiImplicitParam(paramType = "path", dataType = "String", required = true, name = "id", value = "PK主键")
    public Result get(@PathVariable String id) {
        SysMenu sysMenu = sysMenuService.getById(id);
        return ResponseMsgUtil.success(sysMenu);
    }

    /**
     * 通过父级主键与节点类型查询数据
     */
    @GetMapping("/findByParentId/{parentId}")
    @ApiOperation(value = "通过父级主键与节点类型查询数据")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", dataType = "String", required = true, name = "parentId", value = "父级菜单PK"),
            @ApiImplicitParam(paramType = "query", dataType = "Boolean", defaultValue = "false", name = "includeLeaf", value = "是否包括叶子节点")
    })
    public Result findByParentId(@PathVariable String parentId,
                                 @RequestParam(defaultValue = "false") Boolean includeLeaf) {
        List<SysMenu> sysMenuList = sysMenuService.findByParentId(parentId, includeLeaf);
        return ResponseMsgUtil.success(sysMenuList);
    }

    /**
     * 条件分页查询
     */
    @ApiOperation(value = "条件分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "int", required = true, defaultValue = "1", name = "pageNumber", value = "页码"),
            @ApiImplicitParam(paramType = "query", dataType = "int", required = true, defaultValue = "10", name = "pageSize", value = "每页数量"),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "label", value = "菜单名称")
    })
    @GetMapping("/findPageByCondition")
    public Result findPageByCondition(@RequestParam(defaultValue = "1") Integer pageNumber,
                                      @RequestParam(defaultValue = "10") Integer pageSize,
                                      @RequestParam(required = false) String label) {
        PageHelper.startPage(pageNumber, pageSize);
        List<SysMenu> sysMenuList = sysMenuService.findPageByCondition(label);
        return ResponseMsgUtil.success(sysMenuList);
    }
}
