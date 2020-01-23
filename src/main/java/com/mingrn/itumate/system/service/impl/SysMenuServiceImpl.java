package com.mingrn.itumate.system.service.impl;

import com.mingrn.itumate.core.AbstractService;
import com.mingrn.itumate.system.domain.SysMenu;
import com.mingrn.itumate.system.dto.SysMenuDTO;
import com.mingrn.itumate.system.mapper.SysMenuMapper;
import com.mingrn.itumate.system.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统菜单表-ServiceImpl接口实现类
 *
 * @author MinGRn <br > MinGRn97@gmail.com
 * @date 22/01/2020 20:34
 */
@Service
@Transactional
public class SysMenuServiceImpl extends AbstractService<SysMenu, SysMenuDTO, String> implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

}
