package lotto.domain.ticket;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import lotto.domain.ball.Ball;
import lotto.domain.rank.Rank;
import lotto.domain.winning.WinningTicket;
import lotto.exception.LottoException;
import lotto.exception.LottoExceptionStatus;

class TicketTest {

    private void ticketExceptionTest(final List<Integer> ticketNumbers, final LottoExceptionStatus exceptionStatus) {
        assertThatThrownBy(() -> new Ticket(ticketNumbers))
                .isInstanceOf(LottoException.class)
                .hasMessageContaining(exceptionStatus.getMessage());
    }

    @DisplayName("로또 번호 묶음은 6개로 구성되어야 합니다.")
    @ParameterizedTest(name = "[{index}] 로또 번호 : {0}")
    @MethodSource("lotto.domain.ticket.provider.TicketTestProvider#provideForNumbersOutOfSizeExceptionTest")
    void ticketOutOfSizeExceptionTest(final List<Integer> ticketNumbers) {
        ticketExceptionTest(ticketNumbers, LottoExceptionStatus.TICKET_NUMBERS_CANNOT_BE_OUT_OF_SIZE);
    }

    @DisplayName("로또 번호는 중복된 숫자로 구성될 수 없습니다.")
    @ParameterizedTest(name = "[{index}] 로또 번호 : {0}")
    @MethodSource("lotto.domain.ticket.provider.TicketTestProvider#provideForNumbersDuplicatedExceptionTest")
    void ticketNumbersDuplicatedExceptionTest(final List<Integer> ticketNumbers) {
        ticketExceptionTest(ticketNumbers, LottoExceptionStatus.TICKET_NUMBERS_CANNOT_BE_DUPLICATED);
    }

    @DisplayName("로또 번호가 기댓값을 지니고 있으면 true를 반환해야 한다.")
    @ParameterizedTest(name = "[{index}] 로또 번호 : {0}, 포함해야 하는 번호 : {1}")
    @MethodSource("lotto.domain.ticket.provider.TicketTestProvider#provideForContainsTrueTest")
    void containsTrueTest(final List<Integer> ticketNumbers, final int targetNumber) {
        final Ticket ticket = new Ticket(ticketNumbers);
        final Ball targetBall = Ball.from(targetNumber);

        assertThat(ticket.contains(targetBall)).isTrue();
    }

    @DisplayName("로또 번호가 기댓값을 지니고 있지 않으면 false를 반환해야 한다.")
    @ParameterizedTest(name = "[{index}] 로또 번호 : {0}, 포함하면 안되는 번호 : {1}")
    @MethodSource("lotto.domain.ticket.provider.TicketTestProvider#provideForContainsFalseTest")
    void containsFalseTest(final List<Integer> ticketNumbers, final int targetNumber) {
        final Ticket ticket = new Ticket(ticketNumbers);
        final Ball targetBall = Ball.from(targetNumber);

        assertThat(ticket.contains(targetBall)).isFalse();
    }

    @DisplayName("다른 로또 번호와의 일치 개수 계산 결과가 기댓값과 일치해야 한다.")
    @ParameterizedTest(name = "[{index}] 로또 번호 : {0}, 일치 개수 : {2}, 비교 대상 로또 번호 : {1}")
    @MethodSource("lotto.domain.ticket.provider.TicketTestProvider#provideForCountMatchesTest")
    void countMatchesTest(final List<Integer> ticketNumbers,
                          final List<Integer> anotherTicketNumbers,
                          final int matchCount) {
        final Ticket ticket = new Ticket(ticketNumbers);
        final Ticket anotherTicket = new Ticket(anotherTicketNumbers);

        assertThat(ticket.countMatches(anotherTicket)).isEqualTo(matchCount);
    }

    @DisplayName("당첨 등수 결과는 기댓값과 일치해야 한다.")
    @ParameterizedTest(name = "[{index}] 로또 번호 : {0}, 당첨 번호 : {1}, 보너스 번호 : {2}, 당첨 등수 : {3}")
    @MethodSource("lotto.domain.ticket.provider.TicketTestProvider#provideForCalculateRankTest")
    void calculateRankTest(final List<Integer> ticketNumbers,
                           final List<Integer> winningNumbers,
                           final int bonusNumber,
                           final Rank expected) {
        final Ticket ticket = new Ticket(ticketNumbers);
        final WinningTicket winningTicket = new WinningTicket(winningNumbers, bonusNumber);
        final Rank rank = winningTicket.calculateRank(ticket).orElse(null);

        assertThat(rank).isEqualTo(expected);
    }

}
