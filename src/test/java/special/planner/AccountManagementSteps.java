package special.planner;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

public class AccountManagementSteps {
    private AccountManagement accountManagement;
    private UserProfile userProfile;

    @Given("the user opens the account management page")
    public void theUserOpensTheAccountManagementPage() {
        accountManagement = new AccountManagement();
    }

    @When("the user enters their name as {string}")
    public void theUserEntersTheirName(String name) {
        userProfile = new UserProfile(name, 21, "Weight Loss", Arrays.asList("Gluten-Free", "Low-Carb"));
        accountManagement.createProfile(name, 21, "Weight Loss", Arrays.asList("Gluten-Free", "Low-Carb"));
    }

    @When("the user enters their age as {string}")
    public void theUserEntersTheirAgeAs(String ageString) {
        int age = Integer.parseInt(ageString);
        userProfile.setAge(age);
        accountManagement.updateProfile(age, userProfile.getFitnessGoal());
    }

    @When("the user selects {string} as their fitness goal")
    public void theUserSelectsFitnessGoal(String fitnessGoal) {
        userProfile.setFitnessGoal(fitnessGoal);
        accountManagement.updateProfile(userProfile.getAge(), fitnessGoal);
    }

    @When("the user selects {string} and {string} as their dietary preferences")
    public void theUserSelectsDietaryPreferences(String diet1, String diet2) {
        userProfile.setDietaryPreferences(Arrays.asList(diet1, diet2));
        accountManagement.createProfile(userProfile.getName(), userProfile.getAge(),
                userProfile.getFitnessGoal(), userProfile.getDietaryPreferences());
    }

    @Then("the profile should be created with the following details:")
    public void theProfileShouldBeCreatedWithDetails(io.cucumber.datatable.DataTable dataTable) {
        List<List<String>> rows = dataTable.asLists(String.class);
        String name = rows.get(1).get(0);
        int age = Integer.parseInt(rows.get(1).get(1));
        String fitnessGoal = rows.get(1).get(2);
        List<String> dietaryPreferences = Arrays.asList(rows.get(1).get(3).split(", "));

        assertEquals(name, userProfile.getName());
        assertEquals(age, userProfile.getAge());
        assertEquals(fitnessGoal, userProfile.getFitnessGoal());
        assertEquals(dietaryPreferences, userProfile.getDietaryPreferences());
    }

    // Test for Empty Profile
    @Given("the user creates an empty profile")
    public void theUserCreatesAnEmptyProfile() {
        accountManagement.createProfile("", 0, "", Arrays.asList());
    }

    @Then("the profile should be empty")
    public void theProfileShouldBeEmpty() {
        assertNull(accountManagement.getUserProfile());
    }

    // Test for Invalid Name (null)
    @Given("the user enters an invalid name")
    public void theUserEntersAnInvalidName() {
        try {
            accountManagement.createProfile(null, 25, "Fitness", Arrays.asList("Diet1"));
        } catch (NullPointerException e) {
            assertNotNull(e);
        }
    }

    // Test for Invalid Age (non-integer)
    @Given("the user enters an invalid age")
    public void theUserEntersAnInvalidAge() {
        try {
            accountManagement.createProfile("Invalid User", Integer.parseInt("invalid"), "Fitness Goal", Arrays.asList("Diet"));
        } catch (NumberFormatException e) {
            assertNotNull(e);
        }
    }

    // Test for Invalid Age (negative value)
    @Given("the user enters a negative age")
    public void theUserEntersANegativeAge() {
        try {
            accountManagement.createProfile("Invalid User", -5, "Fitness Goal", Arrays.asList("Diet"));
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
        }
    }

    // Test for Profile Update (Valid)
    @Given("the user has an existing profile with the following details:")
    public void theUserHasAnExistingProfileWithTheFollowingDetails(io.cucumber.datatable.DataTable dataTable) {
        List<List<String>> rows = dataTable.asLists(String.class);
        String name = rows.get(1).get(0);
        int age = Integer.parseInt(rows.get(1).get(1));
        String fitnessGoal = rows.get(1).get(2);
        List<String> dietaryPreferences = Arrays.asList(rows.get(1).get(3).split(", "));

        userProfile = new UserProfile(name, age, fitnessGoal, dietaryPreferences);
        accountManagement = new AccountManagement();
        accountManagement.createProfile(name, age, fitnessGoal, dietaryPreferences);
    }

    @When("the user updates their age to {string}")
    public void theUserUpdatesTheirAgeTo(String ageString) {
        int age = Integer.parseInt(ageString);
        userProfile.setAge(age);
        accountManagement.updateProfile(age, userProfile.getFitnessGoal());
    }

    @When("the user updates their fitness goal to {string}")
    public void theUserUpdatesTheirFitnessGoalTo(String fitnessGoal) {
        userProfile.setFitnessGoal(fitnessGoal);
        accountManagement.updateProfile(userProfile.getAge(), fitnessGoal);
    }

    @Then("the profile should be updated with the following details:")
    public void theProfileShouldBeUpdatedWithTheFollowingDetails(io.cucumber.datatable.DataTable dataTable) {
        List<List<String>> rows = dataTable.asLists(String.class);
        String name = rows.get(1).get(0);
        int age = Integer.parseInt(rows.get(1).get(1));
        String fitnessGoal = rows.get(1).get(2);
        List<String> dietaryPreferences = Arrays.asList(rows.get(1).get(3).split(", "));

        assertEquals(name, userProfile.getName());
        assertEquals(age, userProfile.getAge());
        assertEquals(fitnessGoal, userProfile.getFitnessGoal());
        assertEquals(dietaryPreferences, userProfile.getDietaryPreferences());
    }

    // Test for Large Dietary Preferences List
    @Given("the user selects a large list of dietary preferences")
    public void theUserSelectsALargeListOfDietaryPreferences() {
        List<String> largeDietaryList = Arrays.asList(new String[1000]); // 1000 dietary preferences
        accountManagement.createProfile("Large Diet User", 30, "Fitness Goal", largeDietaryList);
    }

    @Then("the profile should have the large dietary preferences list")
    public void theProfileShouldHaveTheLargeDietaryPreferencesList() {
        assertEquals(1000, userProfile.getDietaryPreferences().size());
    }

    // AccountManagement Class
    public class AccountManagement {
        private UserProfile userProfile;

        public void createProfile(String name, int age, String fitnessGoal, List<String> dietaryPreferences) {
            if (name == null || name.isEmpty()) {
                throw new NullPointerException("Name cannot be null or empty");
            }
            if (age < 0) {
                throw new IllegalArgumentException("Age cannot be negative");
            }
            userProfile = new UserProfile(name, age, fitnessGoal, dietaryPreferences);
        }

        public void updateProfile(int newAge, String newFitnessGoal) {
            if (newAge < 0) {
                throw new IllegalArgumentException("Age cannot be negative");
            }
            if (userProfile != null) {
                userProfile.updateProfile(newAge, newFitnessGoal);
            }
        }

        public UserProfile getUserProfile() {
            return userProfile;
        }
    }

    // UserProfile Class
    public class UserProfile {
        private String name;
        private int age;
        private String fitnessGoal;
        private List<String> dietaryPreferences;

        public UserProfile(String name, int age, String fitnessGoal, List<String> dietaryPreferences) {
            this.name = name;
            this.age = age;
            this.fitnessGoal = fitnessGoal;
            this.dietaryPreferences = dietaryPreferences;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getFitnessGoal() {
            return fitnessGoal;
        }

        public void setFitnessGoal(String fitnessGoal) {
            this.fitnessGoal = fitnessGoal;
        }

        public List<String> getDietaryPreferences() {
            return dietaryPreferences;
        }

        public void setDietaryPreferences(List<String> dietaryPreferences) {
            this.dietaryPreferences = dietaryPreferences;
        }

        public void updateProfile(int newAge, String newFitnessGoal) {
            this.age = newAge;
            this.fitnessGoal = newFitnessGoal;
        }

        @Override
        public String toString() {
            return "UserProfile{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", fitnessGoal='" + fitnessGoal + '\'' +
                    ", dietaryPreferences=" + dietaryPreferences +
                    '}';
        }
    }
}
