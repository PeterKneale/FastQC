package test.integration.models;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
public class FileModel{
    public File file;

    public FileModel(File file){
        this.file = file;
    }

    public FileModel AssertExists(){
        assertTrue(file.exists(), "Expected file "+file.getName()+" to exist");
        return this;
    }
    public FileModel AssertSize(){
        assertTrue(file.length()>0, "Expected file "+file.getName()+" to have size > 0");
        return this;
    }
}