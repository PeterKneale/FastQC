package test.integration.models;
import java.io.File;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestZipFolderModel {
    private final File folder;
    private final TestScenario scenario;

    public TestZipFolderModel(TestScenario scenario, File folder) {
        this.scenario = scenario;
        this.folder = folder;
    }
    
    public TestZipFolderModel assertFilesExist(){
        return 
            // Check file exist
            assertFileExists("fastqc_data.txt")
            .assertFileExists("fastqc_report.html")
            .assertFileExists("fastqc.fo")
            .assertFileExists("summary.txt")
            // Check images exist
            .assertImageExists("adapter_content")
            .assertImageExists("duplication_levels")
            .assertImageExists("per_base_n_content")
            .assertImageExists("per_base_quality")
            .assertImageExists("per_base_sequence_content")
            .assertImageExists("per_sequence_gc_content")
            .assertImageExists("per_sequence_quality")
            .assertImageExists("sequence_length_distribution");
    }

    private TestZipFolderModel assertImageExists(String name) {
        return assertFileExists("Images/" + name + ".png").assertFileExists("Images/" + name + ".svg");
    }

    private TestZipFolderModel assertFileExists(String name) {
        var file = new File(folder, name);
        assertTrue(file.exists(), () -> "Expected file to exist: " + file.getAbsolutePath());
        assertTrue(file.isFile(), () -> "Expected path to be a file: " + file.getAbsolutePath());
        return this;
    }
}