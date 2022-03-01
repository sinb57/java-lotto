package lotto.exception;

public enum LottoExceptionStatus {

    READER_CANNOT_READ("입력을 읽어들일 수 없습니다."),

    MONEY_MUST_BE_NUMERIC("구입 금액은 숫자여야 합니다."),
    MONEY_CANNOT_BE_ZERO("구입 금액은 0원이 될 수 없습니다."),
    MONEY_MUST_BE_DIVISIBLE("구입 금액은 1000원으로 나누어 떨어져야 합니다."),

    TICKET_COUNT_MUST_BE_NUMERIC("로또 개수는 숫자여야 합니다."),

    TICKET_NUMBERS_CANNOT_BE_OUT_OF_SIZE("로또 번호는 6개로 구성되어야 합니다."),
    TICKET_NUMBERS_CANNOT_BE_DUPLICATED("로또 번호는 중복될 수 없습니다."),

    BALL_NUMBER_MUST_BE_NUMERIC("번호는 숫자여야 합니다."),
    BALL_NUMBER_CANNOT_BE_OUT_OF_RANGE("번호의 범위는 1부터 45까지여야 합니다.");

    private final String message;

    LottoExceptionStatus(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
