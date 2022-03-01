package lotto.domain.ticket;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import lotto.domain.ticket.generator.CustomTicketGenerator;
import lotto.domain.winning.Rank;
import lotto.domain.winning.WinningTicket;

class TicketManagerTest {

    private final CustomTicketGenerator customTicketGenerator = new CustomTicketGenerator();

    @DisplayName("부족한 개수만큼 로또를 추가로 생성해야 한다.")
    @ParameterizedTest(name = "[{index}] 총 로또 개수 : {2}")
    @MethodSource("lotto.domain.ticket.provider.TicketManagerTestProvider#provideForGenerateTest")
    void generateTicketsSizeCheckTest(final List<Ticket> preparedTickets,
                                      final List<Ticket> generatedTickets,
                                      final int totalTicketCount) {
        customTicketGenerator.initTickets(generatedTickets);
        final TicketManager ticketManager = TicketManager.generateTickets(
                totalTicketCount, Tickets.generateTickets(preparedTickets), customTicketGenerator);
        assertThat(ticketManager.getSize()).isEqualTo(totalTicketCount);
    }

    @DisplayName("주어진 로또 번호와 생성된 로또 번호는 생성 당시의 값을 지니고 있어야 한다.")
    @ParameterizedTest(name = "[{index}] 총 로또 개수 : {2}")
    @MethodSource("lotto.domain.ticket.provider.TicketManagerTestProvider#provideForGenerateTest")
    void generateTicketsValueCheckTest(final List<Ticket> preparedTickets,
                                       final List<Ticket> generatedTickets,
                                       final int totalTicketCount) {
        customTicketGenerator.initTickets(generatedTickets);
        final TicketManager ticketManager = TicketManager.generateTickets(
                totalTicketCount, Tickets.generateTickets(preparedTickets), customTicketGenerator);
        compareTickets(ticketManager.getPreparedTickets(), preparedTickets);
        compareTickets(ticketManager.getGeneratedTickets(), generatedTickets);
    }

    private void compareTickets(final List<Ticket> actualTickets, final List<Ticket> expectedTickets) {
        for (int i = 0; i < actualTickets.size(); i++) {
            final List<Integer> actualBallNumbers = actualTickets.get(i).getBallNumbers();
            final List<Integer> expectedBallNumbers = expectedTickets.get(i).getBallNumbers();
            assertThat(actualBallNumbers).isEqualTo(expectedBallNumbers);
        }
    }

    @DisplayName("계산된 당첨 등수 목록은 기댓값과 일치해야 한다.")
    @ParameterizedTest(name = "[{index}] 당첨 등수 목록 : {4}")
    @MethodSource("lotto.domain.ticket.provider.TicketManagerTestProvider#provideForCalculateRanksTest")
    void calculateRanksTest(final List<Integer> winningNumbers,
                            final int bonusNumber,
                            final List<Ticket> preparedTickets,
                            final List<Ticket> generatedTickets,
                            final List<Rank> expected) {
        customTicketGenerator.initTickets(generatedTickets);
        final int totalTicketCount = preparedTickets.size() + generatedTickets.size();
        final TicketManager ticketManager = TicketManager.generateTickets(
                totalTicketCount, Tickets.generateTickets(preparedTickets), customTicketGenerator);
        final WinningTicket winningTicket = new WinningTicket(winningNumbers, bonusNumber);

        final List<Rank> actual = ticketManager.calculateRanks(winningTicket);
        assertThat(actual).isEqualTo(expected);
    }

}