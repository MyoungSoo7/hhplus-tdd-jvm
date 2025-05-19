package io.hhplus.tdd.point;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//Controller 외부에서 들어오는 데이터의 형식 검증 및 기본적인 유효성 검사(예: 빈 값인지, 숫자인지 등)를 처리합니다.
//Service 비즈니스적으로 유의미한 값인지(예: 범위 검증, 도메인적 유효성 등)를 검증합니다.
// 어떤 접근법을 선택하든 중요한 것은 테스트를 통해 원하는 기능을 명확히 검증하는 것입니다.
// TDD 방법론 중 Classicist(Detroit) vs Mockist(London)는 어떤 방법이 더 나은가요?**
//- **Classicist(Detroit)** 방식은 실제 객체를 최대한 활용하여 테스트하며, 자연스러운 설계를 강조합니다.
//- **Mockist(London)** 방식은 테스트 더블을 적극 활용하여 객체 간 상호작용을 철저히 검증하는 데 중점을 둡니다.
//Throttle 함수는 의도적으로 동시성 이슈를 발생시키기 위해 포함된 코드입니다. 동시성 문제를 실제로 경험하고 이를 해결하는 방식을 고민하게 하려는 목적으로 제공된 코드입니다.

//각 Day와 세션은 *‘학습 목표 → 실습 과제 → 자기 진단’*
// 지식의 흡수 → 적용 → 점검까지 자연스럽게 이어지도록 설계
//오늘의 학습 목표를 한 문장으로 다시 말해보면 더 잘 기억돼요.
//자가진단 중 ‘모호한 부분’이 있다면, 질문을 남기거나 내일 학습 목표


//**자가진단표**
/*
        - 테스트가 필요한 이유를 말로 설명할 수 있는가?
        - 협업 시 테스트 코드가 어떤 역할을 하는가?
        - 테스트 가능한 구조를 만들기 위해 어떤 설계 전략이 필요한가?
*/

//UserService 예제
//테스트 가능한 구조의 3요소: 단일 책임 / 의존성 분리 / 명확한 흐름

@RestController
@RequestMapping("/point")
public class PointController {

    private static final Logger log = LoggerFactory.getLogger(PointController.class);

    /**
     * TODO - 특정 유저의 포인트를 조회하는 기능을 작성해주세요.
     */
    @GetMapping("{id}")
    public UserPoint point(
            @PathVariable long id
    ) {
        return new UserPoint(0, 0, 0);
    }

    /**
     * TODO - 특정 유저의 포인트 충전/이용 내역을 조회하는 기능을 작성해주세요.
     */
    @GetMapping("{id}/histories")
    public List<PointHistory> history(
            @PathVariable long id
    ) {
        return List.of();
    }

    /**
     * TODO - 특정 유저의 포인트를 충전하는 기능을 작성해주세요.
     */
    @PatchMapping("{id}/charge")
    public UserPoint charge(
            @PathVariable long id,
            @RequestBody long amount
    ) {
        return new UserPoint(0, 0, 0);
    }

    /**
     * TODO - 특정 유저의 포인트를 사용하는 기능을 작성해주세요.
     */
    @PatchMapping("{id}/use")
    public UserPoint use(
            @PathVariable long id,
            @RequestBody long amount
    ) {
        return new UserPoint(0, 0, 0);
    }


}
