package com.lvzhu.mall.biz.service;


import java.util.ArrayList;
import java.util.Arrays;


/**
 * @author lvzhu.
 * Time 2020/6/9 11:18 下午
 * Desc 文件描述
 */
public class TestNode {

    public static void main(String[] args) {
        Node node22 = new Node();
        node22.setId(2L);
        node22.setName("节点A-1");
        node22.setParentId(1L);


        Node node33 = new Node();
        node33.setId(3L);
        node33.setName("节点A-2");
        node33.setParentId(1L);


        Node node = new Node();
        node.setId(1L);
        node.setName("节点A");
        node.setParentId(0L);
        node.setChildNodes(Arrays.asList(node22,node33));

         CheckVisitor checkVisitor = new CheckVisitor();
         checkVisitor.visit(node);
         System.out.println("子节点："+checkVisitor.getHasNode());

        RemoveVisitor removeVisitor = new RemoveVisitor();
        removeVisitor.visit(node);


    }
}
