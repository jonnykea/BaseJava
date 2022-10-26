package com.urise.webapp.decorator;

public class MainDecorator {

    public static void main(String[] args) {
        // Christmas tree with just one Garland
        ChristmasTree tree1 = new Garland(new ChristmasTreeImpl());
        System.out.println(tree1.decorate());

        // Christmas tree with two Garlands and one Bubble lights
        ChristmasTree tree2 = new Garland(new BubbleLights(new Garland(new Garland(new ChristmasTreeImpl()))));
        System.out.println(tree2.decorate());
    }
}


