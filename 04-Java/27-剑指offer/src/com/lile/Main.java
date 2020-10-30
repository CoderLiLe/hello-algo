package com.lile;

import com.lile.question._01_AssignmentOperator;
enum Type {
    A, B, C
}

public class Main {

    public static void main(String[] args) {
//        System.out.println("剑指Offer");
        _01_AssignmentOperator.test();
    }

    public void draw(Type type) {

    }

    public void draw(Type[] types) {
        for (Type type : types) {
            draw(type);
        }
    }
}
