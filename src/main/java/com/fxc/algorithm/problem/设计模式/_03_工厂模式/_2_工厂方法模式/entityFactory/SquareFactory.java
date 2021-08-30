package com.fxc.algorithm.problem.设计模式._03_工厂模式._2_工厂方法模式.entityFactory;

import com.fxc.algorithm.problem.设计模式._03_工厂模式._2_工厂方法模式.abstractFactory.AbstractFactory;
import com.fxc.algorithm.problem.设计模式._03_工厂模式.abstractInterface.Shape;
import com.fxc.algorithm.problem.设计模式._03_工厂模式.entities.Square;

/**
 * Description：
 */
public class SquareFactory extends AbstractFactory {
    @Override
    public Shape getShape() {
        return new Square();
    }
}
