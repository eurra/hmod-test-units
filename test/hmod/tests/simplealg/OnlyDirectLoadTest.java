
package hmod.tests.simplealg;

import static flexbuilders.basic.BasicBuilders.builderFor;
import flexbuilders.graph.BuilderGraph;
import static flexbuilders.graph.GraphFactory.graph;
import hmod.core.Algorithm;
import hmod.simplealg.TestIdsA;
import optefx.util.output.OutputConfigBuilder;
import optefx.util.output.OutputManager;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Enrique Urra C.
 */
public class OnlyDirectLoadTest
{
    public OnlyDirectLoadTest()
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
        
        graph.setValue(TestIdsA.ITERATION_CONFIG, builderFor(10));
        Algorithm.create(graph.loadNode(TestIdsA.MAIN_START).build()).start();
        
        graph.setValue(TestIdsA.ITERATION_CONFIG, builderFor(20));
        Algorithm.create(graph.loadNode(TestIdsA.MAIN_START).build()).start();
    }
}
