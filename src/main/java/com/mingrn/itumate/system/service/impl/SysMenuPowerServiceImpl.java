package com.mingrn.itumate.system.service.impl;

import com.mingrn.itumate.core.AbstractService;
import com.mingrn.itumate.system.domain.SysMenuPower;
import com.mingrn.itumate.system.dto.SysMenuPowerDTO;
import com.mingrn.itumate.system.mapper.SysMenuPowerMapper;
import com.mingrn.itumate.system.service.SysMenuPowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统菜单与功能关联表-ServiceImpl接口实现类
 *
 * @author MinGRn <br > MinGRn97@gmail.com
 * @date 22/01/2020 20:34
 */
@Transactional
@Service
public class SysMenuPowerServiceImpl extends AbstractService<SysMenuPower, SysMenuPowerDTO, String> implements SysMenuPowerService {

    @Autowired
    private SysMenuPowerMapper sysMenuPowerMapper;

}
