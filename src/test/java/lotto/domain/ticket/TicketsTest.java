package lotto.domain.ticket;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import lotto.domain.ball.Ball;
import lotto.domain.ball.BallStorage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import lotto.AppConfig;
import lotto.domain.rank.Rank;
import lotto.domain.ticket.generator.CustomTicketGenerator;
import lotto.dto.TicketDto;

class TicketsTest {

    private static final AppConfig APP_CONFIG = AppConfig.getInstance();

    private final CustomTicketGenerator customTicketGenerator = APP_CONFIG.ticketGenerator;

    private Tickets generateTickets(final List<TicketDto> generatedTickets, final int ticketCount) {
        customTicketGenerator.initTickets(generatedTickets);
        return new Tickets(ticketCount, customTicketGenerator);
    }

    @DisplayName("로또는 주어진 개수만큼 생성되어야 한다.")
    @ParameterizedTest(name = "[{index}] 로또 개수 : {1}")
    @MethodSource("lotto.domain.ticket.provider.TicketsTestProvider#provideForGenerateTest")
    void generateTicketsSizeCheckTest(final List<TicketDto> generatedTickets, final int ticketCount) {
        final Tickets tickets = generateTickets(generatedTickets, ticketCount);
        assertThat(tickets.getSize()).isEqualTo(ticketCount);
    }

    @DisplayName("계산된 당첨 등수 목록은 기댓값과 일치해야 한다.")
    @ParameterizedTest(name = "[{index}] 당첨 등수 목록 : {4}")
    @MethodSource("lotto.domain.ticket.provider.TicketsTestProvider#provideForCalculateRanksTest")
    void calculateRanksTest(final TicketDto winningTicketNumbers,
                            final int bonusNumber,
                            final List<TicketDto> generatedTickets,
                            final List<Rank> expected) {
        final int ticketCount = generatedTickets.size();
        final Tickets tickets = generateTickets(generatedTickets, ticketCount);
        final Ticket winningTicket = new Ticket(winningTicketNumbers.getBallNumbers());
        final Ball bonusBall = BallStorage.getBall(bonusNumber);

        final List<Rank> actual = tickets.calculateRanks(winningTicket, bonusBall);
        assertThat(actual).isEqualTo(expected);
    }

}
