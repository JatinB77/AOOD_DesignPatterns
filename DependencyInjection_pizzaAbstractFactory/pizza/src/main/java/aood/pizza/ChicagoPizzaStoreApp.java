package aood.pizza;

public class ChicagoPizzaStoreApp {
    public static void main(String[] args) {
        PizzaStore store = new PizzaStore();
        DaggerChicagoPizzaIngredientFactoryComponent.builder().build().inject(store);
        Pizza pizza = store.orderPizza("cheese");
        System.out.println(pizza.toString());
        pizza = store.orderPizza("clam");
        System.out.println(pizza.toString());
        pizza = store.orderPizza("pepperoni");

        System.out.println(pizza.toString());
        pizza = store.orderPizza("veggie");

        System.out.println(pizza.toString());

    }
}
