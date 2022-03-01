package lotto.view;

import static lotto.view.output.OutputMessage.*;

import java.util.List;

import lotto.domain.analysis.Analysis;
import lotto.dto.AnalysisDto;
import lotto.dto.TicketDto;
import lotto.dto.WinningTicketDto;
import lotto.view.input.InputView;
import lotto.view.output.OutputView;

public class LottoView {

    private final InputView inputView;
    private final OutputView outputView;

    public LottoView(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public int requestMoney() {
        outputView.printMessage(REQUEST_MONEY);
        return inputView.requestMoney();
    }

    public void announceTickets(final List<TicketDto> ticketDtos) {
        outputView.printTicketCount(ticketDtos);
        outputView.printTickets(ticketDtos);
    }

    public WinningTicketDto requestWinningTicketDto() {
        final List<Integer> winningNumbers = requestWinningNumbers();
        final int bonusNumber = requestBonusNumber();
        return new WinningTicketDto(winningNumbers, bonusNumber);
    }

    private List<Integer> requestWinningNumbers() {
        outputView.printMessage(REQUEST_WINNING_NUMBERS);
        return inputView.requestTicketNumbers();
    }

    private int requestBonusNumber() {
        outputView.printMessage(REQUEST_BONUS_NUMBER);
        return inputView.requestBonusNumber();
    }

    public void announceAnalysis(final AnalysisDto analysisDto) {
        outputView.printMessage(EMPTY_STRING);
        outputView.printMessage(TITLE_OF_ANALYSIS);
        outputView.printMessage(DIVIDING_LINE);
        outputView.printAnalysis(analysisDto);
    }

}
