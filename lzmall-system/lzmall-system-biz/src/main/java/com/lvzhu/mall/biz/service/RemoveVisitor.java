package com.lvzhu.mall.biz.service;

import java.util.List;


/**
 * @author lvzhu.
 * Time 2020/6/9 11:07 下午
 * Desc 文件描述
 */
public class RemoveVisitor implements NodeVisitor {

    @Override
    public void visit(Node node) {
        final List<Node> childNodes = node.getChildNodes();
        if(childNodes!=null){
            childNodes.stream().forEach((node1 -> node1.accept(this)));
            System.out.println("子节点删除完成再删父节点 父节点为："+node.getName());

        }else {
            System.out.println("没有子节点要删除当前节点当前节点名称："+node.getName());
        }
    }
}
