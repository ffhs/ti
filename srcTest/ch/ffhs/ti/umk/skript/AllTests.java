package ch.ffhs.ti.umk.skript;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ArithmeticTest.class, VariableTest.class, SimpleStatementsTest.class, IfElseTest.class, LoopsTest.class, MethodTest.class, FilebasedTest.class })
public class AllTests {

}
