package test.integration.models;

import org.junit.jupiter.api.Test;

public class TestScenarios {

    public static final String TEST_DATA_DIR = "test/integration/data/";
    public static final TestScenario MinimalScenario = new TestScenario("minimal");
    public static final TestScenario SimpleScenario = new TestScenario("simple");
    public static final TestScenario ComplexScenario = new TestScenario("complex");

    public static TestScenario[] AllScenarios = new TestScenario[] {
            MinimalScenario,
            SimpleScenario,
            ComplexScenario
    };
}
