package dev.pizzeria.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.pizzeria.PizzaDao;
import dev.pizzeria.PizzaMemDaoJpa;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;



public class PizzaController extends HttpServlet {
	private static final Logger LOGGER = LoggerFactory.getLogger(PizzaController.class);

    /**
     * Page HTML de la réponse en cas d'insertion effectuée.
     * Fichier présent dans le répertoire src/main/resources.
     */
    public static final String TEMPLATE_PIZZA_INSERE = "templates/pizza_insere.html";

    private PizzaDao pizzaDao = new PizzaMemDaoJpa();
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    	
        // récupération du paramètre nom
        // <input name="nom">
        
        
    	String code = req.getParameter("code");
    	String libelle = req.getParameter("libelle");
    	double prix= Double.parseDouble(req.getParameter("prix"));
    	String categoriePizza = req.getParameter("categorie");
    	CategoriePizza categorie = CategoriePizza.valueOf(categoriePizza);
        
        
    
        LOGGER.info("Paramètre nom reçu " + code);
        LOGGER.info("Paramètre nom reçu " + libelle);
        LOGGER.info("Paramètre nom reçu " + prix);
        LOGGER.info("Paramètre nom reçu " + categorie);

        Pizza p = new Pizza(code, libelle, prix, categorie);
        pizzaDao.saveNewPizza(p);
        // TODO insérer un nouveau client en base de données


        try {
            // réponse au format UTF-8 pour le support des accents
            resp.setCharacterEncoding("UTF-8");

            // récupération du contenu du fichier template
            String template = Files.readAllLines(Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_PIZZA_INSERE).toURI())).stream().collect(Collectors.joining());

            // écriture dans le corps de la réponse
            PrintWriter writer = resp.getWriter();
            writer.write(template);

        } catch (URISyntaxException e) {
           LOGGER.error("Fichier HTML non trouvé", e);
        }
    }
}
