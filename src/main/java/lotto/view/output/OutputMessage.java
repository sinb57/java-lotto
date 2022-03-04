package lotto.view.output;

public enum OutputMessage {

    REQUEST_MONEY("구입금액을 입력해주세요."),
    REQUEST_MANUAL_TICKET_COUNT("수동으로 구매할 로또 수를 입력해 주세요."),
    REQUEST_MANUAL_TICKETS("수동으로 구매할 번호를 입력해 주세요."),
    REQUEST_WINNING_NUMBERS("지난 주 당첨 번호를 입력해 주세요."),
    REQUEST_BONUS_NUMBER("보너스 볼을 입력해 주세요."),

    EMPTY_STRING(""),
    TITLE_OF_ANALYSIS("당첨 통계"),
    DIVIDING_LINE("---------"),

    TICKET_COUNT_FORMAT("수동으로 %d장, 자동으로 %d장을 구매했습니다."),
    PROFIT_RAGE_FORMAT("총 수익률은 %.2f입니다."),
    TICKET_FORMAT("[%s]"),
    ANALYSIS_FORMAT("%d개 일치 (%d원) - %d개"),
    ANALYSIS_EXTRA_FORMAT("%d개 일치, 보너스 볼 일치 (%d원) - %d개");

    private final String message;

    OutputMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
