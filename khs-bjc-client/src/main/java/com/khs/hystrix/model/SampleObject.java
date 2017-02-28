package com.khs.hystrix.model;

import java.io.Serializable;

public class SampleObject implements Serializable {

    private static final long serialVersionUID = -2990028729418751273L;

    private int one;
    private String two;
    private String three;
    private int four;
    private String five;

    public SampleObject(int one) {
        this.one = one;
    }

    public int getOne() {
        return one;
    }

    public void setOne(int one) {
        this.one = one;
    }

    public String getTwo() {
        return two;
    }

    public void setTwo(String two) {
        this.two = two;
    }

    public String getThree() {
        return three;
    }

    public void setThree(String three) {
        this.three = three;
    }

    public int getFour() {
        return four;
    }

    public void setFour(int four) {
        this.four = four;
    }

    public String getFive() {
        return five;
    }

    public void setFive(String five) {
        this.five = five;
    }

    @Override
    public String toString() {
        return "MockObject [one=" + one + ", two=" + two + ", three=" + three + ", four=" + four + ", five=" + five + "]";
    }

}
