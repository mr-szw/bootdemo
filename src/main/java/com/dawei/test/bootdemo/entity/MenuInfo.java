package com.dawei.test.bootdemo.entity;

/**
 * @author Dawei 2018/11/14
 */
public class MenuInfo {

    private Long id;
    /* 权限标识 */
    private String permission;
    /* 餐单名称 */
    private String menuName;
    /* 图像标识 */
    private String icon;
    /* 链接地址  */
    private String href;
    /* 打开方式   */
    private String target;
    /* 是否显示  */
    private Integer isShow;

    private String bgColor;

    /* 排序顺序权重 */
    private Integer sortNum;


    /*  数据量 */
    private Integer dataCount;

    public MenuInfo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public Integer getDataCount() {
        return dataCount;
    }

    public void setDataCount(Integer dataCount) {
        this.dataCount = dataCount;
    }

    public Integer getSortNum() {
        return sortNum;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }
}
