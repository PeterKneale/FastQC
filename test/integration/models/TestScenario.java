package test.integration.models;

import java.io.IOException;
import java.nio.file.Files;

public class TestScenario {

    public TestScenario(String name) {
        super();
        Name = name;
        FastqFile = name + ".fastq";
    }

    public String Name;
    public String FastqFile;
    public String FastQcDataSnapshotFile;

    public TestZipFileModel GetZipFile() {
        var file = new java.io.File(TestScenarios.TEST_DATA_DIR + Name + "_fastqc.zip");
        return new TestZipFileModel(this, file);
    }

    public TestHtmlFileModel GetHtmlFile() {
        var file = new java.io.File(TestScenarios.TEST_DATA_DIR + Name + "_fastqc.html");
        return new TestHtmlFileModel(this, file);
    }

    public String GetFastQcDataSnapshotFileContent() throws java.io.IOException {
        var path = new java.io.File(TestScenarios.TEST_DATA_DIR +  Name + "_fastqc_data.txt").toPath();
        try {
            return Files.readString(path);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file content of " + path.getFileName(), e);
        }
    }
}