package com.lvzhu.mall.biz.service;

import java.util.List;


/**
 * @author lvzhu.
 * Time 2020/6/9 10:49 下午
 * Desc 文件描述
 */

public class Node {

    private Long id;

    private String name;

    private Long parentId;

    List<Node> childNodes;


    public  void accept(NodeVisitor visitor){
        visitor.visit(this);
    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public Long getParentId() {
        return parentId;
    }


    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }


    public List<Node> getChildNodes() {
        return childNodes;
    }


    public void setChildNodes(List<Node> childNodes) {
        this.childNodes = childNodes;
    }
}
