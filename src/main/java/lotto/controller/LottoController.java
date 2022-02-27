package lotto.controller;

import java.util.List;

import lotto.dto.AnalysisDto;
import lotto.dto.TicketDto;
import lotto.dto.WinningTicketDto;
import lotto.service.LottoService;
import lotto.view.LottoView;

public class LottoController {

    private final LottoService lottoService;
    private final LottoView lottoView;

    public LottoController(final LottoService lottoService, final LottoView lottoView) {
        this.lottoService = lottoService;
        this.lottoView = lottoView;
    }

    public void purchaseTickets() {
        final int money = lottoView.requestMoney();
        lottoService.generateTickets(money);
    }

    public void showTickets() {
        final List<TicketDto> ticketDtos = lottoService.getTicketDtos();
        lottoView.announceTickets(ticketDtos);
    }

    public void checkOutAnalysis() {
        final WinningTicketDto winningTicketDto = lottoView.requestWinningTicket();
        final AnalysisDto analysisDto = lottoService.generateAnalysis(winningTicketDto);
        lottoView.announceAnalysis(analysisDto);
    }

}
