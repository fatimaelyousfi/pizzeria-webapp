package dev.pizzeria;



import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;



import fr.pizzeria.model.Pizza;



public class PizzaMemDaoJpa implements PizzaDao{

	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pizzeria");
			
		@Override
		public List<Pizza> findAllPizzas() {
			
			EntityManager em = entityManagerFactory.createEntityManager();
			EntityTransaction et = em.getTransaction();
			et.begin();

			Query query= em.createQuery("select p from Pizza p");
			List<Pizza> p =( List <Pizza>) query.getResultList();
			
			
			et.commit();
			em.close();
			return p;
			
			
		}

		@Override
		public void saveNewPizza(Pizza pizza) {
			
			EntityManager em = entityManagerFactory.createEntityManager();
			EntityTransaction et = em.getTransaction();
			et.begin();
			
			
			em.persist(pizza);
			
			et.commit();
			em.close();
			
		}

		@Override
		public void updatePizza(String codePizza, Pizza pizza) {
			
			EntityManager em = entityManagerFactory.createEntityManager();
			EntityTransaction et = em.getTransaction();
			et.begin();
			TypedQuery<Pizza> query2 = em.createQuery("select p from  Pizza where p.codePizza=:codeModif",Pizza.class);
			query2.setParameter("codeModif", codePizza);
			
			Pizza pizzaModif = query2.getResultList().get(0);
			pizza.setPrix(pizza.getPrix());
			pizza.setCategorie(pizza.getCategorie());
			pizza.setLibelle(pizza.getLibelle());
			pizza.setCode(pizza.getCode());
			em.persist(pizza);
			
			et.commit();
			em.close();
			
		}

		@Override
		public void deletePizza(String codePizza) {
			
			EntityManager em = entityManagerFactory.createEntityManager();
			EntityTransaction et = em.getTransaction();
			et.begin();
			TypedQuery<Pizza> query2 = em.createQuery("select p from  Pizza where p.codePizza=:codeSuppr",Pizza.class);
			query2.setParameter("codeSuppr", codePizza);
			em.remove(query2.getResultList().get(0));
			
			et.commit();
			em.close();
			
		}

		@Override
		public Pizza findPizzaByCode(String codePizza) {
			EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pizzeria");
			EntityManager em = entityManagerFactory.createEntityManager();
			EntityTransaction et = em.getTransaction();
			et.begin();
			
			TypedQuery<Pizza> query2 = em.createQuery("select p from  Pizza where p.codePizza=:codeFind",Pizza.class);
			query2.setParameter("codeFind", codePizza);
			Pizza pizzaFind = query2.getResultList().get(0);
			
			pizzaFind.setCode(pizzaFind.getCode());
			em.persist(pizzaFind);
			
			et.commit();
			em.close();
			return null;
		}

		@Override
		public boolean pizzaExists(String codePizza) {
			boolean piz = false;
			EntityManager em = entityManagerFactory.createEntityManager();
			EntityTransaction et = em.getTransaction();
			et.begin();
			
			TypedQuery<Pizza> query2 = em.createQuery("select p from Pizza p where codePizza=:codeExiste",Pizza.class);
			query2.setParameter("codeExiste", codePizza);
			Pizza pizzaExiste = query2.getResultList().get(0);
			
			et.commit();
			if (pizzaExiste !=null)
				piz= true;
			em.close();
			return piz;
		}
}
