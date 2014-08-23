
package hmod.simplealg;

import flexbuilders.graph.NodeLoader;
import hmod.core.Step;

/**
 *
 * @author Enrique Urra C.
 */
public final class TestIdsB
{
    public static final NodeLoader<Step> MAIN_START = MainStartLoaderB::load;
    public static final NodeLoader<Step> INIT_START = TestHeuristicLoaders::initStartLoader;    
    public static final NodeLoader<Step> ITERATION_START = TestHeuristicLoaders::iterationStartALoader;    
    public static final NodeLoader<Step> FINISH_START = TestHeuristicLoaders::finishStartLoader;

    private TestIdsB() {}
}