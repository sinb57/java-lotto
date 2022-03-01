package lotto.utils;

public enum MoneyUnit {

    DIVIDING_UNIT(1000);

    private final int unit;

    MoneyUnit(final int unit) {
        this.unit = unit;
    }

    public static boolean isNotDivisible(final int money) {
        return (money % DIVIDING_UNIT.unit) != 0;
    }

    public static int divide(final int money) {
        return money / DIVIDING_UNIT.unit;
    }

    public static int multiple(final int number) {
        return number * DIVIDING_UNIT.unit;
    }

}