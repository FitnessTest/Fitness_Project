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
        // Set the name for profile creation
        userProfile = new UserProfile(name, 21, "Weight Loss", Arrays.asList("Gluten-Free", "Low-Carb"));
        accountManagement.createProfile(name, 21, "Weight Loss", Arrays.asList("Gluten-Free", "Low-Carb"));
    }

    @When("the user enters their age as {string}")
    public void theUserEntersTheirAgeAs(String ageString) {

        int age = Integer.parseInt(ageString);

        userProfile.setAge(age);
    }

    @When("the user selects {string} as their fitness goal")
    public void theUserSelectsFitnessGoal(String fitnessGoal) {

        userProfile.setFitnessGoal(fitnessGoal);
    }

    @When("the user selects {string} and {string} as their dietary preferences")
    public void theUserSelectsDietaryPreferences(String diet1, String diet2) {

        userProfile.setDietaryPreferences(Arrays.asList(diet1, diet2));
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
    }

    @When("the user updates their fitness goal to {string}")
    public void theUserUpdatesTheirFitnessGoalTo(String fitnessGoal) {
        userProfile.setFitnessGoal(fitnessGoal);
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


    public class AccountManagement {
        private UserProfile userProfile;


        public void createProfile(String name, int age, String fitnessGoal, List<String> dietaryPreferences) {
            userProfile = new UserProfile(name, age, fitnessGoal, dietaryPreferences);
            System.out.println("Profile created: " + userProfile);
        }


        public void updateProfile(int newAge, String newFitnessGoal) {
            if (userProfile != null) {
                userProfile.updateProfile(newAge, newFitnessGoal);
                System.out.println("Profile updated: " + userProfile);
            } else {
                System.out.println("No existing profile to update.");
            }
        }


        public UserProfile getUserProfile() {
            return userProfile;
        }
    }


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
