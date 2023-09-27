package com.desperado.courier.entities.courier;
public enum CourierType {
    FOOT, BIKE, AUTO;

    public static int getCoefficientOfEarnings(CourierType courierType) {
        return switch (courierType) {
            case FOOT -> 2;
            case BIKE -> 3;
            case AUTO -> 4;
        };
    }

    public static int getCoefficientOfRating(CourierType courierType) {
        return switch (courierType) {
            case FOOT -> 3;
            case BIKE -> 2;
            case AUTO -> 1;
        };
    }
}
