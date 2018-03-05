package aood.pizza;

import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = NYPizzaIngredientFactoryModule.class)
public interface NYPizzaIngredientFactoryComponent {
    void inject(PizzaStore store);
}
