package aood.pizza;

import dagger.Module;
import dagger.Provides;
import static org.mockito.Mockito.*;
import javax.inject.Singleton;

/**
 * Created by Jatin on 3/1/2018.
 */
public class ChicagoPizzaIngredientFactoryTestModule {

    // Provides a mocked PizzaIngredientFactory instance
    // with some testable behavior
    @Provides
    @Singleton
    PizzaIngredientFactory provideFactory() {
        PizzaIngredientFactory pizzaIngredFact = mock(ChicagoPizzaIngredientFactory.class);

        when(pizzaIngredFact.createCheese()).thenReturn( new Cheese() {
            @Override
            public String toString() {
                return "I can't believe it's not cheese.";
            }
        });

        when(pizzaIngredFact.createSauce()).thenReturn(new Sauce() {
            @Override
            public String toString() {
                return "Spicy Pasta Sauce.";
            }
        });

        /*
        Cheese cheese = mock(Cheese.class);
        Dough dough = mock(Dough.class);
        when(cheese.toString()).thenReturn("Plain Cheese");
        when(pizzaIngredFact.createCheese()).thenReturn(cheese.toString());
        */

        return pizzaIngredFact;
    }

}
