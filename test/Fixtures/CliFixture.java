package test.Fixtures;
import static org.junit.jupiter.api.Assertions.*;

public class CliFixture{

    // Convenience method for single parameter
    public CliResult Execute(String parameter) throws Exception {
        return Execute(new String[] { parameter });
    }

    // Main method to execute FastQC with given parameters
    public CliResult Execute(String[] parameters) throws Exception {
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

        ProcessBuilder pb = new ProcessBuilder(cmd);
        pb.redirectErrorStream(true);
        // Run from project root so relative classpath entries resolve
        pb.directory(new java.io.File(System.getProperty("user.dir")));

        Process p = pb.start();
        StringBuilder out = new StringBuilder();
        try (java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.InputStreamReader(p.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                out.append(line).append(System.lineSeparator());
            }
        }

        boolean finished = p.waitFor(30, java.util.concurrent.TimeUnit.SECONDS);
        if (!finished) {
            p.destroyForcibly();
            fail("FastQC CLI did not exit within 30s. Command: " + String.join(" ", cmd));
        }

        var exitCode = p.exitValue();
        var output = out.toString();
        return new CliResult(exitCode, output);
    }
}