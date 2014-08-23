
package hmod.tests.simplealg;

import static flexbuilders.basic.BasicBuilders.builderFor;
import flexbuilders.core.BuildException;
import static flexbuilders.graph.GraphFactory.graph;
import static flexbuilders.graph.GraphFactory.scannerGraph;
import flexbuilders.graph.NestableGraph;
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
public class TwoGraphAndTwoModuleTest
{
    public TwoGraphAndTwoModuleTest()
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
        NestableGraph graph1 = graph();
        
        graph1.setValue(HeuristicIds.INIT_START, graph1.loadNode(TestIdsB.INIT_START));
        graph1.setValue(HeuristicIds.ITERATION_START, graph1.loadNode(TestIdsB.ITERATION_START));
        graph1.setValue(HeuristicIds.FINISH_START, graph1.loadNode(TestIdsB.FINISH_START));
        
        NestableGraph graph2 = graph(graph1);
        graph2.setValue(HeuristicIds.MAX_ITERATION_CONFIG, builderFor(5));        
        Algorithm.create(graph2.loadNode(HeuristicIds.MAIN_START).build()).start();
        
        graph2.setValue(HeuristicIds.MAX_ITERATION_CONFIG, builderFor(15));
        Algorithm.create(graph2.loadNode(HeuristicIds.MAIN_START).build()).start();
    }
    
    @Test(expected = BuildException.class)
    public void noAutoLoadFail()
    {
        NestableGraph graph1 = graph();
        
        graph1.setValue(HeuristicIds.INIT_START, graph1.loadNode(TestIdsB.INIT_START));
        graph1.setValue(HeuristicIds.ITERATION_START, graph1.loadNode(TestIdsB.ITERATION_START));
        
        NestableGraph graph2 = graph(graph1);
        graph2.setValue(HeuristicIds.MAX_ITERATION_CONFIG, builderFor(5));        
        Algorithm.create(graph2.loadNode(HeuristicIds.MAIN_START).build()).start();
        
        graph2.setValue(HeuristicIds.MAX_ITERATION_CONFIG, builderFor(15));
        Algorithm.create(graph2.loadNode(HeuristicIds.MAIN_START).build()).start();
    }
    
    @Test
    public void partialAutoLoad()
    {
        NestableGraph graph1 = graph();
        
        graph1.loadNode(TestIdsC.INIT_START);
        graph1.loadNode(TestIdsC.ITERATION_START);
        graph1.loadNode(TestIdsC.FINISH_START);
        NestableGraph graph2 = graph(graph1);
        graph2.setValue(HeuristicIds.MAX_ITERATION_CONFIG, builderFor(5));        
        Algorithm.create(graph2.loadNode(HeuristicIds.MAIN_START).build()).start();
        
        graph2.setValue(HeuristicIds.MAX_ITERATION_CONFIG, builderFor(15));
        Algorithm.create(graph2.loadNode(HeuristicIds.MAIN_START).build()).start();
    }
    
    @Test(expected = BuildException.class)
    public void partialAutoLoadFail()
    {
        NestableGraph graph1 = graph();
        
        graph1.loadNode(TestIdsC.INIT_START);
        graph1.loadNode(TestIdsC.ITERATION_START);
        
        NestableGraph graph2 = graph(graph1);
        graph2.setValue(HeuristicIds.MAX_ITERATION_CONFIG, builderFor(5));        
        Algorithm.create(graph2.loadNode(HeuristicIds.MAIN_START).build()).start();
        
        graph2.setValue(HeuristicIds.MAX_ITERATION_CONFIG, builderFor(15));
        Algorithm.create(graph2.loadNode(HeuristicIds.MAIN_START).build()).start();
    }
    
    @Test
    public void fullAutoLoad()
    {
        ScannableGraph graph1 = scannerGraph();
        graph1.findNodes("hmod.simplealg.TestIdsC");
        
        NestableGraph graph2 = graph(graph1);
        graph2.setValue(HeuristicIds.MAX_ITERATION_CONFIG, builderFor(5));        
        Algorithm.create(graph2.loadNode(HeuristicIds.MAIN_START).build()).start();
        
        graph2.setValue(HeuristicIds.MAX_ITERATION_CONFIG, builderFor(15));
        Algorithm.create(graph2.loadNode(HeuristicIds.MAIN_START).build()).start();
    }
    
    @Test(expected = BuildException.class)
    public void fullAutoLoadFail()
    {
        ScannableGraph graph1 = scannerGraph();
        graph1.findNodes("hmod.solvers.common.ac.HeuristicIds");
        NestableGraph graph2 = graph(graph1);
        graph2.setValue(HeuristicIds.MAX_ITERATION_CONFIG, builderFor(5));        
        Algorithm.create(graph2.loadNode(HeuristicIds.MAIN_START).build()).start();
    }
}
