package aood.pizza;

import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = NYPizzaIngredientFactoryTestModule.class)
public interface NYPizzaIngredientFactoryComponent {
    void inject(PizzaStore store);
}
