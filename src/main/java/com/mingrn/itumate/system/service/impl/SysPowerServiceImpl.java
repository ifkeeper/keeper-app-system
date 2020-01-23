package com.mingrn.itumate.system.service.impl;

import com.mingrn.itumate.core.AbstractService;
import com.mingrn.itumate.system.domain.SysPower;
import com.mingrn.itumate.system.dto.SysPowerDTO;
import com.mingrn.itumate.system.mapper.SysPowerMapper;
import com.mingrn.itumate.system.service.SysPowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 菜单功能按钮表-ServiceImpl接口实现类
 *
 * @author MinGRn <br > MinGRn97@gmail.com
 * @date 22/01/2020 20:34
 */
@Service
@Transactional
public class SysPowerServiceImpl extends AbstractService<SysPower, SysPowerDTO, String> implements SysPowerService {

    @Autowired
    private SysPowerMapper sysPowerMapper;

}
