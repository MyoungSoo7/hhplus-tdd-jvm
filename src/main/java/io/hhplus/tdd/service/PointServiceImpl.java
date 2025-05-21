package io.hhplus.tdd.service;

import io.hhplus.tdd.database.UserPointTable;
import io.hhplus.tdd.point.PointHistory;
import io.hhplus.tdd.point.UserPoint;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointServiceImpl implements PointService {
    @Override
    public List<PointHistory> selectUserPointHistory(long id) {
        return List.of();
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
        UserPointTable userPointTable = new UserPointTable();
        return userPointTable.selectById(id);
    }
}
