package com.dawei.test.bootdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author by Dawei on 2018/7/16.
 */
@Controller
public class IndexController {



    @GetMapping(value="/index")
    public String indexPage(String pageIndex, ModelMap model) {
        System.out.println(pageIndex);

        ModelAndView modelAndView = new ModelAndView();
        model.put("index", "ancc");
        /*modelAndView.setViewName("indexPage");*/
        return "indexPage";
    }

    @GetMapping(value="/index1")
    public ModelAndView indexPage1(String pageIndex, ModelMap model) {
        System.out.println(pageIndex);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("index", "ancc1");

        modelAndView.setViewName("indexPage");
        return modelAndView;
    }
}
