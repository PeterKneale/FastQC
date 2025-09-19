package test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.*;

import uk.ac.babraham.FastQC.Analysis.OfflineRunner;

public class AnalysisTest {

    public AnalysisTest() {
        super();
        // Headless for CI
        System.setProperty("java.awt.headless", "true");
    }

    @Test
    void output_files_are_produced(@TempDir Path outdir) throws IOException {

        Path input = Paths.get("test/data/minimal.fastq").toAbsolutePath().normalize();
        assertTrue(Files.isRegularFile(input), "Test input not found: " + input);

        String[] args = { "-o", outdir.toString(), input.toString() };

        // Run and assert it finishes in time
        assertTimeout(Duration.ofSeconds(30), () -> new OfflineRunner(args));

        // Check either HTML or ZIP is created
        Path html = outdir.resolve("minimal_fastqc.html");
        Path zip = outdir.resolve("minimal_fastqc.zip");
        assertTrue(Files.exists(zip), "Expected FastQC report not found in: " + outdir);
        assertTrue(Files.exists(html), "Expected FastQC report not found in: " + outdir);
    }
}
