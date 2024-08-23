package dto;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity  // 이 클래스도 데이터베이스의 테이블과 매핑됩니다.
@Data    // Lombok 라이브러리의 어노테이션으로, getter, setter, toString 등을 자동 생성해줍니다.
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키를 나타내며, 자동으로 증가되는 값을 사용합니다.
    private Long id;

    private String serviceName;       // 구독 서비스의 이름을 저장할 필드입니다.
    private LocalDateTime startDate;  // 구독이 시작된 날짜를 저장할 필드입니다.
    private LocalDateTime endDate;    // 구독이 종료된 날짜를 저장할 필드입니다. null일 경우 구독이 유효함을 의미합니다.
    private String stripeSubscriptionId; // 결제를 처리한 Stripe에서 제공한 구독 ID를 저장합니다.

    @ManyToOne
    @JoinColumn(name = "user_id")
    // 여러 구독이 하나의 사용자에 속해있음을 나타내는 관계 설정입니다.
    private User user;
}