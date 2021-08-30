package com.fxc.algorithm.problem.设计模式._03_工厂模式._3_抽象工厂模式;


import com.fxc.algorithm.problem.设计模式._03_工厂模式._3_抽象工厂模式.AcstractFactory.AbstractFactory;
import com.fxc.algorithm.problem.设计模式._03_工厂模式._3_抽象工厂模式.RealFactory.AppleFactory;
import com.fxc.algorithm.problem.设计模式._03_工厂模式._3_抽象工厂模式.RealFactory.LenovoFactory;
import com.fxc.algorithm.problem.设计模式._03_工厂模式._3_抽象工厂模式.RealFactory.XiaomiFactory;
import com.fxc.algorithm.problem.设计模式._03_工厂模式._3_抽象工厂模式.抽象接口.AppleProduct;
import com.fxc.algorithm.problem.设计模式._03_工厂模式._3_抽象工厂模式.抽象接口.Product;

/**
 * Description：
 */
public class TestAbstractFactory {
    public static void main(String[] args) {
        //  比如当前要创建一个联想电脑和小米手机
        AbstractFactory factory = null;

        //  生产联想电脑
        factory = new LenovoFactory();
        Product computer = factory.createComputer();
        computer.printBrand();
        computer.printProductClass();
        System.out.println("----------------------------");
        //  生产小米手机
        factory = new XiaomiFactory();
        Product phone = factory.createPhone();
        phone.printBrand();
        phone.printProductClass();
        System.out.println("----------------------------");
        //  生产Apple全系列产品
        factory = new AppleFactory();
        AppleProduct product = (AppleProduct) factory.createPhone();
        product.printInfo();
        product = (AppleProduct) factory.createComputer();
        product.printInfo();
        product = (AppleProduct) factory.createTV();
        product.printInfo();
        System.out.println("----------------------------");
    }
}
