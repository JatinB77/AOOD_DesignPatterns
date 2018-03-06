package aood.pizza;

import javax.inject.Inject;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class PizzaTest extends TestCase {

    @Inject
    PizzaIngredientFactory factory;

    public PizzaTest(String testName) {
        super(testName);
        DaggerPizzaIngredientFactoryTestComponent
            .builder()
            .build()
            .inject(this);
    }

    public static Test suite() {
        return new TestSuite(PizzaTest.class);
    }

    public void testFactory() {
        assertNotNull(factory);
        assertTrue(factory.getName().contains("Mock"));

        Cheese cheese = factory.createCheese();
        assertNotNull(cheese);
        assertTrue(cheese.toString().contains("cheese"));

        Veggies[] veggies = factory.createVeggies();
        assertEquals(1, veggies.length);
        assertEquals("Test vegetable", veggies[0].toString());

        /* The 'clam' instance is null since clams were never mocked. */
        Clams clam = factory.createClam();
        assertNull(clam);
    }
}
