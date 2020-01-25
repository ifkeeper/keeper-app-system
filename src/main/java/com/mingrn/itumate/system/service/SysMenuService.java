package com.mingrn.itumate.system.service;

import com.mingrn.itumate.core.Service;
import com.mingrn.itumate.system.domain.SysMenu;
import com.mingrn.itumate.system.dto.SysMenuDTO;

import java.util.List;

/**
 * 系统菜单表-Service接口类
 *
 * @author MinGRn <br > MinGRn97@gmail.com
 * @date 22/01/2020 20:34
 */
public interface SysMenuService extends Service<SysMenu, SysMenuDTO, String> {

    /**
     * 通过主键查询数据
     *
     * @param id 主键
     * @return SysMenu
     */
    SysMenu getById(String id);

    /**
     * 通过父级主键与节点类型查询数据
     *
     * @param parentId    父级菜单PK主键
     * @param includeLeaf 是否包括叶子节点
     * @return SysMenu
     */
    List<SysMenu> findByParentId(String parentId, boolean includeLeaf);

    /**
     * 条件分页查询
     *
     * @param label 菜单名称
     * @return SysMenu
     */
    List<SysMenu> findPageByCondition(String label);
}
