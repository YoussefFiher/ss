package be.unamur.info.b314.compiler.main;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InstructionTest{

    private static final Logger LOG = LoggerFactory.getLogger(InstructionTest.class);

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
    // public void test_while_ok() throws Exception {
    //     CompilerTestHelper.launchCompilation(
    //             "/syntax/Instructions/instruction.mojt",
    //             testFolder.newFile(),
    //             true,
    //             "instruction : while_ok"
    //     );
    // }

	// @Test
    // public void test_for_ok() throws Exception {
    //     CompilerTestHelper.launchCompilation(
    //             "/syntax/Instructions/for.mojt",
    //             testFolder.newFile(),
    //             true,
    //             "instruction : for_ok"
    //     );
    // }

	// @Test
    // public void test_if_ok() throws Exception {
    //     CompilerTestHelper.launchCompilation(
    //             "/syntax/Instructions/If.mojt",
    //             testFolder.newFile(),
    //             true,
    //             "instruction : if_ok"
    //     );
    // }

    // @Test
    // public void test_if_decl_ok() throws Exception {
    //     CompilerTestHelper.launchCompilation(
    //             "/syntax/Instructions/if_inner_decl.mojt",
    //             testFolder.newFile(),
    //             true,
    //             "instruction : if_inner_decl_ok"
    //     );
    // }

    // @Test
    // public void test_arrest_ok() throws Exception {
    //     CompilerTestHelper.launchCompilation(
    //             "/syntax/Instructions/arrest_simple.mojt",
    //             testFolder.newFile(),
    //             true,
    //             "instruction : arrest_ok"
    //     );
    // }

    // @Test
    // public void no_decl_in_instr_ko() throws Exception {
    //     CompilerTestHelper.launchCompilation(
    //             "/syntax/Instructions/instAndDecl.mojt",
    //             testFolder.newFile(),
    //             true,
    //             "instruction : no_decl_in_instr_ok"
    //     );
    // }

    // @Test
    // public void getter_ok() throws Exception {
    //     CompilerTestHelper.launchCompilation(
    //             "/syntax/Instructions/getter.mojt",
    //             testFolder.newFile(),
    //             true,
    //             "instruction : getter_ok"
    //     );
    // }

    // @Test
    // public void multiple_operation_ok() throws Exception {
    //     CompilerTestHelper.launchCompilation(
    //             "/syntax/Instructions/operation.mojt",
    //             testFolder.newFile(),
    //             true,
    //             "instruction : operation_ok"
    //     );
    // }
}
