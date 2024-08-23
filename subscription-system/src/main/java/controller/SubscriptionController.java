package controller;

import service.SubscriptionService;
import dto.Subscription;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping("/user/{userId}")
    public List<Subscription> getUserSubscriptions(@PathVariable Long userId) {
        return subscriptionService.getSubscriptionsForUser(userId);
    }

    @PostMapping("/user/{userId}")
    public Subscription createSubscription(@PathVariable Long userId, @RequestBody String serviceName) {
        return subscriptionService.createSubscription(userId, serviceName);
    }

    @PostMapping("/cancel/{subscriptionId}")
    public void cancelSubscription(@PathVariable Long subscriptionId) {
        subscriptionService.cancelSubscription(subscriptionId);
    }
}