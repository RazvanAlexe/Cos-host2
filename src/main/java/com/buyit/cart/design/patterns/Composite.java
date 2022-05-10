package com.buyit.cart.design.patterns;

public interface Composite {
    public void operate();
    public void add(Object obj);
    public void remove(Object obj);
    public Object getChild(int index);
}
