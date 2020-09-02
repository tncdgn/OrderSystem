package com.project.ordersystem.validator.util;

import org.hamcrest.Matcher;
import org.hamcrest.number.OrderingComparison;

public final class Validators {

    private Validators() {
    }

    public static <T extends Comparable<T>> Matcher<?> greaterOrEqualTo(T e) {
        return OrderingComparison.greaterThanOrEqualTo(e);
    }
}
