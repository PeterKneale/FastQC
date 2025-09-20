package test.integration.parameters;

import org.junit.jupiter.api.Test;

import test.integration.models.CliWrapper;

public class VersionTest {

    @Test
    public void show_version_displays_name_and_semver() throws Exception {
        new CliWrapper()
                .Execute("fastqc.show_version=true")
                .AssertExitCodeIsZero()
                .AssertOutputContains("FastQC v0.12.1");
    }

}
