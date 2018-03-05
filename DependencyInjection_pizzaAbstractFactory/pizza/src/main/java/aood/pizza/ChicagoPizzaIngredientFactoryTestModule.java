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
        PizzaIngredientFactory pizzaIngredFact = mock(PizzaIngredientFactory.class);
        Cheese cheese = mock(Cheese.class);
        when(cheese.toString()).thenReturn("Plain Cheese");

        Dough dough = mock(Dough.class);

        when(pizzaIngredFact.createCheese()).thenReturn(cheese.toString());

    }

}
