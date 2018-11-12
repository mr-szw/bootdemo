package com.dawei.test.bootdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author by Dawei on 2018/10/18.
 */
@RestController
public class RestFulController {

    @GetMapping(value = "/index2/{pageIndex:\\d+}/{pageSize:\\d*}")
    public String indexPage2(String pageIndex, String pageSize) {
        System.out.println(pageIndex + "   " + pageSize);
        return pageIndex + "   " + pageSize;
    }

    @RequestMapping(value = "/index3/{pageIndex}" , method = RequestMethod.GET)
    public String indexPage3(@PathVariable(value = "pageIndex") Integer pageIndex) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>" + pageIndex );
        return "" + pageIndex ;
    }

    @RequestMapping(value = "/index4/{pageIndex}" , method = RequestMethod.GET)
    public String indexPage4( Integer pageIndex) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>" + pageIndex );
        return "" + pageIndex ;
    }
}
