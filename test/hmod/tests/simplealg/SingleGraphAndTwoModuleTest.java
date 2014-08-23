
package hmod.tests.simplealg;

import static flexbuilders.basic.BasicBuilders.builderFor;
import flexbuilders.core.BuildException;
import flexbuilders.graph.BuilderGraph;
import static flexbuilders.graph.GraphFactory.graph;
import static flexbuilders.graph.GraphFactory.scannerGraph;
import flexbuilders.graph.ScannableGraph;
import hmod.core.Algorithm;
import hmod.simplealg.TestIdsB;
import hmod.simplealg.TestIdsC;
import hmod.solvers.common.ac.HeuristicIds;
import optefx.util.output.OutputConfigBuilder;
import optefx.util.output.OutputManager;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Enrique Urra C.
 */
public class SingleGraphAndTwoModuleTest
{
    public SingleGraphAndTwoModuleTest()
    {
    }
    
    @BeforeClass
    public static void init()
    {
        OutputManager.getCurrent().setOutputsFromConfig(
            new OutputConfigBuilder().addSystemOutputId("test").
                build()
        ); 
    }

    @Test
    public void noAutoLoad()
    {        
        BuilderGraph graph = graph();
        
        graph.setValue(HeuristicIds.INIT_START, graph.loadNode(TestIdsB.INIT_START));
        graph.setValue(HeuristicIds.ITERATION_START, graph.loadNode(TestIdsB.ITERATION_START));
        graph.setValue(HeuristicIds.FINISH_START, graph.loadNode(TestIdsB.FINISH_START));
        graph.setValue(HeuristicIds.MAX_ITERATION_CONFIG, builderFor(10));        
        Algorithm.create(graph.loadNode(HeuristicIds.MAIN_START).build()).start();
        
        graph.setValue(HeuristicIds.MAX_ITERATION_CONFIG, builderFor(20));
        Algorithm.create(graph.loadNode(HeuristicIds.MAIN_START).build()).start();
    }
    
    @Test
    public void partialAutoLoad()
    {        
        BuilderGraph graph = graph();
        
        graph.loadNode(TestIdsC.INIT_START);
        graph.loadNode(TestIdsC.ITERATION_START);
        graph.loadNode(TestIdsC.FINISH_START);
        graph.setValue(HeuristicIds.MAX_ITERATION_CONFIG, builderFor(10));        
        Algorithm.create(graph.loadNode(HeuristicIds.MAIN_START).build()).start();
        
        graph.setValue(HeuristicIds.MAX_ITERATION_CONFIG, builderFor(20));
        Algorithm.create(graph.loadNode(HeuristicIds.MAIN_START).build()).start();
    }
    
    @Test
    public void fullAutoLoad()
    {
        ScannableGraph graph = scannerGraph();
        
        graph.findNodes("hmod.simplealg.TestIdsC");        
        graph.setValue(HeuristicIds.MAX_ITERATION_CONFIG, builderFor(10));        
        Algorithm.create(graph.loadNode(HeuristicIds.MAIN_START).build()).start();
        
        graph.setValue(HeuristicIds.MAX_ITERATION_CONFIG, builderFor(20));
        Algorithm.create(graph.loadNode(HeuristicIds.MAIN_START).build()).start();
    }
    
    @Test(expected = BuildException.class)
    public void fullAutoLoadFail()
    {
        ScannableGraph graph = scannerGraph();
        
        graph.findNodes("hmod.solvers.common.ac.HeuristicIds");        
        graph.setValue(HeuristicIds.MAX_ITERATION_CONFIG, builderFor(10));        
        graph.loadNode(HeuristicIds.MAIN_START).build();
    }
}
