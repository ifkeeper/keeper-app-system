package com.mingrn.itumate.system.mapper;

import com.mingrn.itumate.core.Mapper;
import com.mingrn.itumate.system.domain.SysMenu;
import org.springframework.stereotype.Repository;

/**
 * 系统菜单表-Mapper接口类
 *
 * @author MinGRn <br > MinGRn97@gmail.com
 * @date 2020/1/23 14:15
 */
@Repository("sysMenuMapper")
public interface SysMenuMapper extends Mapper<SysMenu> {
}