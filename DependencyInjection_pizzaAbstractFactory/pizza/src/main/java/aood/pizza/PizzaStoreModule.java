package aood.pizza;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public class PizzaStoreModule{
	@Provides
	@Singleton

	PizzaStore providePizzaStore(){
		return new PizzaStore();
	}
}
