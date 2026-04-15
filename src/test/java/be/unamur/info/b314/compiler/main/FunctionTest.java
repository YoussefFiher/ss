package be.unamur.info.b314.compiler.main;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FunctionTest{

    private static final Logger LOG = LoggerFactory.getLogger(FunctionTest.class);

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


    
    // @Test
    // public void test_simple_ok() throws Exception {
    //     CompilerTestHelper.launchCompilation(
    //             "/syntax/function/simple.mojt",
    //             testFolder.newFile(),
    //             true,
    //             "function : simple_ok"
    //     );
    // }

    // @Test
    // public void test_one_complex_ok() throws Exception {
    //     CompilerTestHelper.launchCompilation(
    //             "/syntax/function/oneComplexFunction.mojt",
    //             testFolder.newFile(),
    //             true,
    //             "function : one_complex_ok"
    //     );
    // }
    
}