package com.buyit.cart.DTOs;

import com.buyit.cart.iterator.Iterator;
import com.buyit.cartItem.DTOs.CartItemDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CartDTO implements Iterator {
    private List<CartItemDTO> items;
    private double totalPrice;
    private int currIndex;

    public CartDTO() {
        items = new ArrayList<>();
        currIndex = 0;
    }

    public CartDTO(List<CartItemDTO> items, double totalPrice) {
        this.items = items;
        this.totalPrice = totalPrice;
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
        currIndex = 0;
    }
}
