
package hmod.tests.simplealg;

import static flexbuilders.basic.BasicBuilders.builderFor;
import flexbuilders.core.BuildException;
import flexbuilders.graph.AutoLoadResolver;
import flexbuilders.graph.SecondaryIdsResolver;
import flexbuilders.graph.ExtensibleGraph;
import static flexbuilders.graph.GraphFactory.graph;
import flexbuilders.graph.GroupFilterResolver;
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
    public void manualLoad()
    {
        ExtensibleGraph graph1 = graph();
        ExtensibleGraph graph2 = graph().addDependencies(graph1);
        
        graph2.setValue(HeuristicIds.ITERATION_DATA, HeuristicDataLoaders.loadIterationData(graph2));
        graph2.setValue(HeuristicIds.TIME_ELAPSED_DATA, HeuristicDataLoaders.loadTimeElapsedData(graph2));
        graph2.setValue(HeuristicIds.FINISH_DATA, HeuristicDataLoaders.loadFinishData(graph2));
        graph2.setValue(HeuristicIds.MAIN_START, HeuristicStartLoader.load(graph2));
        
        graph1.setValue(TestIds.TEST_DATA, TestDataLoaders.loadTestData(graph1));        
        graph1.setValue(TestIds.INIT_START, TestHeuristicLoaders.loadInitStart(graph1));
        graph1.setValue(TestIds.ITERATION_START, TestHeuristicLoaders.loadIterationStart(graph1));
        graph1.setValue(TestIds.FINISH_START, TestHeuristicLoaders.loadFinishStart(graph1));
        graph1.setValue(HeuristicIds.INIT_START, graph1.node(TestIds.INIT_START));
        graph1.setValue(HeuristicIds.ITERATION_START, graph1.node(TestIds.ITERATION_START));
        graph1.setValue(HeuristicIds.FINISH_START, graph1.node(TestIds.FINISH_START));
                
        graph2.setValue(HeuristicIds.MAX_ITERATION_CONFIG, builderFor(10));        
        Algorithm.create(graph2.node(HeuristicIds.MAIN_START).build()).start();
        
        graph2.setValue(HeuristicIds.MAX_ITERATION_CONFIG, builderFor(20));
        Algorithm.create(graph2.node(HeuristicIds.MAIN_START).build()).start();
    }
    
    @Test(expected = BuildException.class)
    public void manualLoadFail()
    {
        ExtensibleGraph graph1 = graph();
        ExtensibleGraph graph2 = graph().addDependencies(graph1);
        
        graph2.setValue(HeuristicIds.ITERATION_DATA, HeuristicDataLoaders.loadIterationData(graph2));
        graph2.setValue(HeuristicIds.TIME_ELAPSED_DATA, HeuristicDataLoaders.loadTimeElapsedData(graph2));
        graph2.setValue(HeuristicIds.FINISH_DATA, HeuristicDataLoaders.loadFinishData(graph2));
        graph2.setValue(HeuristicIds.MAIN_START, HeuristicStartLoader.load(graph2));
        
        graph1.setValue(TestIds.TEST_DATA, TestDataLoaders.loadTestData(graph1));        
        graph1.setValue(TestIds.INIT_START, TestHeuristicLoaders.loadInitStart(graph1));
        graph1.setValue(TestIds.ITERATION_START, TestHeuristicLoaders.loadIterationStart(graph1));
        graph1.setValue(TestIds.FINISH_START, TestHeuristicLoaders.loadFinishStart(graph1));
        graph1.setValue(HeuristicIds.INIT_START, graph1.node(TestIds.INIT_START));
        graph1.setValue(HeuristicIds.ITERATION_START, graph1.node(TestIds.ITERATION_START));
        //graph1.setValue(HeuristicIds.FINISH_START, graph1.node(TestIds.FINISH_START));
                
        graph2.setValue(HeuristicIds.MAX_ITERATION_CONFIG, builderFor(10));        
        Algorithm.create(graph2.node(HeuristicIds.MAIN_START).build()).start();
        
        graph2.setValue(HeuristicIds.MAX_ITERATION_CONFIG, builderFor(20));
        Algorithm.create(graph2.node(HeuristicIds.MAIN_START).build()).start();
    }
    
    @Test
    public void autoLoad()
    {
        ExtensibleGraph graph1 = graph().
            addData(new NodeGroupData(TestIds.class)).
            addResolver(new GroupFilterResolver(new AutoLoadResolver()));
        
        ExtensibleGraph graph2 = graph().
            addDependencies(graph1).
            addData(new NodeGroupData(HeuristicIds.class)).
            addResolver(new GroupFilterResolver(new AutoLoadResolver()));
        
        graph1.setValue(HeuristicIds.INIT_START, graph1.node(TestIds.INIT_START));
        graph1.setValue(HeuristicIds.ITERATION_START, graph1.node(TestIds.ITERATION_START));
        graph1.setValue(HeuristicIds.FINISH_START, graph1.node(TestIds.FINISH_START));
        
        graph2.setValue(HeuristicIds.MAX_ITERATION_CONFIG, builderFor(10));        
        Algorithm.create(graph2.node(HeuristicIds.MAIN_START).build()).start();
        
        graph2.setValue(HeuristicIds.MAX_ITERATION_CONFIG, builderFor(20));
        Algorithm.create(graph2.node(HeuristicIds.MAIN_START).build()).start();
    }
    
    @Test(expected = BuildException.class)
    public void autoLoadFail()
    {
        ExtensibleGraph graph1 = graph().
            addData(new NodeGroupData(HeuristicIds.class)).
            addResolver(new GroupFilterResolver(new AutoLoadResolver()));
        
        ExtensibleGraph graph2 = graph().
            addDependencies(graph1).
            addData(new NodeGroupData(TestIds.class)).
            addResolver(new GroupFilterResolver(new AutoLoadResolver()));
        
        graph1.setValue(HeuristicIds.INIT_START, graph1.node(TestIds.INIT_START));
        graph1.setValue(HeuristicIds.ITERATION_START, graph1.node(TestIds.ITERATION_START));
        //graph1.setValue(HeuristicIds.FINISH_START, graph1.node(TestIds.FINISH_START));
        
        graph2.setValue(HeuristicIds.MAX_ITERATION_CONFIG, builderFor(10));        
        Algorithm.create(graph2.node(HeuristicIds.MAIN_START).build()).start();
        
        graph2.setValue(HeuristicIds.MAX_ITERATION_CONFIG, builderFor(20));
        Algorithm.create(graph2.node(HeuristicIds.MAIN_START).build()).start();
    }
    
    @Test
    public void autoSet()
    {        
        ExtensibleGraph graph1 = graph().
            addData(new NodeGroupData(TestIds.class)).
            addResolver(new GroupFilterResolver(new AutoLoadResolver())).
            addResolver(new SecondaryIdsResolver());
        
        ExtensibleGraph graph2 = graph().
            addDependencies(graph1).
            addData(new NodeGroupData(HeuristicIds.class)).
            addResolver(new GroupFilterResolver(new AutoLoadResolver())).
            addResolver(new SecondaryIdsResolver());
        
        graph2.setValue(HeuristicIds.MAX_ITERATION_CONFIG, builderFor(10));
        Algorithm.create(graph2.node(HeuristicIds.MAIN_START).build()).start();
        
        graph2.setValue(HeuristicIds.MAX_ITERATION_CONFIG, builderFor(20));
        Algorithm.create(graph2.node(HeuristicIds.MAIN_START).build()).start();
    }
    
    @Test
    public void circularGraphs()
    {        
        ExtensibleGraph graph1 = graph().
            addData(new NodeGroupData(TestIds.class)).
            addResolver(new GroupFilterResolver(new AutoLoadResolver())).
            addResolver(new SecondaryIdsResolver());
        
        ExtensibleGraph graph2 = graph().
            addDependencies(graph1).
            addData(new NodeGroupData(HeuristicIds.class)).
            addResolver(new GroupFilterResolver(new AutoLoadResolver())).
            addResolver(new SecondaryIdsResolver());
        
        graph1.addDependencies(graph2);
        
        graph2.setValue(HeuristicIds.MAX_ITERATION_CONFIG, builderFor(10));
        Algorithm.create(graph2.node(HeuristicIds.MAIN_START).build()).start();
        
        graph2.setValue(HeuristicIds.MAX_ITERATION_CONFIG, builderFor(20));
        Algorithm.create(graph2.node(HeuristicIds.MAIN_START).build()).start();
    }
    
    @Test(expected = BuildException.class)
    public void autoSetFail()
    {
        ExtensibleGraph graph1 = graph().
            addData(new NodeGroupData(HeuristicIds.class)).
            addResolver(new GroupFilterResolver(new AutoLoadResolver())).
            addResolver(new SecondaryIdsResolver());
        
        ExtensibleGraph graph2 = graph().
            addDependencies(graph1).
            addData(new NodeGroupData(TestIds.class)).
            addResolver(new GroupFilterResolver(new AutoLoadResolver())).
            addResolver(new SecondaryIdsResolver());
        
        graph2.setValue(HeuristicIds.MAX_ITERATION_CONFIG, builderFor(10));
        Algorithm.create(graph2.node(HeuristicIds.MAIN_START).build()).start();
        
        graph2.setValue(HeuristicIds.MAX_ITERATION_CONFIG, builderFor(20));
        Algorithm.create(graph2.node(HeuristicIds.MAIN_START).build()).start();
    }
}
