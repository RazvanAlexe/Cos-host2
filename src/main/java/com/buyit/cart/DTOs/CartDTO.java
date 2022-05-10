package com.buyit.cart.DTOs;

import com.buyit.cart.design.patterns.Composite;
import com.buyit.cart.design.patterns.Iterator;
import com.buyit.cart.design.patterns.Prototype;
import com.buyit.cartItem.DTOs.CartItemDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CartDTO implements Iterator, Composite, Prototype {
    private List<CartItemDTO> items;
    private double totalPrice;
    private int currIndex;

    public CartDTO() {
        items = new ArrayList<>();
        currIndex = -1;
    }

    public CartDTO(List<CartItemDTO> items, double totalPrice) {
        this.items = items;
        this.totalPrice = totalPrice;
        currIndex = -1;
    }

    @Override
    public Object next() {
        if (this.hasNext()) {
            currIndex++;
            return items.get(currIndex);
        } else {
            throw new IndexOutOfBoundsException("Index " + currIndex + " is out of bounds!");
        }
    }

    @Override
    public boolean hasNext() {
        return currIndex + 1 < items.size();
    }

    @Override
    public void reset() {
        currIndex = -1;
    }

    @Override
    public void operate() {
        for(CartItemDTO item : items){
            item.operate();
        }
    }

    @Override
    public void add(Object obj) {
        items.add((CartItemDTO) obj);
    }

    @Override
    public void remove(Object obj) {
        items.remove((CartItemDTO) obj);
    }

    @Override
    public Object getChild(int index) {
        return items.get(index);
    }

    @Override
    public Object clone() {
        List<CartItemDTO> itemsClone = new ArrayList<>();
        for(CartItemDTO item : items){
            itemsClone.add(new CartItemDTO(item));
        }
        return new CartDTO(itemsClone, totalPrice);
    }
}
