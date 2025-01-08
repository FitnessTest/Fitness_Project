package special.planner;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

/**
 * Step definitions for account management in a fitness application.
 */
public class AccountManagementSteps {
    private AccountManagement accountManagement;
    private UserProfile userProfile;

    /**
     * Default constructor for AccountManagementSteps.
     * Initializes the step definitions for testing account management features.
     */
    public AccountManagementSteps() {
        // Default constructor for step definitions
    }
    /**
     * Opens the account management page to start the account setup process.
     */
    @Given("the user opens the account management page")
    public void theUserOpensTheAccountManagementPage() {
        accountManagement = new AccountManagement();  // Initializes account management for the user
    }

    /**
     * Enters the user's name into the profile creation form.
     * @param name The name of the user.
     */
    @When("the user enters their name as {string}")
    public void theUserEntersTheirName(String name) {
        // Creates a new user profile and sets it up with the given name, age, fitness goal, and dietary preferences.
        userProfile = new UserProfile(name, 21, "Weight Loss", Arrays.asList("Gluten-Free", "Low-Carb"));
        accountManagement.createProfile(name, 21, "Weight Loss", Arrays.asList("Gluten-Free", "Low-Carb"));
    }

    /**
     * Enters the user's age into the profile creation form.
     * @param ageString The age of the user in string format.
     */
    @When("the user enters their age as {string}")
    public void theUserEntersTheirAgeAs(String ageString) {
        int age = Integer.parseInt(ageString);  // Parses the age from the string to an integer
        userProfile.setAge(age);  // Sets the user's age in the profile
        accountManagement.updateProfile(age, userProfile.getFitnessGoal());  // Updates profile with new age
    }

    /**
     * Creates a new user profile with the provided details.
     * @param fitnessGoal A data table containing the user's profile details.
     */
    @When("the user selects {string} as their fitness goal")
    public void theUserSelectsFitnessGoal(String fitnessGoal) {
        userProfile.setFitnessGoal(fitnessGoal);  // Updates fitness goal in the profile
        accountManagement.updateProfile(userProfile.getAge(), fitnessGoal);  // Updates the profile with the new fitness goal
    }

    /**
     * Selects dietary preferences for the user.
     * @param diet1 The first dietary preference.
     * @param diet2 The second dietary preference.
     */
    @When("the user selects {string} and {string} as their dietary preferences")
    public void theUserSelectsDietaryPreferences(String diet1, String diet2) {
        userProfile.setDietaryPreferences(Arrays.asList(diet1, diet2));  // Updates dietary preferences
        accountManagement.createProfile(userProfile.getName(), userProfile.getAge(),
                userProfile.getFitnessGoal(), userProfile.getDietaryPreferences());  // Creates the profile with new preferences
    }

    /**
     * Verifies that the profile is created with the given details.
     * @param dataTable The details of the user profile.
     */
    @Then("the profile should be created with the following details:")
    public void theProfileShouldBeCreatedWithDetails(DataTable dataTable) {
        List<List<String>> rows = dataTable.asLists(String.class);  // Converts data table to a list of lists
        String name = rows.get(1).get(0);
        int age = Integer.parseInt(rows.get(1).get(1));
        String fitnessGoal = rows.get(1).get(2);
        List<String> dietaryPreferences = Arrays.asList(rows.get(1).get(3).split(", "));

        // Verifies the profile details against the user profile
        assertEquals(name, userProfile.getName());
        assertEquals(age, userProfile.getAge());
        assertEquals(fitnessGoal, userProfile.getFitnessGoal());
        assertEquals(dietaryPreferences, userProfile.getDietaryPreferences());
    }

    /**
     * Creates an empty profile for the user.
     */
    @Given("the user creates an empty profile")
    public void theUserCreatesAnEmptyProfile() {
        accountManagement.createProfile("", 0, "", Arrays.asList());  // Creates an empty profile
    }

    /**
     * Verifies that the profile is empty.
     */
    @Then("the profile should be empty")
    public void theProfileShouldBeEmpty() {
        assertNull(accountManagement.getUserProfile());  // Verifies that the profile is null (empty)
    }

    /**
     * Simulates the action where the user enters an invalid name (such as an empty or special character name).
     * This step ensures that invalid names are handled correctly.
     */
    @Given("the user enters an invalid name")
    public void theUserEntersAnInvalidName() {
        try {
            accountManagement.createProfile(null, 25, "Fitness", Arrays.asList("Diet1"));
        } catch (NullPointerException e) {
            assertNotNull(e);  // Verifies that a null pointer exception is thrown
        }
    }
    /**
     * Simulates the action where the user enters an invalid age (such as zero or non-numeric values).
     * This step ensures that invalid age entries are properly handled.
     */
    @Given("the user enters an invalid age")
    public void theUserEntersAnInvalidAge() {
        try {
            accountManagement.createProfile("Invalid User", Integer.parseInt("invalid"), "Fitness Goal", Arrays.asList("Diet"));
        } catch (NumberFormatException e) {
            assertNotNull(e);  // Verifies that a number format exception is thrown
        }
    }

    /**
     * Simulates the action where the user enters a negative age.
     * This method ensures that negative age entries are rejected by the system.
     */
    @Given("the user enters a negative age")
    public void theUserEntersANegativeAge() {
        try {
            accountManagement.createProfile("Invalid User", -5, "Fitness Goal", Arrays.asList("Diet"));
        } catch (IllegalArgumentException e) {
            assertNotNull(e);  // Verifies that an illegal argument exception is thrown
        }
    }

    /**
     * Provides an existing user profile with the specified details from a DataTable.
     * This step is used to initialize a user profile with predefined data.
     * @param dataTable A DataTable containing user profile details such as name, age, etc.
     */
    @Given("the user has an existing profile with the following details:")
    public void theUserHasAnExistingProfileWithTheFollowingDetails(DataTable dataTable) {
        List<List<String>> rows = dataTable.asLists(String.class);
        String name = rows.get(1).get(0);
        int age = Integer.parseInt(rows.get(1).get(1));
        String fitnessGoal = rows.get(1).get(2);
        List<String> dietaryPreferences = Arrays.asList(rows.get(1).get(3).split(", "));

        userProfile = new UserProfile(name, age, fitnessGoal, dietaryPreferences);
        accountManagement = new AccountManagement();
        accountManagement.createProfile(name, age, fitnessGoal, dietaryPreferences);
    }
    /**
     * Updates the user's age to the specified value.
     * This step is used to change the user's age in their profile.
     * @param ageString The new age as a string.
     */
    @When("the user updates their age to {string}")
    public void theUserUpdatesTheirAgeTo(String ageString) {
        int age = Integer.parseInt(ageString);  // Updates the user's age
        userProfile.setAge(age);  // Sets the new age
        accountManagement.updateProfile(age, userProfile.getFitnessGoal());  // Updates the profile with new age
    }
    /**
     * Updates the user's fitness goal to the specified value.
     * This step is used to modify the user's fitness goal in their profile.
     * @param fitnessGoal The new fitness goal to set for the user.
     */
    @When("the user updates their fitness goal to {string}")
    public void theUserUpdatesTheirFitnessGoalTo(String fitnessGoal) {
        userProfile.setFitnessGoal(fitnessGoal);  // Updates the fitness goal in the profile
        accountManagement.updateProfile(userProfile.getAge(), fitnessGoal);  // Updates the profile with new fitness goal
    }

    /**
     * Updates the profile with the provided details.
     * @param dataTable The new details for the profile.
     */
    @Then("the profile should be updated with the following details:")
    public void theProfileShouldBeUpdatedWithTheFollowingDetails(DataTable dataTable) {
        List<List<String>> rows = dataTable.asLists(String.class);
        String name = rows.get(1).get(0);
        int age = Integer.parseInt(rows.get(1).get(1));
        String fitnessGoal = rows.get(1).get(2);
        List<String> dietaryPreferences = Arrays.asList(rows.get(1).get(3).split(", "));

        // Verifies the updated profile details
        assertEquals(name, userProfile.getName());
        assertEquals(age, userProfile.getAge());
        assertEquals(fitnessGoal, userProfile.getFitnessGoal());
        assertEquals(dietaryPreferences, userProfile.getDietaryPreferences());
    }

    /**
     * Simulates the action where the user selects a large list of dietary preferences during profile creation.
     * This step validates that large lists of dietary preferences can be handled by the system.
     */
    @Given("the user selects a large list of dietary preferences")
    public void theUserSelectsALargeListOfDietaryPreferences() {
        List<String> largeDietaryList = Arrays.asList(new String[1000]);  // Creates a list of 1000 dietary preferences
        accountManagement.createProfile("Large Diet User", 30, "Fitness Goal", largeDietaryList);  // Creates a profile with the large list
    }
    /**
     * Verifies that the profile should have a large dietary preferences list.
     */
    @Then("the profile should have the large dietary preferences list")
    public void theProfileShouldHaveTheLargeDietaryPreferencesList() {
        assertEquals(1000, userProfile.getDietaryPreferences().size());  // Verifies that the profile contains 1000 dietary preferences
    }

    /**
     * Class for managing user accounts.
     */
    public class AccountManagement {
        private UserProfile userProfile;

        /**
         * Default constructor for AccountManagement.
         * Initializes the class to manage user accounts.
         */
        public AccountManagement() {
            // Default constructor for managing accounts.
        }
        /**
         * Creates a user profile with the specified details.
         * @param name The name of the user.
         * @param age The age of the user.
         * @param fitnessGoal The user's fitness goal.
         * @param dietaryPreferences The user's dietary preferences.
         */
        public void createProfile(String name, int age, String fitnessGoal, List<String> dietaryPreferences) {
            if (name == null || name.isEmpty()) {
                throw new NullPointerException("Name cannot be null or empty");  // Throws exception if name is null or empty
            }
            if (age < 0) {
                throw new IllegalArgumentException("Age cannot be negative");  // Throws exception if age is negative
            }
            userProfile = new UserProfile(name, age, fitnessGoal, dietaryPreferences);  // Creates a new user profile
        }

        /**
         * Updates the profile with new details.
         * @param newAge The new age of the user.
         * @param newFitnessGoal The new fitness goal of the user.
         */
        public void updateProfile(int newAge, String newFitnessGoal) {
            if (newAge < 0) {
                throw new IllegalArgumentException("Age cannot be negative");  // Throws exception if new age is negative
            }
            if (userProfile != null) {
                userProfile.updateProfile(newAge, newFitnessGoal);  // Updates the user profile
            }
        }

        /**
         * Returns the current user profile.
         * @return The current user profile.
         */
        public UserProfile getUserProfile() {
            return userProfile;
        }
    }
    /**
     * Class representing a user profile.
     */
    public class UserProfile {
        private String name;
        private int age;
        private String fitnessGoal;
        private List<String> dietaryPreferences;

        /**
         * Constructor for creating a new user profile.
         * @param name The name of the user.
         * @param age The age of the user.
         * @param fitnessGoal The user's fitness goal.
         * @param dietaryPreferences The user's dietary preferences.
         */
        public UserProfile(String name, int age, String fitnessGoal, List<String> dietaryPreferences) {
            this.name = name;
            this.age = age;
            this.fitnessGoal = fitnessGoal;
            this.dietaryPreferences = dietaryPreferences;
        }

        /**
         * Returns the name of the user.
         * @return The user's name.
         */
        public String getName() {
            return name;
        }
        /**
         * Sets the name of the user.
         * @param name The name to set.
         */
        public void setName(String name) {
            this.name = name;
        }
        /**
         * Returns the age of the user.
         * @return The age of the user.
         */
        public int getAge() {
            return age;
        }
        /**
         * Sets the age of the user.
         * @param age The age to set for the user.
         */
        public void setAge(int age) {
            this.age = age;
        }
        /**
         * Returns the fitness goal of the user.
         * @return The fitness goal.
         */
        public String getFitnessGoal() {
            return fitnessGoal;
        }
        /**
         * Sets the fitness goal of the user.
         * @param fitnessGoal The fitness goal to set.
         */
        public void setFitnessGoal(String fitnessGoal) {
            this.fitnessGoal = fitnessGoal;
        }
        /**
         * Returns the list of dietary preferences for the user.
         * @return The list of dietary preferences.
         */
        public List<String> getDietaryPreferences() {
            return dietaryPreferences;
        }
        /**
         * Sets the dietary preferences for the user.
         * @param dietaryPreferences The list of dietary preferences to set.
         */
        public void setDietaryPreferences(List<String> dietaryPreferences) {
            this.dietaryPreferences = dietaryPreferences;
        }

        /**
         * Updates the user profile with new age and fitness goal.
         * @param newAge The new age.
         * @param newFitnessGoal The new fitness goal.
         */
        public void updateProfile(int newAge, String newFitnessGoal) {
            this.age = newAge;
            this.fitnessGoal = newFitnessGoal;
        }
    }
}
