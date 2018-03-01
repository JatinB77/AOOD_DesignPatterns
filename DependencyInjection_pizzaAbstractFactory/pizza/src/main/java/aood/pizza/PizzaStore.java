package aood.pizza;

import javax.inject.Inject;

public class PizzaStore {

	@Inject
	PizzaIngredientFactory factory;

	public PizzaStore(){
		DaggerPizzaStoreComponent
			.builder()
			.build()
			.inject(this);
	}
	

	private Pizza createPizza(String item){
		Pizza pizza=null;
		if(item.equals("cheese")){
			pizza = new CheesePizza(factory);
			pizza.setName(factory.getName()+" Style Cheese Pizza");
		} else if(item.equals("veggie")){
			pizza = new VeggiePizza(factory);
			pizza.setName(factory.getName()+" Style Veggie Pizza");
		} else if(item.equals("clam")){
			pizza = new ClamPizza(factory);
			pizza.setName(factory.getName()+" Style Clam Pizza");
		} else if(item.equals("pepperoni")){
			pizza = new PepperoniPizza(factory);
			pizza.setName(factory.getName()+" Style Pepperoni Pizza");
		}
		return pizza;
	}
 
	public Pizza orderPizza(String type) {
		Pizza pizza = createPizza(type);
		System.out.println("--- Making a " + pizza.getName() + " ---");
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();
		return pizza;
	}
}
