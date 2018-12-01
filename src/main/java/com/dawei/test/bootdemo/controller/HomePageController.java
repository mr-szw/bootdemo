package com.dawei.test.bootdemo.controller;

import com.dawei.test.bootdemo.entity.MenuInfo;
import com.dawei.test.bootdemo.entity.UserInfo;
import com.dawei.test.bootdemo.service.UserInfoService;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.dawei.test.bootdemo.entity.ShiroUserInfo.ShiroUser;

/**
 * @author by Dawei on 2018/7/16.
 */
@Controller
public class HomePageController {

    @Resource
    private UserInfoService userInfoService;

    /**
     * 首页信息
     */
    @GetMapping("/main")
    public String homeMainPage(Model model) {
        Map<String, Integer> menuMap = userInfoService.selectAllUserMenuMap();
        UserInfo userInfo = userInfoService.findUserById(ShiroUser().getId());
        Set<MenuInfo> menuInfoSet = userInfo.getMenuInfoSet();
        List<MenuInfo> showMenuList = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(menuInfoSet)) {
            //过滤掉没有 跳转连接的菜单
            List<MenuInfo> menuCollect = menuInfoSet.parallelStream().filter(menuInfo -> StringUtils.isNotBlank(menuInfo.getHref())).collect(Collectors.toList());
            menuCollect.forEach(menuInfo -> {
                Integer count = menuMap.get(menuInfo.getPermission());
                if (count != null) {
                    menuInfo.setDataCount(count);
                    showMenuList.add(menuInfo);
                }
            });
        }
        showMenuList.sort(Comparator.comparing(MenuInfo::getSortNum));
        model.addAttribute("userMenu", showMenuList);
        return "main";
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
