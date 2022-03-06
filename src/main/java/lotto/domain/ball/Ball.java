package lotto.domain.ball;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import lotto.domain.ball.validation.BallValidator;

public class Ball {

    private static final Map<Integer, Ball> balls = new HashMap<>();

    private final int number;

    private Ball(final int number) {
        BallValidator.validateBallNumber(number);
        this.number = number;
    }

    public static Ball from(final int ballNumber) {
        saveBallIfNotExist(ballNumber);
        return balls.get(ballNumber);
    }

    private static void saveBallIfNotExist(final int ballNumber) {
        if (balls.containsKey(ballNumber)) {
            return;
        }
        balls.put(ballNumber, new Ball(ballNumber));
    }

    public int getBallNumber() {
        return this.number;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Ball ball = (Ball) object;
        return number == ball.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

}
