package io.hhplus.tdd.service;

import io.hhplus.tdd.database.PointHistoryTable;
import io.hhplus.tdd.database.UserPointTable;
import io.hhplus.tdd.point.PointHistory;
import io.hhplus.tdd.point.TransactionType;
import io.hhplus.tdd.point.UserPoint;
import io.hhplus.tdd.point.UserPointLockManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

@Service
@RequiredArgsConstructor
public class PointServiceImpl implements PointService {

    private final UserPointTable userPointTable;
    private final PointHistoryTable pointHistoryTable;
    private final UserPointLockManager lockManager;

    @Override
    public UserPoint selectUserPoint(long id) {
        return userPointTable.selectById(id);
    }

    @Override
    public List<PointHistory> selectUserPointHistory(long id) {
        return pointHistoryTable.selectAllByUserId(id);
    }

    @Override
    public UserPoint chargeUserPoint(long id, long amount) {
        ReentrantLock lock = lockManager.getLock(id);
        lock.lock();
        // 충전시 순서대로 충전되도록 lock
        try {
            UserPoint currentUser = userPointTable.selectById(id);
            // 충전 금액을 가산 후 충전
            UserPoint validUserPoint = currentUser.charge(amount);
            // 충전요금 금액에 대한 검사
            UserPoint result = userPointTable.insertOrUpdate(validUserPoint.id(), validUserPoint.point());
            // 충전 후 금액이 적합한지 검사
            pointHistoryTable.insert(id, amount, TransactionType.CHARGE, System.currentTimeMillis());
            return result;
        } finally {
            lock.unlock();
        }

    }

    @Override
    public UserPoint useUserPoint(long id, long amount) {
        ReentrantLock lock = lockManager.getLock(id);
        lock.lock();

        try {
            // 충전요금 금액에 대한 검사
            UserPoint currentUserPoint = userPointTable.selectById(id);
            UserPoint result = userPointTable.insertOrUpdate(currentUserPoint.id(), currentUserPoint.point() - amount);
            pointHistoryTable.insert(id, amount, TransactionType.USE, System.currentTimeMillis());
            return result;
        } finally {
            lock.unlock();
        }
    }


}
