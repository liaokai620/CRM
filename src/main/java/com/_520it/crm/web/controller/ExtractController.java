package com._520it.crm.web.controller;

import com._520it.crm.creeper.LinkTypeData;
import com._520it.crm.service.IExtractService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created on 2017/11/14.
 *
 * @author NO_ONE
 *         描述:
 */
@Controller
@RequestMapping("/extract")
public class ExtractController {

    private IExtractService extractService;

    @RequestMapping("creep")
    public List<LinkTypeData> creep(String word){
        return extractService.extract(word);
    }

}
/**
 * 错误 :
 * 总结 :
 */