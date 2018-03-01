package aood.pizza;

public class NYPizzaStoreApp {
    public static void main(String[] args) {
        PizzaStore store = DaggerPizzaStoreComponent.builder().build().getPizzaStore();
        // PizzaStore = blah
        // Pizza pizza = store.orderPizza("cheese")
        // orderPizza("clam")
        // orderPizza("pepperoni")
        // orderPizza("veggie")


    }
}
