package com.project.ordersystem.enums;

public enum OrderItemStatus {

    PAID(2),
    CANCELED(4),
    ACCEPTED(5);

    private int index;

    OrderItemStatus(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
