package test.integration.models;

public class TestScenario {
    public TestScenario(String name) {
        super();
        Name = name;
        FastqFile = name + ".fastq";
        ExpectedHtmlFile = TestScenarios.TEST_DATA_DIR +  name + "_fastqc.html";
        ExpectedZipFile = TestScenarios.TEST_DATA_DIR +  name + "_fastqc.zip";
    }

    public String Name;
    public String FastqFile;
    public String ExpectedHtmlFile;
    public String ExpectedZipFile;

    public ZipFileModel GetZipFile(){
        var file = new java.io.File(ExpectedZipFile);
        return new ZipFileModel(file);
    }

    public HtmlFileModel GetHtmlFile(){
        var file = new java.io.File(ExpectedHtmlFile);
        return new HtmlFileModel(file);
    }
}