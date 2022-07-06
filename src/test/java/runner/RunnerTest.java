package runner;

import base.BaseTest;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(features="src/test/java/resources",
        glue={"steps"})
public class RunnerTest extends BaseTest
{
}
