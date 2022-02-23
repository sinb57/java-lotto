package lotto.domain.rank;

import java.util.Arrays;

public enum Rank {

	FIFTH_GRADE(3, false, 5_000L),
	FOURTH_GRADE(4, false, 50_000L),
	THIRD_GRADE(5, false, 1_500_000L),
	SECOND_GRADE(5, true, 30_000_000L),
	FIRST_GRADE(6, false, 3_000_000_000L);

	private final int matchCount;
	private final boolean bonusBallMatched;
	private final Long prize;

	Rank(final int matchCount, final boolean bonusBallMatched, final Long prize) {
		this.matchCount = matchCount;
		this.bonusBallMatched = bonusBallMatched;
		this.prize = prize;
	}

	public static Rank of(int matchCount, boolean bonusBallMatched) {
		return Arrays.stream(Rank.values())
			.filter(rank -> rank.matches(matchCount, bonusBallMatched))
			.findFirst()
			.orElseGet(null);
	}

	private boolean matches(int matchCount, boolean bonusBallMatched) {
		return (matchCount == this.matchCount) && (bonusBallMatched == this.bonusBallMatched);
	}

	public long getPrize() {
		return prize;
	}

	public boolean getBonusBallMatched() {
		return bonusBallMatched;
	}

	public int getMatchCount() {
		return matchCount;
	}

}
