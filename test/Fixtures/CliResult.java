package test.Fixtures;

import static org.junit.jupiter.api.Assertions.*;

public class CliResult {
    private final int exitCode;
    private final String output;

    public CliResult(int exitCode, String output) {
        this.exitCode = exitCode;
        this.output = output;
    }

    public CliResult AssertExitCode(int expected) {
        assertEquals(expected, exitCode, () -> "Unexpected exit code. Got:\n" + exitCode + " Expected:\n" + expected);
        return this;
    }

    public CliResult AssertExitCodeIsZero() {
        return AssertExitCode(0);
    }

    public CliResult AssertOutputContains(String expected) {
        assertTrue(output.contains(expected), () -> "Expected output to contain:\n" + expected + "\nGot:\n" + output);
        return this;
    }
}
