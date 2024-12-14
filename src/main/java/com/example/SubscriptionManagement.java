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

        // Sample user subscriptions (email -> plan)
        userSubscriptions.put("client1@example.com", Plan.BASIC);
        userSubscriptions.put("instructor1@example.com", Plan.PREMIUM);
        userSubscriptions.put("client2@example.com", Plan.PREMIUM);
    }


    public Plan getSubscriptionPlan(String email) {
        return userSubscriptions.getOrDefault(email, Plan.BASIC); // Default to BASIC if not found
    }


    public void upgradeToPremium(String email) {
        if (userSubscriptions.containsKey(email)) {
            userSubscriptions.put(email, Plan.PREMIUM);
            System.out.println("User with email " + email + " upgraded to Premium.");
        } else {
            System.out.println("No user found with email " + email);
        }
    }


    public void downgradeToBasic(String email) {
        if (userSubscriptions.containsKey(email)) {
            userSubscriptions.put(email, Plan.BASIC);
            System.out.println("User with email " + email + " downgraded to Basic.");
        } else {
            System.out.println("No user found with email " + email);
        }
    }


    public void viewAllSubscriptions() {
        if (userSubscriptions.isEmpty()) {
            System.out.println("No users have subscriptions.");
        } else {
            System.out.println("All user subscriptions:");
            for (Map.Entry<String, Plan> entry : userSubscriptions.entrySet()) {
                System.out.println(entry.getKey() + " - " + entry.getValue());
            }
        }
    }


    public void changeSubscriptionPlan(String email, Plan newPlan) {
        if (userSubscriptions.containsKey(email)) {
            userSubscriptions.put(email, newPlan);
            System.out.println("User with email " + email + " subscription plan changed to " + newPlan);
        } else {
            System.out.println("No user found with email " + email);
        }
    }

    public static void main(String[] args) {
        SubscriptionManagement sm = new SubscriptionManagement();


        sm.viewAllSubscriptions();


        String email = "client1@example.com";
        System.out.println("\nSubscription plan for " + email + ": " + sm.getSubscriptionPlan(email));


        sm.upgradeToPremium("client1@example.com");


        sm.downgradeToBasic("instructor1@example.com");


        sm.changeSubscriptionPlan("client2@example.com", Plan.BASIC);


        sm.viewAllSubscriptions();
    }
}
