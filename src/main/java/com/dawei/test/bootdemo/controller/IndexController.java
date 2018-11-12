package com.dawei.test.bootdemo.controller;

import com.dawei.test.bootdemo.cache.LocalCacheCaffeine;
import com.dawei.test.bootdemo.pojo.DemoPojo;
import java.util.Date;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author by Dawei on 2018/7/16.
 */
@Controller
public class IndexController {


    @Resource
    private LocalCacheCaffeine localCacheCaffeine;


    @GetMapping(value = "/index")
    public String indexPage(String pageIndex, ModelMap model) {
        System.out.println(pageIndex);

        DemoPojo demoPojo = new DemoPojo();
        demoPojo.setuId(11L);
        demoPojo.setBirthday(new Date());
        demoPojo.setPhoneNum("ddddd");
        localCacheCaffeine.save(demoPojo);
        System.out.println("save");
        DemoPojo demoPojo1 = localCacheCaffeine.get(11L);
        System.out.println("get1");
        System.out.println(demoPojo1);

        localCacheCaffeine.delete(11L);
        System.out.println("delete");
        DemoPojo demoPojo2 = localCacheCaffeine.get(11L);
        System.out.println("get2");

        System.out.println(demoPojo2);
        ModelAndView modelAndView = new ModelAndView();
        model.put("index", "ancc");
        modelAndView.setViewName("indexPage");
        return "indexPage";
    }

    @GetMapping(value = "/index1")
    public ModelAndView indexPage1(String pageIndex, ModelMap model) {
        System.out.println(pageIndex);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("index", "ancc1");

        modelAndView.setViewName("indexPage");
        return modelAndView;
    }


}
