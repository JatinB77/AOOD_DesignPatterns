package aood.pizza;

import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class ChicagoPizzaIngredientFactoryModule {
    @Provides
    @Singleton
    ChicagoPizzaIngredientFactory provideFactory() {
        return new ChicagoPizzaIngredientFactory();
    }
}
