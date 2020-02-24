package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoMachine {
	private final LottoNumbers allLottoNumbers;

	private LottoMachine() {
		List<LottoNumber> initialSetting = new ArrayList<>();

		for (int i = LottoNumber.MINIMUM; i <= LottoNumber.MAXIMUM; i++) {
			initialSetting.add(new LottoNumber(i));
		}
		this.allLottoNumbers = new LottoNumbers(initialSetting);
	}

	public static LottoMachine getInstance() {
		return LottoMachineSingletonHolder.instance;
	}

	public List<Lotto> makeRandomLottos(int lottoCount) {
		List<Lotto> lottos = new ArrayList<>();

		for (int i = 0; i < lottoCount; i++) {
			lottos.add(new Lotto(new LottoNumbers(pickRandomNumbers())));
		}
		return lottos;
	}

	private List<LottoNumber> pickRandomNumbers() {
		Collections.shuffle(allLottoNumbers.getLottoNumbers());
		return allLottoNumbers.getLottoNumbers()
			.stream()
			.limit(Lotto.SIZE)
			.sorted()
			.collect(Collectors.toList());
	}

	private static class LottoMachineSingletonHolder {
		private static final LottoMachine instance = new LottoMachine();
	}
}