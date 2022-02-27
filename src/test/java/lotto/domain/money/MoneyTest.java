package lotto.domain.money;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import lotto.exception.LottoException;
import lotto.exception.money.MoneyExceptionStatus;

class MoneyTest {

    @DisplayName("구입 금액은 0원이 될 수 없습니다.")
    @ParameterizedTest(name = "[{index}] 구입 금액 : {0}")
    @ValueSource(ints = {0})
    void moneyIsZeroExceptionTest(final int money) {
        assertThatThrownBy(() -> new Money(money))
                .isInstanceOf(LottoException.class)
                .hasMessageContaining(MoneyExceptionStatus.MONEY_CANNOT_BE_ZERO.getMessage());
    }

    @DisplayName("구입 금액은 1000으로 나누어 떨어져야 한다.")
    @ParameterizedTest(name = "[{index}] 구입 금액 : {0}")
    @ValueSource(ints = {100, 1100, 200001})
    void moneyIsDivisibleExceptionTest(final int money) {
        assertThatThrownBy(() -> new Money(money))
                .isInstanceOf(LottoException.class)
                .hasMessageContaining(MoneyExceptionStatus.MONEY_MUST_BE_DIVISIBLE.getMessage());
    }

    @DisplayName("구입 금액 생성자 기능 테스트")
    @ParameterizedTest(name = "[{index}] 구입 금액 : {0}")
    @ValueSource(ints = {1000, 12000, 1300000})
    void initTest(final int money) {
        final Money credit = new Money(money);
        assertThat(credit.getMoney()).isEqualTo(money);
    }

}
