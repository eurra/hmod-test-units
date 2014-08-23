
package hmod.simplealg;

import static flexbuilders.basic.BasicBuilders.builderFor;
import flexbuilders.core.BuildException;
import flexbuilders.core.NestedBuilder;
import flexbuilders.graph.BuilderGraph;
import static hmod.loader.graph.AlgorithmBuilders.argFactory;
import hmod.solvers.common.ac.HeuristicHandlerFactory;
import hmod.solvers.common.ac.IterationHandler;
import hmod.test.TestHandler;
import hmod.test.TestHandlerFactory;

/**
 *
 * @author Enrique Urra C.
 */
class DataLoaders
{
    public static NestedBuilder<TestHandler> testDataLoader(BuilderGraph graph) throws BuildException
    {
        TestHandlerFactory testHandlers = TestHandlerFactory.getInstance();
        return argFactory(testHandlers::test, builderFor(0));
    }
    
    public static NestedBuilder<IterationHandler> iterationDataLoader(BuilderGraph graph) throws BuildException
    {
        HeuristicHandlerFactory heuristicHandlers = HeuristicHandlerFactory.getInstance();
        return argFactory(heuristicHandlers::iteration, graph.getNode(TestIdsA.ITERATION_CONFIG));
    }
}
