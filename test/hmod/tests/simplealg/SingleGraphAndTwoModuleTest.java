
package hmod.tests.simplealg;

import static flexbuilders.basic.BasicBuilders.builderFor;
import flexbuilders.core.BuildException;
import flexbuilders.graph.AutoLoadResolver;
import flexbuilders.graph.SecondaryIdsResolver;
import flexbuilders.graph.ExtensibleGraph;
import static flexbuilders.graph.GraphFactory.graph;
import flexbuilders.graph.NodeGroupData;
import hmod.core.Algorithm;
import hmod.simplealg.TestDataLoaders;
import hmod.simplealg.TestHeuristicLoaders;
import hmod.simplealg.TestIds;
import hmod.solvers.common.ac.HeuristicDataLoaders;
import hmod.solvers.common.ac.HeuristicIds;
import hmod.solvers.common.ac.HeuristicStartLoader;
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
    public void manualLoad()
    {        
        ExtensibleGraph graph = graph();
        
        graph.setValue(HeuristicIds.ITERATION_DATA, HeuristicDataLoaders.loadIterationData(graph));
        graph.setValue(HeuristicIds.TIME_ELAPSED_DATA, HeuristicDataLoaders.loadTimeElapsedData(graph));
        graph.setValue(HeuristicIds.FINISH_DATA, HeuristicDataLoaders.loadFinishData(graph));
        graph.setValue(HeuristicIds.INIT_START, graph.node(TestIds.INIT_START));
        graph.setValue(HeuristicIds.ITERATION_START, graph.node(TestIds.ITERATION_START));
        graph.setValue(HeuristicIds.FINISH_START, graph.node(TestIds.FINISH_START));
        graph.setValue(HeuristicIds.MAIN_START, HeuristicStartLoader.load(graph));
        
        graph.setValue(TestIds.TEST_DATA, TestDataLoaders.loadTestData(graph));        
        graph.setValue(TestIds.INIT_START, TestHeuristicLoaders.loadInitStart(graph));
        graph.setValue(TestIds.ITERATION_START, TestHeuristicLoaders.loadIterationStart(graph));
        graph.setValue(TestIds.FINISH_START, TestHeuristicLoaders.loadFinishStart(graph));
                
        graph.setValue(HeuristicIds.MAX_ITERATION_CONFIG, builderFor(10));        
        Algorithm.create(graph.node(HeuristicIds.MAIN_START).build()).start();
        
        graph.setValue(HeuristicIds.MAX_ITERATION_CONFIG, builderFor(20));
        Algorithm.create(graph.node(HeuristicIds.MAIN_START).build()).start();
    }
    
    @Test(expected = BuildException.class)
    public void manualLoadFail()
    {        
        ExtensibleGraph graph = graph();
        
        graph.setValue(HeuristicIds.ITERATION_DATA, HeuristicDataLoaders.loadIterationData(graph));
        graph.setValue(HeuristicIds.TIME_ELAPSED_DATA, HeuristicDataLoaders.loadTimeElapsedData(graph));
        graph.setValue(HeuristicIds.FINISH_DATA, HeuristicDataLoaders.loadFinishData(graph));
        graph.setValue(HeuristicIds.INIT_START, graph.node(TestIds.INIT_START));
        graph.setValue(HeuristicIds.ITERATION_START, graph.node(TestIds.ITERATION_START));
        //graph.setValue(HeuristicIds.FINISH_START, graph.node(TestIds.FINISH_START));
        graph.setValue(HeuristicIds.MAIN_START, HeuristicStartLoader.load(graph));
        
        graph.setValue(TestIds.TEST_DATA, TestDataLoaders.loadTestData(graph));        
        graph.setValue(TestIds.INIT_START, TestHeuristicLoaders.loadInitStart(graph));
        graph.setValue(TestIds.ITERATION_START, TestHeuristicLoaders.loadIterationStart(graph));
        graph.setValue(TestIds.FINISH_START, TestHeuristicLoaders.loadFinishStart(graph));
                
        graph.setValue(HeuristicIds.MAX_ITERATION_CONFIG, builderFor(10));        
        Algorithm.create(graph.node(HeuristicIds.MAIN_START).build()).start();
        
        graph.setValue(HeuristicIds.MAX_ITERATION_CONFIG, builderFor(20));
        Algorithm.create(graph.node(HeuristicIds.MAIN_START).build()).start();
    }
    
    @Test
    public void autoLoad()
    {        
        ExtensibleGraph graph = graph().
            addResolver(new AutoLoadResolver());
        
        graph.setValue(HeuristicIds.INIT_START, graph.node(TestIds.INIT_START));
        graph.setValue(HeuristicIds.ITERATION_START, graph.node(TestIds.ITERATION_START));
        graph.setValue(HeuristicIds.FINISH_START, graph.node(TestIds.FINISH_START));
        
        graph.setValue(HeuristicIds.MAX_ITERATION_CONFIG, builderFor(10));        
        Algorithm.create(graph.node(HeuristicIds.MAIN_START).build()).start();
        
        graph.setValue(HeuristicIds.MAX_ITERATION_CONFIG, builderFor(20));
        Algorithm.create(graph.node(HeuristicIds.MAIN_START).build()).start();
    }
    
    @Test(expected = BuildException.class)
    public void autoLoadFail()
    {        
        ExtensibleGraph graph = graph().
            addResolver(new AutoLoadResolver());
        
        graph.setValue(HeuristicIds.INIT_START, graph.node(TestIds.INIT_START));
        //graph.setValue(HeuristicIds.ITERATION_START, graph.node(TestIds.ITERATION_START));
        graph.setValue(HeuristicIds.FINISH_START, graph.node(TestIds.FINISH_START));
        
        graph.setValue(HeuristicIds.MAX_ITERATION_CONFIG, builderFor(10));        
        Algorithm.create(graph.node(HeuristicIds.MAIN_START).build()).start();
        
        graph.setValue(HeuristicIds.MAX_ITERATION_CONFIG, builderFor(20));
        Algorithm.create(graph.node(HeuristicIds.MAIN_START).build()).start();
    }
    
    @Test
    public void autoSet()
    {
        ExtensibleGraph graph = graph().
            addData(new NodeGroupData(TestIds.class)).
            addResolver(new AutoLoadResolver()).
            addResolver(new SecondaryIdsResolver());
        
        graph.setValue(HeuristicIds.MAX_ITERATION_CONFIG, builderFor(10));        
        Algorithm.create(graph.node(HeuristicIds.MAIN_START).build()).start();
        
        graph.setValue(HeuristicIds.MAX_ITERATION_CONFIG, builderFor(20));
        Algorithm.create(graph.node(HeuristicIds.MAIN_START).build()).start();
    }
    
    @Test(expected = BuildException.class)
    public void autoSetFail()
    {
        ExtensibleGraph graph = graph().
            addData(new NodeGroupData(HeuristicIds.class)).
            addResolver(new AutoLoadResolver()).
            addResolver(new SecondaryIdsResolver());
        
        graph.setValue(HeuristicIds.MAX_ITERATION_CONFIG, builderFor(10));        
        Algorithm.create(graph.node(HeuristicIds.MAIN_START).build()).start();
        
        graph.setValue(HeuristicIds.MAX_ITERATION_CONFIG, builderFor(20));
        Algorithm.create(graph.node(HeuristicIds.MAIN_START).build()).start();
    }
    
    /*@Test
    public void fullAutoLoad()
    {
        ExtensibleGraph graph = graph();
        
        graph.findNodes("hmod.simplealg.TestIdsC");        
        graph.setValue(HeuristicIds.MAX_ITERATION_CONFIG, builderFor(10));        
        Algorithm.create(graph.loadNode(HeuristicIds.MAIN_START).build()).start();
        
        graph.setValue(HeuristicIds.MAX_ITERATION_CONFIG, builderFor(20));
        Algorithm.create(graph.loadNode(HeuristicIds.MAIN_START).build()).start();
    }*/
}
