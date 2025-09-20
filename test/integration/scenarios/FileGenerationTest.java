package test.integration.scenarios;

import org.junit.jupiter.api.Test;

import test.integration.models.CliWrapper;
import test.integration.models.TestScenarios;

public class FileGenerationTest {
    
    @Test
    public void files_are_generated() throws Exception {
        var scenario=TestScenarios.MinimalScenario;
        
        var cli=new CliWrapper();
        cli.Execute(new String[]{},scenario.FastqFile)
            .AssertOutputContains("Started analysis of minimal.fastq")
            .AssertOutputContains("Analysis complete for minimal.fastq")
            .AssertExitCodeIsZero();

        var zip = scenario
            .GetZipFile()
            .AssertExists()
            .AssertSize();
        
    }
}
