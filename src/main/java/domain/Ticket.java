package domain;

import java.util.List;

public class Ticket {
	private final Balls balls;

	public Ticket(TicketGenerator ticketGenerator) {
		final List<Integer> numbers = ticketGenerator.generate();
		this.balls = new Balls(numbers);
	}

	public Rank getRank(Balls answer, Ball bonusBall) {
		int matchCount = answer.countMatches(this.balls);
		boolean bonusBallMatched = this.balls.contains(bonusBall);
		return Rank.of(matchCount, bonusBallMatched);
	}

	public List<Integer> getBallNumbers() {
		return balls.getBallNumbers();
	}
}
