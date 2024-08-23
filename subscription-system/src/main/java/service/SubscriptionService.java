package service;

import dto.Subscription;
import dto.User;
import org.springframework.stereotype.Service;
import repository.SubscriptionRepository;
import repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;

    public SubscriptionService(SubscriptionRepository subscriptionRepository, UserRepository userRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.userRepository = userRepository;
    }

    public List<Subscription> getSubscriptionsForUser(Long userId) {
        return subscriptionRepository.findByUserId(userId);
    }

    public Subscription createSubscription(Long userId, String serviceName) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Subscription subscription = new Subscription();
        subscription.setServiceName(serviceName);
        subscription.setStartDate(LocalDateTime.now());
        subscription.setEndDate(null);  // 구독 종료일을 설정하지 않음
        subscription.setUser(user);
        return subscriptionRepository.save(subscription);
    }

    public void cancelSubscription(Long subscriptionId) {
        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new RuntimeException("Subscription not found"));
        subscription.setEndDate(LocalDateTime.now());
        subscriptionRepository.save(subscription);
    }
}