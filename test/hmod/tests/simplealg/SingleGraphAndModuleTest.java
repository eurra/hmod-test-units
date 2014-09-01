
package hmod.tests.simplealg;

import static flexbuilders.basic.BasicBuilders.builderFor;
import flexbuilders.core.BuildException;
import flexbuilders.graph.AutoLoadResolver;
import flexbuilders.graph.ExtensibleGraph;
import static flexbuilders.graph.GraphFactory.graph;
import hmod.core.Algorithm;
import hmod.simplealg.TestDataLoaders;
import hmod.simplealg.MainStartLoader;
import hmod.simplealg.TestHeuristicLoaders;
import hmod.simplealg.TestIds;
import optefx.util.output.OutputConfigBuilder;
import optefx.util.output.OutputManager;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Enrique Urra C.
 */
public class SingleGraphAndModuleTest
{
    public SingleGraphAndModuleTest()
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
        
        graph.setValue(TestIds.ITERATION_DATA, TestDataLoaders.loadIterationData(graph));
        graph.setValue(TestIds.TEST_DATA, TestDataLoaders.loadTestData(graph));
        
        graph.setValue(TestIds.INIT_START, TestHeuristicLoaders.loadInitStart(graph));
        graph.setValue(TestIds.ITERATION_START, TestHeuristicLoaders.loadIterationStart(graph));
        graph.setValue(TestIds.FINISH_START, TestHeuristicLoaders.loadFinishStart(graph));
        graph.setValue(TestIds.MAIN_START, MainStartLoader.load(graph));
        
        graph.setValue(TestIds.ITERATION_CONFIG, builderFor(10));
        Algorithm.create(graph.node(TestIds.MAIN_START).build()).start();
        
        graph.setValue(TestIds.ITERATION_CONFIG, builderFor(15));
        Algorithm.create(graph.node(TestIds.MAIN_START).build()).start();
    }
        
    @Test
    public void autoLoad()
    {        
        ExtensibleGraph graph = graph().
            addResolver(new AutoLoadResolver());
        
        graph.setValue(TestIds.ITERATION_CONFIG, builderFor(10));
        Algorithm.create(graph.node(TestIds.MAIN_START).build()).start();
        
        graph.setValue(TestIds.ITERATION_CONFIG, builderFor(15));
        Algorithm.create(graph.node(TestIds.MAIN_START).build()).start();
    }
    
    @Test(expected = BuildException.class)
    public void autoLoadFail()
    {
        ExtensibleGraph graph = graph();
        graph.node(TestIds.MAIN_START).build();
    }
}
