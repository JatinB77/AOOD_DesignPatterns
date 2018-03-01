package aood.pizza;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = ChicagoPizzaIngredientFactoryModule.class)
public interface ChicagoPizzaIngredientFactoryComponent {

    void inject(PizzaStore store);
}
