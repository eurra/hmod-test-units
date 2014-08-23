
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
class TestHeuristicLoaders
{
    public static NestedBuilder<Step> initStartLoader(BuilderGraph graph) throws BuildException
    {
        TestOperatorFactory ops = TestOperatorFactory.getInstance();
        NestedBuilder testData = graph.loadNode(TestIdsA.TEST_DATA);

        return algorithmBlock().
            run(ops::testInit, testData);
    }

    public static NestedBuilder<Step> iterationStartALoader(BuilderGraph graph) throws BuildException
    {
        TestOperatorFactory ops = TestOperatorFactory.getInstance();
        NestedBuilder testData = graph.loadNode(TestIdsA.TEST_DATA);

        return algorithmBlock().
            run(ops::test, testData);
    }
    
    public static NestedBuilder<Step> loadIterationBLoader(BuilderGraph graph) throws BuildException
    {
        TestOperatorFactory ops = TestOperatorFactory.getInstance();
        NestedBuilder testData = graph.loadNode(TestIdsA.TEST_DATA);

        return algorithmBlock().
            run(ops::test, testData);
    }
    
    public static NestedBuilder<Step> finishStartLoader(BuilderGraph graph) throws BuildException
    {
        TestOperatorFactory ops = TestOperatorFactory.getInstance();

        return algorithmBlock().
            run(ops::testFinish);
    }
}
