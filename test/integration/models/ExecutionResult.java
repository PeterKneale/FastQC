package test.integration.models;

import static org.junit.jupiter.api.Assertions.*;

public class ExecutionResult {
    private final int exitCode;
    private final String output;

    public ExecutionResult(int exitCode, String output) {
        this.exitCode = exitCode;
        this.output = output;
    }

    public ExecutionResult AssertExitCode(int expected) {
        assertEquals(expected, exitCode, () -> "Unexpected exit code. Expected " + expected + " but got " + exitCode);
        return this;
    }

    public ExecutionResult AssertExitCodeIsZero() {
        return AssertExitCode(0);
    }

    public ExecutionResult AssertOutputContains(String expected) {
        assertTrue(output.contains(expected), () -> "Expected output to contain:\n" + expected + "\nGot:\n" + output);
        return this;
    }
}
