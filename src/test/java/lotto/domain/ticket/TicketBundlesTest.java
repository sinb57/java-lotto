package lotto.domain.ticket;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import lotto.domain.rank.Rank;
import lotto.domain.ticket.generator.CustomTicketGenerator;
import lotto.domain.winning.WinningTicket;

class TicketBundlesTest {

    private final CustomTicketGenerator customTicketGenerator = new CustomTicketGenerator();

    @DisplayName("부족한 개수만큼 로또를 추가로 생성해야 한다.")
    @ParameterizedTest(name = "[{index}] 총 로또 개수 : {2}")
    @MethodSource("lotto.domain.ticket.provider.TicketBundlesTestProvider#provideForGenerateTest")
    void generateTicketsSizeCheckTest(final List<Ticket> manualTickets,
                                      final List<Ticket> automaticTickets,
                                      final int totalTicketCount) {
        customTicketGenerator.initTickets(automaticTickets);
        final TicketBundles ticketBundles = TicketBundles.generateTicketBundles(
                totalTicketCount, manualTickets, customTicketGenerator);
        assertThat(ticketBundles.getSize()).isEqualTo(totalTicketCount);
    }

    @DisplayName("주어진 로또 번호와 생성된 로또 번호는 생성 당시의 값을 지니고 있어야 한다.")
    @ParameterizedTest(name = "[{index}] 총 로또 개수 : {2}")
    @MethodSource("lotto.domain.ticket.provider.TicketBundlesTestProvider#provideForGenerateTest")
    void generateTicketsValueCheckTest(final List<Ticket> manualTickets,
                                       final List<Ticket> automaticTickets,
                                       final int totalTicketCount) {
        customTicketGenerator.initTickets(automaticTickets);
        final TicketBundles ticketBundles = TicketBundles.generateTicketBundles(
                totalTicketCount, manualTickets, customTicketGenerator);
        compareTickets(ticketBundles.getManualTickets(), manualTickets);
        compareTickets(ticketBundles.getAutomaticTickets(), automaticTickets);
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
    @MethodSource("lotto.domain.ticket.provider.TicketBundlesTestProvider#provideForCalculateRanksTest")
    void calculateRanksTest(final List<Integer> winningNumbers,
                            final int bonusNumber,
                            final List<Ticket> manualTickets,
                            final List<Ticket> automaticTickets,
                            final List<Rank> expected) {
        customTicketGenerator.initTickets(automaticTickets);
        final int totalTicketCount = manualTickets.size() + automaticTickets.size();
        final TicketBundles ticketBundles = TicketBundles.generateTicketBundles(
                totalTicketCount, manualTickets, customTicketGenerator);
        final WinningTicket winningTicket = new WinningTicket(winningNumbers, bonusNumber);

        final List<Rank> actual = ticketBundles.calculateRanks(winningTicket);
        assertThat(actual).isEqualTo(expected);
    }

}
