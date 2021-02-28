package lotto.domain;

import lotto.view.InputView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoTicketFactory {
    public static final int LOTTO_PRICE = 1000;
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int LOTTO_TICKET_SIZE = 6;
    private static final List<LottoNumber> AUTO_LOTTO_CANDIDATES;

    static {
        AUTO_LOTTO_CANDIDATES = new ArrayList<>();
        IntStream.rangeClosed(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
                .forEach(number -> AUTO_LOTTO_CANDIDATES.add(LottoNumber.of(Integer.toString(number))));
    }

    private LottoTicketFactory() {
    }

    public static LottoTickets createLottoTicketsIncludingManualTickets(Money money, List<String> manualTicketInputs) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        addManualLottoTickets(lottoTickets, manualTicketInputs);

        int autoCreateCount = (int) money.getValue() / LOTTO_PRICE - manualTicketInputs.size();
        addAutoLottoTickets(lottoTickets, autoCreateCount);
        return new LottoTickets(lottoTickets);
    }

    private static void addManualLottoTickets(List<LottoTicket> lottoTickets, List<String> lottoTicketsNumbers) {
        for (String numbers : lottoTicketsNumbers) {
            lottoTickets.add(
                    createManualLottoTicket(Arrays.asList(numbers.split(InputView.DELIMITER))));
        }
    }

    private static void addAutoLottoTickets(List<LottoTicket> lottoTickets, int autoCreateCount) {
        for (int i = 0; i < autoCreateCount; i++) {
            lottoTickets.add(createAutoLottoTicket());
        }
    }

    private static LottoTicket createAutoLottoTicket() {
        Collections.shuffle(AUTO_LOTTO_CANDIDATES);
        return new LottoTicket(AUTO_LOTTO_CANDIDATES.stream()
                .limit(LOTTO_TICKET_SIZE)
                .sorted()
                .collect(Collectors.toList()));
    }

    public static LottoTicket createManualLottoTicket(List<String> numbers) {
        return new LottoTicket(numbers.stream()
                .map(LottoNumber::of)
                .sorted()
                .collect(Collectors.toList()));
    }

    public static WinningLotto createWinningLotto(List<String> winningInputs, String bonusNumberInput) {
        return new WinningLotto(createManualLottoTicket(winningInputs), LottoNumber.of(bonusNumberInput));
    }
}