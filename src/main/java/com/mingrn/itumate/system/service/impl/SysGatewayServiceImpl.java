package com.mingrn.itumate.system.service.impl;

import com.mingrn.itumate.system.mapper.SysGatewayMapper;
import com.mingrn.itumate.system.domain.SysGateway;
import com.mingrn.itumate.system.service.SysGatewayService;
import com.mingrn.itumate.system.dto.SysGatewayDTO;
import com.mingrn.itumate.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.lang.String;

import javax.annotation.Resource;

/**
 * 系统网关-ServiceImpl接口实现类
 * @author MinGRn <br > MinGRn97@gmail.com
 * @date 16/11/2018 20:42
 */
@Service
@Transactional
public class SysGatewayServiceImpl extends AbstractService<SysGateway, SysGatewayDTO, String> implements SysGatewayService {
    @Resource
    private SysGatewayMapper sysGatewayMapper;

}
