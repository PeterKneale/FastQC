package test.integration.Parameters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import test.Fixtures.CliFixture;

public class VersionTest {

    @Test
    public void show_version_displays_name_and_semver() throws Exception {
        new CliFixture()
                .Execute("fastqc.show_version=true")
                .AssertExitCodeIsZero()
                .AssertOutputContains("FastQC v0.12.1");
    }

}
