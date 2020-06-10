package com.lvzhu.mall.biz.service;

import java.util.List;


/**
 * @author lvzhu.
 * Time 2020/6/9 11:02 下午
 * Desc 文件描述
 */
public class CheckVisitor implements NodeVisitor {

    private Boolean hasNode = null;


    public Boolean getHasNode() {
        return hasNode;
    }


    @Override
    public void visit(Node node) {
        List<Node> childNodes = node.getChildNodes();
        if (childNodes!=null&&!childNodes.isEmpty()) {
            this.hasNode = true;
            childNodes.stream().forEach((node1 -> node1.accept(this)));
        }else {
            System.out.println("最后一个节点了："+node.getName());
        }

    }
}
