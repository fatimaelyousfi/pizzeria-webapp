package dev.pizzeria;


import java.util.List;

import fr.pizzeria.model.Pizza;

/**
 * interface permettant de d�finir toute les m�thodes.
 */
public interface PizzaDao {
	/**
	 * m�thode qui permet de lister les pizzas
	 * 
	 * @return List<Pizza>
	 */
	List<Pizza> findAllPizzas();

	/**
	 * m�thode qui permet de sauvegarder les pizzas
	 * 
	 * @param pizza
	 */
	void saveNewPizza(Pizza pizza);

	/**
	 * m�thode qui permet de modifier les pizzas
	 * 
	 * @param codePizza
	 * @param pizza
	 */
	void updatePizza(String codePizza, Pizza pizza);

	/**
	 * m�thode qui permet de supprimer les pizzas
	 * 
	 * @param codePizza
	 */
	void deletePizza(String codePizza);

	/**
	 * m�thode qui permet de trouver une pizza selon son code
	 * 
	 * @param codePizza
	 * @return
	 */
	Pizza findPizzaByCode(String codePizza);

	/**
	 * m�thode qui permet de definir les pizzas qui existent
	 * 
	 * @param codePizza
	 * @return true if existing
	 */
	boolean pizzaExists(String codePizza);
}
