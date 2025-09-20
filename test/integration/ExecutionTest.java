package test.integration.scenarios;

import org.junit.jupiter.api.Test;

import test.integration.models.ExecutionHelper;
import test.integration.models.TestScenarios;

public class ExecutionTest {

    @Test
    public void files_are_generated() throws Exception {
        var scenario = TestScenarios.MinimalScenario;

        var cli = new ExecutionHelper();
        cli.Execute(new String[] {}, scenario.FastqFile)
                .AssertOutputContains("Started analysis of minimal.fastq")
                .AssertOutputContains("Analysis complete for minimal.fastq")
                .AssertExitCodeIsZero();

        var zip = scenario
            .GetZipFile();
        
        var folder = zip
                .assertExists()
                .assertSize()
                .unzip();

        var expectedFolderName = scenario.Name + "_fastqc/";
        folder
            .assertFilesExist();

    }
}
