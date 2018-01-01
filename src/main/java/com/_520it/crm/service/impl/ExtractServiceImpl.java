package com._520it.crm.service.impl;

import com._520it.crm.creeper.LinkTypeData;
import com._520it.crm.creeper.Rule;
import com._520it.crm.service.IExtractService;
import com.alibaba.druid.util.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2017/11/13.
 *
 * @author NO_ONE
 *         描述:
 */
public class ExtractServiceImpl implements IExtractService {

    @Override
    public List<LinkTypeData> extract(String word) {
        Rule  rule =new Rule("http://news.baidu.com/ns",
                new String[]{"word"},new String[]{word},null,-1,Rule.GET);

        validate(rule);
        List<LinkTypeData> datas = new ArrayList<>();
        LinkTypeData data = null;
        try {
            //解析URL
            String url = rule.getUrl();
            String[] params = rule.getParams();
            String[] values = rule.getValues();
            String resultTagName = rule.getResultTagName();
            int type = rule.getType();
            int method = rule.getRequestMethod();

            Connection connection = Jsoup.connect(url);

            //设设置参数
            if (params != null) {
                for (int i = 0; i < values.length; i++) {
                    connection.data(params[i], values[i]);
                }
            }

            //设置请求类型
            Document document = null;
            switch (method) {
                case Rule.GET:
                    document = connection.timeout(100000).get();
                    break;
                case Rule.POST:
                    document = connection.timeout(100000).post();
                    break;
            }

            Elements results = new Elements();

            //处理返回数据
            switch (type) {
                case Rule.CLASS:
                    results = document.getElementsByClass(resultTagName);
                    break;
                case Rule.ID:
                    Element element = document.getElementById(resultTagName);
                    results.add(element);
                    break;
                case Rule.SELECTION:
                    results = document.select(resultTagName);
                    break;
                default:
                    //当resultTagName为空时默认去body标签
                    if (StringUtils.isEmpty(resultTagName)) {
                        results = document.getElementsByTag("body");
                    }
            }
            for (Element result : results) {
                Elements links = document.getElementsByTag("a");
                for (Element link : links) {
                    //必要的筛选
                    String linkHref = link.attr("href");
                    String linkText = link.text();

                    data = new LinkTypeData();
                    data.setLinkHref(linkHref);
                    data.setLinkText(linkText);
                    datas.add(data);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return datas;
    }

    private void validate(Rule rule) {
        String url = rule.getUrl();
        if (StringUtils.isEmpty(url)) {
            throw new RuntimeException("URL不能为空");
        } else if (!url.startsWith("http://")) {
            throw new RuntimeException("请求的URL格式不正确");
        }

        if (rule.getParams() != null && rule.getValues() != null) {
            if (rule.getValues().length != rule.getParams().length) {
                throw new RuntimeException("参数键值个数不匹配");
            }
        }
    }


    @Test
    public void testBaidu() throws  Exception{
        Rule  rule =new Rule("http://news.baidu.com/ns",
                new String[]{"word"},new String[]{"支付宝"},null,-1,Rule.GET);
        List<LinkTypeData> extracts = new ExtractServiceImpl().extract("支付宝");
        printf(extracts);
    }

    private void printf(List<LinkTypeData> extracts) {
        for (LinkTypeData data : extracts){
            System.out.println(data.getLinkHref());
            System.out.println(data.getLinkText());
            System.out.println("************************************************************");
        }
    }
}
/**
 * 错误 :
 * 总结 :
 */