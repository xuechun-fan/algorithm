package com.fxc.algorithm.problem.设计模式._03_工厂模式._2_工厂方法模式;

import com.fxc.algorithm.problem.设计模式._03_工厂模式._2_工厂方法模式.entityFactory.CircleFactory;
import com.fxc.algorithm.problem.设计模式._03_工厂模式._2_工厂方法模式.entityFactory.RectangleFactory;
import com.fxc.algorithm.problem.设计模式._03_工厂模式._2_工厂方法模式.entityFactory.SquareFactory;
import com.fxc.algorithm.problem.设计模式._03_工厂模式.abstractInterface.Shape;

/**
 * Description：
 */
public class TestFactoryMethod {
    public static void main(String[] args) {
        Shape s1 = new CircleFactory().getShape();
        s1.draw();
        Shape s2 = new RectangleFactory().getShape();
        s2.draw();
        Shape s3 = new SquareFactory().getShape();
        s3.draw();
    }
}
