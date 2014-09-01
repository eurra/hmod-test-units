
package hmod.simplealg;

import flexbuilders.core.BuildException;
import flexbuilders.core.NestedBuilder;
import flexbuilders.graph.BuilderGraph;
import hmod.core.Step;
import static hmod.loader.graph.AlgorithmBuilders.algorithmBlock;
import hmod.test.TestOperatorFactory;

/**
 *
 * @author Enrique Urra C.
 */
public final class TestHeuristicLoaders
{
    public static NestedBuilder<Step> loadInitStart(BuilderGraph graph) throws BuildException
    {
        TestOperatorFactory ops = TestOperatorFactory.getInstance();
        NestedBuilder testData = graph.node(TestIds.TEST_DATA);

        return algorithmBlock().
            run(ops::testInit, testData);
    }

    public static NestedBuilder<Step> loadIterationStart(BuilderGraph graph) throws BuildException
    {
        TestOperatorFactory ops = TestOperatorFactory.getInstance();
        NestedBuilder testData = graph.node(TestIds.TEST_DATA);

        return algorithmBlock().
            run(ops::test, testData);
    }
    
    public static NestedBuilder<Step> loadFinishStart(BuilderGraph graph) throws BuildException
    {
        TestOperatorFactory ops = TestOperatorFactory.getInstance();

        return algorithmBlock().
            run(ops::testFinish);
    }
}
