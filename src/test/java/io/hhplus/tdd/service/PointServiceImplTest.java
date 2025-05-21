package io.hhplus.tdd.service;

import io.hhplus.tdd.database.PointHistoryTable;
import io.hhplus.tdd.database.UserPointTable;
import io.hhplus.tdd.point.PointHistory;
import io.hhplus.tdd.point.TransactionType;
import io.hhplus.tdd.point.UserPoint;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PointServiceImplTest {

    // 행동
    @Mock
    PointHistoryTable pointHistoryTable;
    @Mock
    UserPointTable userPointTable;
 
    @Test
    @DisplayName("유저 포인트 조회")
    void selectUserPoint() {
        // given => id
        long userId = 1L;
        UserPoint expectedUserPoint = new UserPoint(
                userId,
                1000L,
                System.currentTimeMillis()
        );

        // when
        when(userPointTable.selectById(userId)).thenReturn(expectedUserPoint);
        UserPoint userPoint =userPointTable.selectById(userId);

        // then
        assertEquals(expectedUserPoint.id(), userPoint.id());
        assertEquals(expectedUserPoint.point(), userPoint.point());
        assertEquals(expectedUserPoint.updateMillis(),  userPoint.updateMillis());

    }

    
    @Test
    @DisplayName("유저 포인트 히스토리")
    void selectUserPointHistory() {
        // given =>long id
        long id = 1L;
        long userId = 1L;
        long amount = 1000L;
        TransactionType type = TransactionType.CHARGE;
        long updateMillis = 1652953000000L;
        PointHistory initPointHistory = new PointHistory(
                id,
                userId,
                amount,
                type,
                updateMillis
        );

        // when
        when(pointHistoryTable.selectAllByUserId(userId)).thenReturn(List.of(initPointHistory));
        List<PointHistory> pointHistories = pointHistoryTable.selectAllByUserId(userId);

        // then
        assertEquals(1, pointHistories.size());
        assertEquals(initPointHistory.id(), pointHistories.get(0).id());
        assertEquals(initPointHistory.userId(), pointHistories.get(0).userId());
        assertEquals(initPointHistory.amount(), pointHistories.get(0).amount());
        assertEquals(initPointHistory.type(), pointHistories.get(0).type());
        assertEquals(initPointHistory.updateMillis(), pointHistories.get(0).updateMillis());

    }

    @Test
    @DisplayName("유저 포인트 충전")
    void chargeUserPoint() {




    }

    @Test
    @DisplayName("유저 포인트 사용")
    void useUserPoint() {


    }


    
    
    

}