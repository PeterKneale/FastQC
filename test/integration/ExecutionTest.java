package test.integration.scenarios;

import org.junit.jupiter.api.Test;

import test.integration.models.ExecutionHelper;
import test.integration.models.TestScenarios;

public class ExecutionTest {

    @Test
    public void zip_file_contains_all_files() throws Exception {
        var scenario = TestScenarios.MinimalScenario;

        var cli = new ExecutionHelper();
        cli.Execute(new String[] {}, scenario)
                .AssertStartedMessage(scenario)
                .AssertOutputContains("Analysis complete for minimal.fastq")
                .AssertExitCodeIsZero();

        // based on the name of the scenario, find the zip file.
        var zip = scenario.GetZipFile();
        
        // check the zip file appears to be valid and unzip it
        var folder = zip
                .assertExists()
                .assertSize()
                .unzip()
                .assertZipFileContent();
        
        // Match fastqc_data.txt against snapshot
        folder.assertfastqc_data_matches();

    }
}
