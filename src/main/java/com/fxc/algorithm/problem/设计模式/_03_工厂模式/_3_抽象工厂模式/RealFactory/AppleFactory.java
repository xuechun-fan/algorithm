package com.fxc.algorithm.problem.设计模式._03_工厂模式._3_抽象工厂模式.RealFactory;

import com.fxc.algorithm.problem.设计模式._03_工厂模式._3_抽象工厂模式.AcstractFactory.AbstractFactory;
import com.fxc.algorithm.problem.设计模式._03_工厂模式._3_抽象工厂模式.实体类.AppleTV;
import com.fxc.algorithm.problem.设计模式._03_工厂模式._3_抽象工厂模式.实体类.Mac;
import com.fxc.algorithm.problem.设计模式._03_工厂模式._3_抽象工厂模式.实体类.iPhone;

/**
 * Description：
 */
public class AppleFactory extends AbstractFactory {
    @Override
    public iPhone createPhone() {
        return new iPhone();
    }

    @Override
    public Mac createComputer() {
        return new Mac();
    }

    @Override
    public AppleTV createTV() {
        return new AppleTV();
    }
}
