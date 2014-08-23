
package hmod.simplealg;

import static flexbuilders.graph.GraphFactory.nodeId;
import flexbuilders.graph.NodeLoader;
import flexbuilders.graph.NodeId;
import hmod.core.Step;
import hmod.solvers.common.ac.IterationHandler;
import hmod.test.TestHandler;

/**
 *
 * @author Enrique Urra C.
 */
public final class TestIdsA
{    
    public static final NodeLoader<TestHandler> TEST_DATA = DataLoaders::testDataLoader;
    public static final NodeLoader<IterationHandler> ITERATION_DATA = DataLoaders::iterationDataLoader;
    
    public static final NodeId<Integer> ITERATION_CONFIG = nodeId();
    
    public static final NodeLoader<Step> MAIN_START = MainStartLoaderA::load;
    public static final NodeId<Step> INIT_START = nodeId();    
    public static final NodeId<Step> ITERATION_START = nodeId();    
    public static final NodeId<Step> FINISH_START = nodeId();

    private TestIdsA() {}
}