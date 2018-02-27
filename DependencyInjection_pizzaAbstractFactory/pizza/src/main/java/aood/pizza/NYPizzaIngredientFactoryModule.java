package aood.pizza;

import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class NYPizzaIngredientFactoryModule {
    @Provides
    @Singleton
    NYPizzaIngredientFactory provideFactory() {
        return new NYPizzaIngredientFactory();
    }
}
