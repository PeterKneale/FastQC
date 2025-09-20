package test.integration.parameters;

import org.junit.jupiter.api.Test;

import test.integration.models.CliWrapper;

public class OutDirTest {
    
    @Test
    public void shows_error_when_0() throws Exception {
        new CliWrapper()
            .Execute("fastqc.output_dir=this_folder_does_not_exist")
            .AssertExitCode(1)
            .AssertOutputContains("Output dir this_folder_does_not_exist doesn't exist or isn't writeable");
    }
}
