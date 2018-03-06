package aood.pizza;

import dagger.Module;
import dagger.Provides;
import static org.mockito.Mockito.*;
import javax.inject.Singleton;

/**
 * Created by Jatin on 3/1/2018.
 */
@Module
public class PizzaIngredientFactoryTestModule {

    /**
     * Provides a mocked PizzaIngredientFactory instance with some testable
     * behavior. The instance does not implement all of the interface's methods.
     * Only the methods we want to test are implemented.
     */
    @Provides
    @Singleton
    PizzaIngredientFactory provideFactory() {
        PizzaIngredientFactory pizzaIngredFact = mock(
            PizzaIngredientFactory.class);
        when(pizzaIngredFact.getName()).thenReturn("Mock Pizza Factory");

        when(pizzaIngredFact.createCheese()).thenReturn(new Cheese() {
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

        Veggies[] veggies = {
            new Veggies() {
                @Override
                public String toString() {
                    return "Test vegetable";
                }
            }
        };
        when(pizzaIngredFact.createVeggies()).thenReturn(veggies);

        return pizzaIngredFact;
    }
}
