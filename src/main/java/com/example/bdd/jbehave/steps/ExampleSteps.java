package com.example.bdd.jbehave.steps;

import org.jbehave.core.annotations.*;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class ExampleSteps {
    int injectedData;

    //A simple Given, givens are for test preconditions and test data
    @Given("some test data")
    public void given() {
        System.out.println("This is a basic given step.");
    }

    //You can have more than one of any kind of annotated step
    //This example also shows annotation.
    //The @Named part is optional, and allows you to have a mismatch between the $variable_name and your java parameter
    @Given("you can inject data as well: $injected")
    public void givenSomeInjectedData(@Named("injected") int injected) {
        injectedData = injected;
        System.out.println("You injected this data into the method: " + injected);
        System.out.println("We set a class member to its value so it's available in subsequent methods");
    }

    //When is a single test action.
    //If you have multiple test actions, chain them in whens using the And keyword
    @When("you perform a testing action")
    public void when() {
        System.out.println("This is where your test step should be captured");
    }

    //if you architect well, you only need to inject data once by using class members
    @Then("you can verify them against your conclusion")
    public void then() {
        System.out.println("This should pass.");
        //this is just an arbitrary assignment
        int results = 1000;
        assertThat(results, equalTo(injectedData));
    }

    //A method can be annotated with multiple step types,
    //though in general this pattern should be avoided, it is possible
    @Given("you publish your data into the system")
    @When("you publish your data into the system")
    public void givenAndWhen() {
    }

    //If you have a step that is code identical but would be clearer with different scenario text, you can alias the step
    @Given("a precondition")
    @Alias("a testing precondition")
    public void givenWithAnAlias() {
    }

    //if you have multiple aliases, you can use a single Aliases annotation rather than use the Alias annotation repeatedly
    @Given("a known state")
    @Aliases(values = {"reset the state", "some test clean up step"})
    public void givenWithMultipleAliases() {
    }

    //
    @Then("the system exhibits a behavior")
    public void thenCheckTheExpectedBehavior() {
        System.out.println("This should fail.");
        //this is just an arbitrary assignment
        int results = -4;
        assertThat(results, equalTo(injectedData));
    }

}