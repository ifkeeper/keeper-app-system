package com.mingrn.itumate.system.web;

import com.mingrn.itumate.global.result.ResponseMsgUtil;
import com.mingrn.itumate.global.result.Result;
import com.mingrn.itumate.system.domain.SysMenu;
import com.mingrn.itumate.system.service.SysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
    @PostMapping("/post")
    public Result post(@RequestParam Float recover,
                       @RequestParam String label,
                       @RequestParam String parents,
                       @RequestParam Boolean isLeaf,
                       @RequestParam Boolean iconFrom,
                       @RequestParam String iconClass,
                       @RequestParam String menuUrl,
                       @RequestParam Integer status,
                       @RequestParam Integer sort,
                       @RequestParam String remark,
                       @RequestParam String description) {

        parents = parents.replace(",", "|");
        String parentId = parents.substring(parents.lastIndexOf("|"));

        SysMenu sysMenu = new SysMenu();
        sysMenu.setRecover(recover);
        sysMenu.setLabel(label);
        sysMenu.setParentId(parentId);
        sysMenu.setParents(parents);
        sysMenu.setIsLeaf(isLeaf);
        sysMenu.setIconFrom(iconFrom);
        sysMenu.setIconClass(iconClass);
        sysMenu.setMenuUrl(menuUrl);
        sysMenu.setStatus(status);
        sysMenu.setSort(sort);
        sysMenu.setRemark(remark);
        sysMenu.setDescription(description);
        sysMenu.setCreateTime(new Date());
        sysMenu.setCreateUser("SYS");

        sysMenuService.insert(sysMenu);

        return ResponseMsgUtil.success();
    }

    /**
     * 通过PK主键删除
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "通过PK主键删除")
    @ApiImplicitParam(paramType = "path", dataTypeClass = String.class, required = true, name = "id", value = "PK主键")
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
     * 修改
     */
    @PutMapping("/{id}")
    @ApiOperation(value = "通过PK主键修改")
    @ApiImplicitParam(paramType = "path", dataTypeClass = String.class, required = true, name = "id", value = "PK主键")
    public Result update(@PathVariable String id) {
        SysMenu sysMenu = sysMenuService.getById(id);
        if (Objects.nonNull(sysMenu)) {
            // TODO: 2020/1/24 修改
        }
        return ResponseMsgUtil.success();
    }

    /**
     * 通过PK获取单条数据
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "通过PK获取单条数据")
    @ApiImplicitParam(paramType = "path", dataTypeClass = String.class, required = true, name = "id", value = "PK主键")
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
            @ApiImplicitParam(paramType = "query", dataType = "boolean", defaultValue = "false", name = "includeLeaf", value = "是否包括叶子节点")
    })
    public Result findByParentId(@PathVariable String parentId,
                                 @RequestParam(defaultValue = "false") Boolean includeLeaf) {
        List<SysMenu> sysMenuList = sysMenuService.findByParentId(parentId, includeLeaf);
        return ResponseMsgUtil.success(sysMenuList);
    }
}
