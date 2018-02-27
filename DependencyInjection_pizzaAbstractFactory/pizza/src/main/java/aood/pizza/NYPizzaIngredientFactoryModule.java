package aood.pizza;

import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class NYPizzaIngredientFactoryModule {
    @Provides
    @Singleton
    PizzaIngredientFactory provideFactory() {
        return new NYPizzaIngredientFactory();
    }
}
