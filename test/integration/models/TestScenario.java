package test.integration.models;

public class TestScenario {
    public TestScenario(String name) {
        super();
        Name = name;
        FastqFile = name + ".fastq";
    }

    public String Name;
    public String FastqFile;

    public TestZipFileModel GetZipFile(){
        var file = new java.io.File(TestScenarios.TEST_DATA_DIR + Name + "_fastqc.zip");
        return new TestZipFileModel(this, file);
    }

    public TestHtmlFileModel GetHtmlFile(){
        var file = new java.io.File(TestScenarios.TEST_DATA_DIR + Name + "_fastqc.html");
        return new TestHtmlFileModel(this, file);
    }
}