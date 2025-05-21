package io.hhplus.tdd.service;

import io.hhplus.tdd.database.PointHistoryTable;
import io.hhplus.tdd.database.UserPointTable;
import io.hhplus.tdd.point.PointHistory;
import io.hhplus.tdd.point.UserPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PointServiceImpl implements PointService {
    @Autowired
    private UserPointTable userPointTable;
    @Autowired
    private PointHistoryTable pointHistoryTable;

    @Override
    public List<PointHistory> selectUserPointHistory(long id) {
        return pointHistoryTable.selectAllByUserId(id);
    }

    @Override
    public UserPoint chargeUserPoint(long id, long amount) {
        return null;
    }

    @Override
    public UserPoint useUserPoint(long id, long amount) {
        return null;
    }

    @Override
    public UserPoint selectUserPoint(long id) {
        return userPointTable.selectById(id);
    }
}
