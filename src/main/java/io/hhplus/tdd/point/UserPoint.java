package io.hhplus.tdd.point;

public record UserPoint(
        long id,
        long point,
        long updateMillis
) {

    private static final long MAX_POINT = 100_000;
    private static final long MIN_USE_CHARGE_POINT = 1L;

    public UserPoint use(long amount) {
        if (amount < MIN_USE_CHARGE_POINT) {
            throw new IllegalArgumentException("사용할 포인트는 1 이상이어야 합니다.");
        }
        if (point < amount) {
            throw new IllegalArgumentException("포인트가 부족합니다.");
        }
        return new UserPoint(id, point - amount, System.currentTimeMillis());
    }

    public UserPoint charge(long amount) {
        if (amount < MIN_USE_CHARGE_POINT) {
            throw new IllegalArgumentException("충전할 포인트는 1 이상이어야 합니다.");
        }
        if (point + amount > MAX_POINT) {
            throw new IllegalArgumentException("최대 포인트는 " + MAX_POINT + "입니다.");
        }
        return new UserPoint(id, point + amount, System.currentTimeMillis());
    }

    public static UserPoint empty(long id) {
        return new UserPoint(id, 0, System.currentTimeMillis());
    }
}
