
package hmod.simplealg;

import flexbuilders.core.BuildException;
import flexbuilders.core.NestedBuilder;
import flexbuilders.graph.BuilderGraph;
import hmod.core.Step;
import static hmod.loader.graph.AlgorithmBuilders.algorithmBlock;
import hmod.solvers.common.ac.HeuristicOperatorFactory;

/**
 *
 * @author Enrique Urra C.
 */
public final class MainStartLoader
{
    public static NestedBuilder<Step> load(BuilderGraph graph) throws BuildException
    {
        HeuristicOperatorFactory hOps = HeuristicOperatorFactory.getInstance();
        NestedBuilder iterationData = graph.node(TestIds.ITERATION_DATA);
        
        return algorithmBlock().
            call(graph.node(TestIds.INIT_START)).
            repeat().
                call(graph.node(TestIds.ITERATION_START)).
                run(hOps::nextIteration, iterationData).
            until(hOps::checkIterationsFinished, iterationData).
            call(graph.node(TestIds.FINISH_START));
    }
}
