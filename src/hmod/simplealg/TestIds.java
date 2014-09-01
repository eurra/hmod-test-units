
package hmod.simplealg;

import static flexbuilders.graph.GraphFactory.nodeId;
import flexbuilders.graph.NodeLoaderData;
import flexbuilders.graph.SecondaryIdsData;
import flexbuilders.graph.NodeGroup;
import flexbuilders.graph.NodeId;
import hmod.core.Step;
import hmod.solvers.common.ac.HeuristicIds;
import hmod.solvers.common.ac.IterationHandler;
import hmod.test.TestHandler;

/**
 *
 * @author Enrique Urra C.
 */
public final class TestIds implements NodeGroup
{  
    public static final NodeId<TestHandler> TEST_DATA = nodeId(
        "TestIds.TEST_DATA",
        new NodeLoaderData<>(TestDataLoaders::loadTestData)
    );
    
    public static final NodeId<IterationHandler> ITERATION_DATA = nodeId(
        "TestIds.ITERATION_DATA",
        new NodeLoaderData<>(TestDataLoaders::loadIterationData)
    );
    
    public static final NodeId<Integer> ITERATION_CONFIG = nodeId(
        "TestIds.ITERATION_CONFIG"
    );
    
    public static final NodeId<Step> MAIN_START = nodeId(
        "TestIds.MAIN_START",
        new NodeLoaderData<>(MainStartLoader::load)
    );
    
    public static final NodeId<Step> INIT_START = nodeId(
        "TestIds.INIT_START",
        new NodeLoaderData<>(TestHeuristicLoaders::loadInitStart),
        new SecondaryIdsData<>(HeuristicIds.INIT_START)
    );   
    
    public static final NodeId<Step> ITERATION_START = nodeId(
        "TestIds.ITERATION_START",
        new NodeLoaderData<>(TestHeuristicLoaders::loadIterationStart),
        new SecondaryIdsData<>(HeuristicIds.ITERATION_START)
    ); 
    
    public static final NodeId<Step> FINISH_START = nodeId(
        "TestIds.FINISH_START",
        new NodeLoaderData<>(TestHeuristicLoaders::loadFinishStart),
        new SecondaryIdsData<>(HeuristicIds.FINISH_START)
    );

    private TestIds() {}
}