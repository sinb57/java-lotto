package lotto.domain;

import java.util.*;
import java.util.stream.Collectors;

public class Lotto {
    public static final int LOTTO_START_INDEX = 0;
    public static final int LOTTO_SIZE = 6;
    public static final String LOTTO_SIZE_ERROR = String.format("번호는 총 %d개 이어야 합니다.", LOTTO_SIZE);
    public static final String DUPLICATE_ERROR = "중복되는 번호가 있습니다.";

    protected final List<LottoNumber> lottoNumbers;

    public Lotto(List<LottoNumber> lottoNumbers) {
        validateLottoSize(lottoNumbers);
        validateDuplicates(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    private void validateLottoSize(List<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(LOTTO_SIZE_ERROR);
        }
    }

    private void validateDuplicates(List<LottoNumber> numbers) {
        Set<LottoNumber> numberGroup = new HashSet<>(numbers);
        if (numberGroup.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(DUPLICATE_ERROR);
        }
    }

    public List<LottoNumber> getLottoNumbers() {
        return Collections.unmodifiableList(lottoNumbers);
    }

    public int countMatchingNumbers(WinningLotto winningLotto) {
        return winningLotto.countMatchingNumbers(lottoNumbers);
    }

    public boolean hasBonusNumber(WinningLotto winningLotto) {
        return winningLotto.hasBonusMatch(lottoNumbers);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lotto lotto = (Lotto) o;
        return lottoNumbers.equals(lotto.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers);
    }
}