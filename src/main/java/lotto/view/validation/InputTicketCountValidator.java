package lotto.view.validation;

import lotto.exception.LottoException;
import lotto.exception.LottoExceptionStatus;

public class InputTicketCountValidator {

    private InputTicketCountValidator() {
    }

    public static void validateTicketCount(final int totalTicketCount, final int ticketCount) {
        verifyTicketCountNotNegative(ticketCount);
        verifyTotalTicketCountIsMoreThanTicketCount(totalTicketCount, ticketCount);
    }

    private static void verifyTicketCountNotNegative(final int ticketCount) {
        if (ticketCount < 0) {
            throw new LottoException(LottoExceptionStatus.MANUAL_TICKET_COUNT_CANNOT_BE_NEGATIVE);
        }
    }

    private static void verifyTotalTicketCountIsMoreThanTicketCount(final int totalTicketCount, final int ticketCount) {
        if (ticketCount > totalTicketCount) {
            throw new LottoException(LottoExceptionStatus.MANUAL_TICKET_COUNT_CANNOT_BE_TOO_MANY);
        }
    }

}
