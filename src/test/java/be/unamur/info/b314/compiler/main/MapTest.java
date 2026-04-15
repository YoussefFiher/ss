package be.unamur.info.b314.compiler.main;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MapTest{

    private static final Logger LOG = LoggerFactory.getLogger(MapTest.class);

    @Rule
    // Create a temporary folder for outputs deleted after tests
    public TemporaryFolder testFolder = new TemporaryFolder();

    @Rule
    // Print message on logger before each test
    public TestRule watcher = new TestWatcher() {
        @Override
        protected void starting(Description description) {
            LOG.info(String.format("Starting test: %s()...",
                    description.getMethodName()));
        }
    ;
    };

    /* Serie affectations OK */
    
    @Test
     public void test_simple_ok() throws Exception {
         CompilerTestHelper.launchCompilation(
                "/syntax/map/simple.map",
                testFolder.newFile(),
                true,
                "Map : simple_ok"
        );
    }

}