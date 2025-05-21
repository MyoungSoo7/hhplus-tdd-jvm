package io.hhplus.tdd.service;

import io.hhplus.tdd.database.PointHistoryTable;
import io.hhplus.tdd.database.UserPointTable;
import io.hhplus.tdd.point.PointHistory;
import io.hhplus.tdd.point.TransactionType;
import io.hhplus.tdd.point.UserPoint;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PointServiceImplTest {

    @Mock
    PointHistoryTable pointHistoryTable;
    @Mock
    UserPointTable userPointTable;

    @InjectMocks PointServiceImpl pointService;

    
    @Test
    @DisplayName("유저 포인트 히스토리")
    void selectUserPointHistory() {
        
    }

    @Test
    @DisplayName("유저 포인트 충전")
    void chargeUserPoint() {

    }

    @Test
    @DisplayName("유저 포인트 사용")
    void useUserPoint() {


    }

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
    
    
    

}