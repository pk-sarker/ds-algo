package com.dsalgo.practice.common;

public class NodeV2 {
    public int val;
    public NodeV2 left;
    public NodeV2 right;
    public NodeV2 next;

    public NodeV2() {}

    public NodeV2(int _val) {
        val = _val;
    }

    public NodeV2(int _val, NodeV2 _left, NodeV2 _right, NodeV2 _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}
