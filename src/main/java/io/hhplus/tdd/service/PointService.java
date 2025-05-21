package io.hhplus.tdd.service;

import io.hhplus.tdd.point.PointHistory;
import io.hhplus.tdd.point.UserPoint;

import java.util.List;

public interface PointService {

    UserPoint selectUserPoint(long id);

    List<PointHistory> selectUserPointHistory(long id);

    UserPoint chargeUserPoint(long id, long amount);

    UserPoint useUserPoint(long id, long amount);

}
