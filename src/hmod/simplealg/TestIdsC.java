
package hmod.simplealg;

import static flexbuilders.graph.GraphFactory.customLoader;
import flexbuilders.graph.NodeLoader;
import flexbuilders.graph.AutoSetProcessor;
import hmod.core.Step;
import hmod.solvers.common.ac.HeuristicIds;

/**
 *
 * @author Enrique Urra C.
 */
public final class TestIdsC
{    
    public static final NodeLoader<Step> INIT_START = customLoader(
        TestHeuristicLoaders::initStartLoader,
        new AutoSetProcessor<>(TestIdsA.INIT_START, HeuristicIds.INIT_START)
    );
    
    public static final NodeLoader<Step> ITERATION_START = customLoader(
        TestHeuristicLoaders::iterationStartALoader,
        new AutoSetProcessor<>(TestIdsA.ITERATION_START, HeuristicIds.ITERATION_START)
    );
    
    public static final NodeLoader<Step> FINISH_START = customLoader(
        TestHeuristicLoaders::finishStartLoader,
        new AutoSetProcessor<>(TestIdsA.FINISH_START, HeuristicIds.FINISH_START)
    );

    private TestIdsC() {}
}