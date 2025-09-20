package test.integration.models;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.Test;

public class TestHtmlFileModel {
    private final File file;
    private final TestScenario scenario;

    public TestHtmlFileModel(TestScenario scenario, File file) {
        this.scenario = scenario;
        this.file = file;
    }

    public TestHtmlFileModel assertExists() {
        assertTrue(file.exists(), "Expected file " + file.getName() + " to exist");
        return this;
    }

    public TestHtmlFileModel assertSize() {
        assertTrue(file.length() > 0, "Expected file " + file.getName() + " to have size > 0");
        return this;
    }
}