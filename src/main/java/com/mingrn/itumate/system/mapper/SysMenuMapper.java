package com.mingrn.itumate.system.mapper;

import com.mingrn.itumate.core.Mapper;
import com.mingrn.itumate.system.domain.SysMenu;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 系统菜单表-Mapper接口类
 *
 * @author MinGRn <br > MinGRn97@gmail.com
 * @date 2020/1/23 14:15
 */
@Repository("sysMenuMapper")
public interface SysMenuMapper extends Mapper<SysMenu> {

    /**
     * 通过主键查询数据
     *
     * @param id 主键
     * @return SysMenu
     */
    SysMenu getById(String id);


    /**
     * 通过父级主键与节点类型查询数据
     * id: PK主键
     * includeLeaf: 是否包括叶子节点
     *
     * @param params 参数
     * @return SysMenu Of List
     */
    List<SysMenu> findByParentId(Map<String, Object> params);
}