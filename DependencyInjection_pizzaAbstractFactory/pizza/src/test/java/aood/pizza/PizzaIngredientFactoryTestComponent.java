package aood.pizza;

import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = PizzaIngredientFactoryTestModule.class)
public interface PizzaIngredientFactoryTestComponent {
    void inject(PizzaTest test);
}
