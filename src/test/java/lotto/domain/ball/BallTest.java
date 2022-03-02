package lotto.domain.ball;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import lotto.exception.LottoException;
import lotto.exception.LottoExceptionStatus;

class BallTest {

    @DisplayName("범위 밖의 번호는 생성할 수 없다.")
    @ParameterizedTest(name = "[{index}] 로또 번호 : {0}")
    @ValueSource(ints = {-2, -1, 0, 46, 47, 48})
    void rangeOutExceptionTest(final int ballNumber) {
        assertThatThrownBy(() -> new Ball(ballNumber))
                .isInstanceOf(LottoException.class)
                .hasMessageContaining(LottoExceptionStatus.BALL_NUMBER_CANNOT_BE_OUT_OF_RANGE.getMessage());
    }

    @DisplayName("번호 객체는 생성 당시의 번호 값을 지녀야 한다.")
    @ParameterizedTest(name = "[{index}] 로또 번호 : {0}")
    @ValueSource(ints = {1, 2, 3, 43, 44, 45})
    void initTest(final int ballNumber) {
        final Ball ball = new Ball(ballNumber);
        assertThat(ball.getBallNumber()).isEqualTo(ballNumber);
    }

}
