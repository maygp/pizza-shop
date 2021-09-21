package br.edu.ifpb.padroes.service;

import br.edu.ifpb.padroes.api.damenos.DamenosPizza;
import br.edu.ifpb.padroes.api.damenos.DamenosServiceImpl;
import br.edu.ifpb.padroes.api.damenos.proxy.DamenosService;
import br.edu.ifpb.padroes.api.pizzahot.PizzaHotPizza;
import br.edu.ifpb.padroes.api.pizzahot.PizzaHotServiceImpl;
import br.edu.ifpb.padroes.api.pizzahot.proxy.PizzaHotService;
import br.edu.ifpb.padroes.domain.Pizza;
import br.edu.ifpb.padroes.domain.adapter.DamenosAdapter;
import br.edu.ifpb.padroes.domain.adapter.PizzahotAdapter;

import java.util.ArrayList;
import java.util.List;

public class PizzaShopService {

    private DamenosService damenosService;
    private PizzaHotService pizzaHotService;

    public PizzaShopService() {
        // TODO - alterar criação de instância para chamar para o Proxy de Cache
        damenosService = new DamenosServiceImpl();
        pizzaHotService = new PizzaHotServiceImpl();
    }

    public void orderPizza(Pizza pizza) {

        Float totalPrice = pizza.getPrice();
        String name = pizza.getName();

        System.out.println(String.format("New order for = %s", name));
        System.out.println(String.format("Total price = %f", totalPrice));

    }

    public List<DamenosPizza> getPizzasDamenos() {
        return damenosService.getPizzas();
    }

    public List<PizzaHotPizza> getPizzasPizzaHot() {
        return pizzaHotService.getPizzas();
    }
    
    public List<Pizza> getPizzas(){
    	List<Pizza> adapterPizza = new ArrayList<>();
    	List<DamenosPizza> adapterDamenos = damenosService.getPizzas();
    	List<PizzaHotPizza> adapterHots = pizzaHotService.getPizzas();

    	for (DamenosPizza adapterDameno: adapterDamenos ) {
    		adapterPizza.add(new DamenosAdapter(adapterDameno));
    	}
    	for (PizzaHotPizza adapterHot: adapterHots ) {
    		adapterPizza.add(new PizzahotAdapter(adapterHot));
    	}
    	return adapterPizza;
    	
    }

}
