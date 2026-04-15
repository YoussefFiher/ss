package be.unamur.info.b314.compiler.main;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VarDeclTest{

    private static final Logger LOG = LoggerFactory.getLogger(VarDeclTest.class);

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
    //          "/syntax/declarationVar/simpleTest.mojt",
    //          testFolder.newFile(),
    //          true,
    //          "VarDecl : simple_ok"
    //     );
    // }

    // @Test
    // public void test_strange_emoji() throws Exception {
    //     CompilerTestHelper.launchCompilation(
    //          "/syntax/declarationVar/strangeEmoji.mojt",
    //          testFolder.newFile(),
    //          true,
    //          "VarDecl : strange_emoji_ok"
    //     );
    // }

    // @Test
    // public void test_group() throws Exception {
    //     CompilerTestHelper.launchCompilation(
    //          "/syntax/declarationVar/groupEmoji.mojt",
    //          testFolder.newFile(),
    //          true,
    //          "VarDecl : group_emoji_ok"
    //     );
    // }
    // @Test
    // public void test_ok() throws Exception {
    //     CompilerTestHelper.launchCompilation(
    //          "/syntax/affectations/ok/affect_all_types.mojt",
    //          testFolder.newFile(),
    //          true,
    //          "VarDecl : affect_all_types_ok"
    //     );
    // }
    @Test
    public void test_simple_ok() throws Exception {
        CompilerTestHelper.launchCompilation(
                "/syntax/function/simple.mojt",
                testFolder.newFile(),
                true,
                "function : simple_ok"
        );
    }

    @Test
    public void comparaison_test_ok() throws Exception {
        CompilerTestHelper.launchCompilation(
             "/syntax/affectations/ok/comparaison.mojt",
             testFolder.newFile(),
             true,
             "comparaison : Ok"
        );
    }


    // @Test
    // public void emojyUsed_test_ok() throws Exception {
    //     CompilerTestHelper.launchCompilation(
    //          "/syntax/declarationVar/emojyUsed.mojt",
    //          testFolder.newFile(),
    //          true,
    //          "emojyUsed : Ok"
    //     );
    // }
}