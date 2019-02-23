package com.mingrn.keeper.system.web;

import com.mingrn.keeper.global.result.ResponseMsgUtil;
import com.mingrn.keeper.global.result.Result;
import com.mingrn.keeper.msg.api.MailSendApi;
import com.mingrn.keeper.msg.api.enums.MailTemplateEnums;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/mail")
public class MailController {

    @Resource
    private MailSendApi mailSendApi;


    @PostMapping("/sendTextMessage")
    public Result sendTextMessage(@RequestParam(name = "subject") String subject,
                                  @RequestParam(name = "msg") String msg, @RequestParam(name = "receiver") String... receiver) {
        return mailSendApi.sendTextMessage(subject, msg, receiver);
    }

    @PostMapping("/sendHtmlMessage")
    public Result sendHtmlMessage(@RequestParam(name = "subject") String subject, @RequestParam int type,
                                  @RequestParam String htmlContent, @RequestParam(name = "receiver") String... receiver) {
        if (type == 1) {
            return mailSendApi.sendHtmlMessageWithFtl(subject, MailTemplateEnums.REGISTER_VERIFY, receiver);
        } else {
            return mailSendApi.sendHtmlMessage(subject, htmlContent, receiver);
        }
    }

}
