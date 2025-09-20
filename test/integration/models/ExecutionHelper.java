package test.integration.models;

import static org.junit.jupiter.api.Assertions.fail;

import java.nio.file.Path;

public class ExecutionHelper {
    public static final int DEFAULT_TIMEOUT_SECONDS = 30;

    // Convenience methods
    public ExecutionResult Execute(String parameter) throws Exception {
        return DoExecute(new String[]{parameter}, null);
    }
    public ExecutionResult Execute(String parameter, TestScenario scenario) throws Exception {
        return DoExecute(new String[]{parameter}, scenario.Name);
    }
    public ExecutionResult Execute(String[] parameters, TestScenario scenario) throws Exception {
        return DoExecute(parameters, scenario.FastqFile);
    }

    // Execute FastQC with given parameters
    private ExecutionResult DoExecute(String[] parameters, String fileName) throws Exception {
        // Build a classpath that matches the CLI example plus compiled classes in bin/
        String sep = java.io.File.pathSeparator;
        String cp = String.join(sep,
                "bin",
                ".",
                "jbzip2-0.9.jar",
                "cisd-jhdf5.jar",
                "htsjdk.jar");

        java.util.List<String> cmd = new java.util.ArrayList<>();
        cmd.add("java");
        cmd.add("-Xmx250m");
        for (String parameter : parameters) {
            cmd.add("-D" + parameter);
        }
        cmd.add("-cp");
        cmd.add(cp);
        cmd.add("uk.ac.babraham.FastQC.FastQCApplication");
        if (fileName != null) {
            cmd.add(Path.of(TestScenarios.TEST_DATA_DIR, fileName).toString());
        }

        ProcessBuilder pb = new ProcessBuilder(cmd);
        pb.redirectErrorStream(true);
        // Run from project root so relative classpath entries resolve
        pb.directory(new java.io.File(System.getProperty("user.dir")));

        Process p = pb.start();
        StringBuilder out = new StringBuilder();
        try (java.io.BufferedReader reader = new java.io.BufferedReader(
                new java.io.InputStreamReader(p.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                out.append(line).append(System.lineSeparator());
            }
        }

        boolean finished = p.waitFor(DEFAULT_TIMEOUT_SECONDS, java.util.concurrent.TimeUnit.SECONDS);
        if (!finished) {
            p.destroyForcibly();
            fail("FastQC CLI did not exit within " + DEFAULT_TIMEOUT_SECONDS + "s. Command: " + String.join(" ", cmd));
        }

        var exitCode = p.exitValue();
        var output = out.toString();
        return new ExecutionResult(exitCode, output);
    }
}