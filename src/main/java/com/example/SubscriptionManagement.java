package com.example;

import java.util.HashMap;
import java.util.Map;

public class SubscriptionManagement {

    public enum Plan {
        BASIC,
        PREMIUM
    }

    public enum UserType {
        CLIENT,
        INSTRUCTOR
    }

    private static class User {
        private String id;
        private Plan plan;
        private UserType type;

        public User(String id, Plan plan, UserType type) {
            this.id = id;
            this.plan = plan;
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public Plan getPlan() {
            return plan;
        }

        public void setPlan(Plan plan) {
            this.plan = plan;
        }

        public UserType getType() {
            return type;
        }
    }

    private static Map<String, User> userSubscriptions;

    public SubscriptionManagement() {
        userSubscriptions = new HashMap<>();

        // Sample user subscriptions (ID -> User object)
        userSubscriptions.put("1", new User("1", Plan.BASIC, UserType.CLIENT));
        userSubscriptions.put("2", new User("2", Plan.PREMIUM, UserType.INSTRUCTOR));
        userSubscriptions.put("3", new User("3", Plan.PREMIUM, UserType.CLIENT));
    }

    public Plan getSubscriptionPlan(String id) {
        User user = userSubscriptions.get(id);
        return (user != null) ? user.getPlan() : Plan.BASIC; // Default to BASIC if not found
    }

    public static void upgradeToPremium(String id) {
        User user = userSubscriptions.get(id);
        if (user != null) {
            if (user.getPlan() == Plan.PREMIUM) {
                System.out.println("User with ID " + id + " is already on Premium.");
            } else {
                user.setPlan(Plan.PREMIUM);
                System.out.println("User with ID " + id + " upgraded to Premium.");
            }
        } else {
            System.out.println("No user found with ID " + id);
        }
    }

    public static void downgradeToBasic(String id) {
        User user = userSubscriptions.get(id);
        if (user != null) {
            if (user.getPlan() == Plan.BASIC) {
                System.out.println("User with ID " + id + " is already on Basic.");
            } else {
                user.setPlan(Plan.BASIC);
                System.out.println("User with ID " + id + " downgraded to Basic.");
            }
        } else {
            System.out.println("No user found with ID " + id);
        }
    }

    public static void viewAllSubscriptions() {
        if (userSubscriptions == null || userSubscriptions.isEmpty()) {
            System.out.println("No users have subscriptions.");
        } else {
            System.out.println("All user subscriptions:");
            System.out.println("+-------+------------+------------+");
            System.out.printf("| %-5s | %-10s | %-10s |%n", "ID", "Plan", "Type");
            System.out.println("+-------+------------+------------+");
            for (User user : userSubscriptions.values()) {
                System.out.printf("| %-5s | %-10s | %-10s |%n", user.getId(), user.getPlan(), user.getType());
            }
            System.out.println("+-------+------------+------------+");
        }
    }

    public static void changeSubscriptionPlan(String id, Plan newPlan) {
        User user = userSubscriptions.get(id);
        if (user != null) {
            Plan oldPlan = user.getPlan();
            user.setPlan(newPlan);
            System.out.println("User with ID " + id + " subscription plan changed from " + oldPlan + " to " + newPlan);
        } else {
            System.out.println("No user found with ID " + id);
        }
    }
}
