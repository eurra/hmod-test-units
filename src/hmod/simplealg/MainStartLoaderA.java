
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
class MainStartLoaderA
{
    public static NestedBuilder<Step> load(BuilderGraph graph) throws BuildException
    {
        HeuristicOperatorFactory hOps = HeuristicOperatorFactory.getInstance();
        NestedBuilder iterationData = graph.loadNode(TestIdsA.ITERATION_DATA);
        
        return algorithmBlock().
            call(graph.loadNode(TestIdsB.INIT_START)).
            repeat().
                call(graph.loadNode(TestIdsB.ITERATION_START)).
                run(hOps::nextIteration, iterationData).
            until(hOps::checkIterationsFinished, iterationData).
            call(graph.loadNode(TestIdsB.FINISH_START));
    }
}
