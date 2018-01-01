package com._520it.crm.web.controller;

import com._520it.crm.service.IChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created on 2017/11/14.
 *
 * @author NO_ONE
 *         描述:
 */
@Controller
public class ChatController {
    @Autowired
    private IChatService chatService;
    @RequestMapping("sendMsg")
    @ResponseBody
    public Object chat(String word){
        return  chatService.sendMsg(word);
    }
}
/**
 * 错误 :
 * 总结 :
 */