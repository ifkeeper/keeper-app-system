package com.mingrn.itumate.system.mapper;

import com.mingrn.itumate.core.Mapper;
import com.mingrn.itumate.system.domain.SysMenuPower;
import org.springframework.stereotype.Repository;

/**
 * 系统菜单与功能关联表-Mapper接口类
 *
 * @author MinGRn <br > MinGRn97@gmail.com
 * @date 2020/1/23 14:16
 */
@Repository("sysMenuPowerMapper")
public interface SysMenuPowerMapper extends Mapper<SysMenuPower> {
}