package aood.pizza;

import dagger.Module;
import dagger.Provides;
import static org.mockito.Mockito.*;
import javax.inject.Singleton;


@Module
public class NYPizzaIngredientFactoryTestModule {

    // Provides a mocked PizzaIngredientFactory instance
    // with some testable behavior
    @Provides
    @Singleton
    PizzaIngredientFactory provideFactory() {
        PizzaIngredientFactory pizzaIngredFact = mock(NYPizzaIngredientFactory.class);

        when(pizzaIngredFact.createCheese()).thenReturn( new Cheese() {
            @Override
            public String toString() {
                return "This is mocked cheese. This might as well be canned cheese.";
            }
        });

        when(pizzaIngredFact.createSauce()).thenReturn(new Sauce() {
            @Override
            public String toString() {
                return "In NYC we call it gravy.";
            }
        });


        return pizzaIngredFact;
    }

}