package lotto.view.utils;

import java.util.List;

public enum Delimiter {

    COMMA(","),
    SPACE(" ");

    private final String unit;

    Delimiter(final String unit) {
        this.unit = unit;
    }

    public String[] splitWith(final String targetString) {
        final int limitForSplitAllElement = -1;
        return targetString.split(this.unit, limitForSplitAllElement);
    }

    public String appendBehind(final String targetString) {
        return targetString + this.unit;
    }

    public String joinWith(final List<String> strings) {
        return String.join(this.unit + SPACE.unit, strings);
    }

}
