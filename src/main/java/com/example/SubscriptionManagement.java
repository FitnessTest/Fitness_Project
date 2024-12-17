package com.example;

import java.util.HashMap;
import java.util.Map;

public class SubscriptionManagement {

    public enum Plan {
        BASIC,
        PREMIUM
    }

    private Map<String, Plan> userSubscriptions;

    public SubscriptionManagement() {
        userSubscriptions = new HashMap<>();

        // Sample user subscriptions (ID -> plan)
        userSubscriptions.put("1", Plan.BASIC);
        userSubscriptions.put("2", Plan.PREMIUM);
        userSubscriptions.put("3", Plan.PREMIUM);
    }

    public Plan getSubscriptionPlan(String id) {
        return userSubscriptions.getOrDefault(id, Plan.BASIC); // Default to BASIC if not found
    }

    public void upgradeToPremium(String id) {
        if (userSubscriptions.containsKey(id)) {
            userSubscriptions.put(id, Plan.PREMIUM);
            System.out.println("User with ID " + id + " upgraded to Premium.");
        } else {
            System.out.println("No user found with ID " + id);
        }
    }

    public void downgradeToBasic(String id) {
        if (userSubscriptions.containsKey(id)) {
            userSubscriptions.put(id, Plan.BASIC);
            System.out.println("User with ID " + id + " downgraded to Basic.");
        } else {
            System.out.println("No user found with ID " + id);
        }
    }

    public void viewAllSubscriptions() {
        if (userSubscriptions.isEmpty()) {
            System.out.println("No users have subscriptions.");
        } else {
            System.out.println("All user subscriptions:");
            System.out.println("+-------+------------+");
            System.out.printf("| %-5s | %-10s |%n", "ID", "Plan");
            System.out.println("+-------+------------+");
            for (Map.Entry<String, Plan> entry : userSubscriptions.entrySet()) {
                System.out.printf("| %-5s | %-10s |%n", entry.getKey(), entry.getValue());
            }
            System.out.println("+-------+------------+");
        }
    }

    public void changeSubscriptionPlan(String id, Plan newPlan) {
        if (userSubscriptions.containsKey(id)) {
            userSubscriptions.put(id, newPlan);
            System.out.println("User with ID " + id + " subscription plan changed to " + newPlan);
        } else {
            System.out.println("No user found with ID " + id);
        }
    }
}