package lotto.view;

import java.util.List;

import lotto.dto.AnalysisDto;
import lotto.dto.TicketDto;
import lotto.view.input.InputView;
import lotto.view.output.OutputView;

public class LottoView {

    private final InputView inputView;
    private final OutputView outputView;

    public LottoView(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public int requestCreditMoney() {
        outputView.printMessageOfRequestCreditMoney();
        return inputView.requestCreditMoney();
    }

    public List<Integer> requestWinningNumbers() {
        outputView.printMessageOfRequestWinningNumbers();
        return inputView.requestWinningNumbers();
    }

    public int requestBonusNumber() {
        outputView.printMessageOfRequestBonusNumber();
        return inputView.requestBonusNumber();
    }

    public void announceTickets(List<TicketDto> ticketDtos) {
        outputView.printTicketCount(ticketDtos);
        outputView.printTickets(ticketDtos);
    }

    public void announceAnalysis(AnalysisDto analysis) {
        outputView.printEmptyLine();
        outputView.printTitleOfAnalysis();
        outputView.printDividingLine();
        outputView.printAnalysis(analysis);
    }

}
