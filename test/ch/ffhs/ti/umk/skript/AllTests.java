package ch.ffhs.ti.umk.skript;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ MathTest.class, VarTest.class, DummyTest.class, IfElseEndTest.class, ForWhileTest.class, FunTest.class, FileTest.class })
public class AllTests {

}
