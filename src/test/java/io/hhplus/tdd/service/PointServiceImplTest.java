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

import java.util.List;

import static io.hhplus.tdd.point.TransactionType.CHARGE;
import static io.hhplus.tdd.point.TransactionType.USE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;



@ExtendWith(MockitoExtension.class)
class PointServiceImplTest {

    @InjectMocks
    private PointServiceImpl pointService;

    // 행동
    @Mock
    PointHistoryTable pointHistoryTable;
    @Mock
    UserPointTable userPointTable;
 
    @Test
    @DisplayName("유저 포인트 조회")
    void selectUserPoint() {
        // given => id
        final long userId = 1L;
        final long amount = 1000L;

        UserPoint userPoint = new UserPoint(
                userId,
                amount,
                System.currentTimeMillis()
        );
        given(userPointTable.selectById(anyLong())).willReturn(userPoint);

        //when
        UserPoint result = pointService.selectUserPoint(userId);

        // then => UserPoint
        assertThat(result.id()).isEqualTo(userId);
        assertThat(result.point()).isEqualTo(amount);

    }

    
    @Test
    @DisplayName("유저 포인트 히스토리")
    void selectUserPointHistory() {
        // given =>long id
        final long userId = 1L;
        final long amount1 = 1000L;
        final long amount2 = 2000L;

        PointHistory pointHistory1 = new PointHistory(
                1L,
                userId,
                amount1,
                CHARGE,
                System.currentTimeMillis()
        );
        PointHistory pointHistory2 = new PointHistory(
                2L,
                userId,
                amount2,
                USE,
                System.currentTimeMillis()
        );

        //when
        List<PointHistory> resultList = pointService.selectUserPointHistory(userId);

        //then
        assertThat(resultList)
                .hasSize(2)
                .extracting("id","userId", "amount", "type")
                .containsExactlyInAnyOrder(
                        tuple(1L,userId, amount1, CHARGE),
                        tuple(2L, userId, amount2, USE)
                );

    }

    @Test
    @DisplayName("유저 포인트 충전")
    void chargeUserPoint() {
        // given => long id, long amount
        Long userId = 1L;
        Long amount = 1000L;

        UserPoint beforeCharge = new UserPoint(userId, amount, System.currentTimeMillis());
        UserPoint afterCharge = new UserPoint(userId, beforeCharge.point() + amount, System.currentTimeMillis());

        given(userPointTable.selectById(anyLong())).willReturn(beforeCharge);
        given(userPointTable.insertOrUpdate(anyLong(), anyLong())).willReturn(afterCharge);

        //when
        UserPoint result = pointService.chargeUserPoint(userId, amount);

        //then
        assertThat(result.id()).isEqualTo(userId);
        assertThat(result.point()).isEqualTo(beforeCharge.point() + amount);

        
        // 충전요금 금액에 대한 검사가 부재
        // 충전 금액을 가산 후 충전하여야 하나 현재 로직이 부재
        // 충전 후 금액이 적합한지 검사하는 로직이 부재
        // 충전시 순서대로 충전되도록 lock을 거는 부분도 현재로직이 부재

    }

    @Test
    @DisplayName("유저 포인트 사용")
    void useUserPoint() {
        // given => long id, long amount
        long userId = 1L;
        long amount = 5000L;
        long usePoint = 1000L;

        UserPoint beforeUse = new UserPoint(userId, amount, System.currentTimeMillis());// 사용 전 유저 정보
        UserPoint afterUse = new UserPoint(userId, amount - usePoint, System.currentTimeMillis());// 사용 후 유저 정보

        given(userPointTable.selectById(anyLong())).willReturn(beforeUse);
        given(userPointTable.insertOrUpdate(anyLong(), anyLong())).willReturn(afterUse);

        // when
        UserPoint usedUserPoint = pointService.chargeUserPoint(beforeUse.id(), usePoint);

        // then
        assertThat(usedUserPoint.point()).isEqualTo(afterUse.point());


    } 
    

}