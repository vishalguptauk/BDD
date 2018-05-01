package de.ahus1.bdd;

import com.tngtech.jgiven.annotation.ScenarioStage;
import com.tngtech.jgiven.junit.ScenarioTest;
import de.ahus1.bdd.stage.GivenStartPage;
import de.ahus1.bdd.stage.ThenResultPage;
import de.ahus1.bdd.stage.ThenStartPage;
import de.ahus1.bdd.stage.WhenStartPage;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.ArquillianTest;
import org.jboss.arquillian.junit.ArquillianTestClass;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

public class StartPageFeature extends ScenarioTest<GivenStartPage, WhenStartPage, ThenStartPage> {

    @ClassRule
    public static ArquillianTestClass arquillianTestClass = new ArquillianTestClass();

    @Rule
    public ArquillianTest arquillianTest = new ArquillianTest();

    @Drone
    protected WebDriver browser;

    // here we inject a 4th scenario stage using the @ScenarioStage annotation
    @ScenarioStage
    private ThenResultPage thenResultPage;

    @Before
    public void setup() {
        browser.manage().window().setSize(new Dimension(1200, 800));
    }

    /**
     * The is the very simple test.
     */
    @Test
    public void should_show_the_start_page() {
        given().the_start_page_is_opened_in_browser();
        then().the_page_title_is("Willkommen!");
    }

    /**
     * This shows the usage of a 4th stage that is injected using @ScenarioStage
     */
    @Test
    public void should_have_working_search_capability() {
        given().the_start_page_is_opened_in_browser();
        when().searching_for("hystrix");
        thenResultPage
                .then().the_result_page_shows_a_result_count_of(3);
    }

}
