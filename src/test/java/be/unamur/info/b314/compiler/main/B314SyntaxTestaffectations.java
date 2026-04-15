package be.unamur.info.b314.compiler.main;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class B314SyntaxTestaffectations {

    private static final Logger LOG = LoggerFactory.getLogger(B314SyntaxTestaffectations.class);

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

    // @Test
    // public void testaffectations_affect_all_types_ok() throws Exception {
    //     CompilerTestHelper.launchCompilation(
    //             "/syntax/affectations/ok/affect_all_types.mojt",
    //             testFolder.newFile(),
    //             true,
    //             "affectations : affect_all_types"
    //     );
    // }

    // /* Serie affectations KO */

    // @Test
    // public void testaffectations_affect_without_semicolon_ko() throws Exception {
    //     CompilerTestHelper.launchCompilation(
    //             "/syntax/affectations/ko/affect_without_semicolon.mojt",
    //             testFolder.newFile(), 
    //             false, 
    //             "affectations : affect_without_semicolon"
    //     );
    // }

    // @Test
    // public void testaffectations_import_ok() throws Exception {
    //     CompilerTestHelper.launchCompilation(
    //             "/syntax/affectations/ok/importation.mojt",
    //             testFolder.newFile(),
    //             true,
    //             "affectations : importation"
    //     );
    // }

    
    // @Test
    // public void testaffectations_comment_ok() throws Exception {
    //     CompilerTestHelper.launchCompilation(
    //             "/syntax/affectations/ok/comment.mojt",
    //             testFolder.newFile(),
    //             true,
    //             "affectations : comment"
    //     );
    // }

    // @Test
    // public void test_ok() throws Exception {
    //     System.out.println("ok");
    // }
}