package dto;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity  // 이 클래스가 데이터베이스 테이블과 매핑된다는 것을 나타냅니다.
@Data    // Lombok 라이브러리의 어노테이션으로, getter, setter, toString 등을 자동 생성해줍니다.
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키(primary key)를 나타내며, 자동으로 증가되는 값을 사용합니다.
    private Long id;

    private String username; // 사용자의 이름을 저장할 필드입니다.
    private String email;    // 사용자의 이메일을 저장할 필드입니다.
    private String password; // 사용자의 암호화된 비밀번호를 저장할 필드입니다.

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    // 사용자와 구독 간의 1:N 관계를 나타냅니다. 한 명의 사용자는 여러 구독을 가질 수 있습니다.
    private List<Subscription> subscriptions;
}