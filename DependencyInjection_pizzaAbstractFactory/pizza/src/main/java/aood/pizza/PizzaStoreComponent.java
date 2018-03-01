import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = PizzaStoreModule.class)
public interfce PizzaStoreComponent{
	PizzaStore getPizzaStore();
	void inject(PizzaStore pizzaStore);
}
