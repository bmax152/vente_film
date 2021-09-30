package mb.film.main;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import mb.film.dao.Acteur;
import mb.film.dao.Article;
import mb.film.dao.Commande;
import mb.film.dao.Film;
import mb.film.dao.Filmacteur;
import mb.film.dao.Filmrealisateur;
import mb.film.dao.Realisateur;
import mb.film.dao.Role;
import mb.film.dao.RoleDao;
import mb.film.dao.User;
import mb.film.model.ActeurRepository;
import mb.film.model.ArticleRepository;
import mb.film.model.CommandeRepository;
import mb.film.model.FilmRepository;
import mb.film.model.RealisateurRepository;
import mb.film.model.UserRepository;


/**
 * Servlet implementation class MainServlet
 */
@WebServlet({ "/MainServlet", "/main" })
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger("Main Servlet");  //crée un log
	private static final String VIEW_SUFFIX = ".jsp";
	private static final String VIEW_DIR = "views/";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        super();
    }
    //***********************GET*********************************//
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String page = request.getParameter("page");
		if(page == null) {
			page = "index";
		}

		String viewName = VIEW_DIR + page.toLowerCase()+ VIEW_SUFFIX; 
		
		if(viewExists(viewName)) {
			
    		getViewModel(request, page.toLowerCase()); 
    		display(request, response, viewName);    	   
        }else if (page.equals("index")) {
        	
            display(request, response,page + VIEW_SUFFIX);
        } else {
        	
            display(request, response, VIEW_DIR + "404" + VIEW_SUFFIX);
        } 
	}

	
	//***********************POST*********************************//
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		if(action.equalsIgnoreCase("add")) {

			int idTxt = Integer.parseInt(request.getParameter("id"));
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			FilmRepository repo = new FilmRepository();
			Film item = repo.find(idTxt);
			BigDecimal prix = new BigDecimal(request.getParameter("prix"));
			List<Article> listCommande = (List<Article>) session.getAttribute("listCommande");
			Commande commande = new Commande();
			listCommande.add(new Article(0, prix, quantity, commande, item));
			response.sendRedirect("./main?page=panier");
			
		}else if(action.equalsIgnoreCase("commande")) {
			User user = (User) session.getAttribute("user");
			List<Article> listCommande = (List<Article>) session.getAttribute("listCommande");
			//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date(System.currentTimeMillis());
			//System.out.println(sdf.format(date));
			Commande commande = new Commande(date, listCommande, user);
			CommandeRepository repo = new CommandeRepository();
			repo.add(commande);
			ArticleRepository repoArt = new ArticleRepository();
			FilmRepository repoFilm = new FilmRepository();
			for (Article article : listCommande) {
				article.setCommande(commande);
				Film film = article.getFilm();
				film.setQuantite(film.getQuantite() - article.getQuantite());
				repoFilm.update(film);
				repoArt.add(article);
			}
			
			//on vide la list
			listCommande.clear();
			session.setAttribute("listCommande", listCommande);
			response.sendRedirect("./main?page=panier");
			
		}else if(action.equalsIgnoreCase("search")) {
			String search = request.getParameter("search");
			
			if(search.equalsIgnoreCase("")) {
				response.sendRedirect("./main");
			}else {
				ActeurRepository repoActeur = new ActeurRepository();
				RealisateurRepository repoRealisateur = new RealisateurRepository();
				FilmRepository repoFilm = new FilmRepository();
				
				List<Film> listFilm = repoFilm.getSearch(search);
				List<Acteur> listActeur = repoActeur.getSearch(search);
				List<Realisateur> listRealisateur = repoRealisateur.getSearch(search);
				
				session.setAttribute("listFilm", listFilm);
				session.setAttribute("listActeur", listActeur);
				session.setAttribute("listRealisateur", listRealisateur);
				session.setAttribute("word", search);
				response.sendRedirect("./main?page=films&action=search");
			}
			
//		}else if(action.equalsIgnoreCase("ajouterfilm")) {
//			
//			///////////////////////////////////////////////////////////////////////////////////////
//			
//			response.sendRedirect("./main?page=admin");
		}else if(action.equalsIgnoreCase("ajouteracteur")) {
			ActeurRepository repo = new ActeurRepository();
			String nom = request.getParameter("nomActeur");
			String description = request.getParameter("descActeur");
			List<Filmacteur> list = new ArrayList<Filmacteur>();

				try {
					repo.add(new Acteur(0, description, nom, list));
					
				} catch (Exception e) {
					logger.info("Erreur");
				}
			
			response.sendRedirect("./main?page=admin");
		}else if(action.equalsIgnoreCase("ajouterreal")) {
			RealisateurRepository repo = new RealisateurRepository();
			String nom = request.getParameter("nomReal");
			String description = request.getParameter("descReal");
			List<Filmrealisateur> list = new ArrayList<Filmrealisateur>();
			
			try {
				repo.add(new Realisateur(0, nom, description, list));
			} catch (Exception e) {
				logger.info("Erreur");
			}
			
			response.sendRedirect("./main?page=admin");
			
		}else if(action.equalsIgnoreCase("inscription")) {
			String nom = request.getParameter("nom");
			String mail = request.getParameter("email");
			String password = request.getParameter("password");
			
			try {
				UserRepository repoUser = new UserRepository();
				RoleDao dao = new RoleDao();
				Role parDefault = dao.find(2);
				repoUser.add(new User(0, mail, nom, password, parDefault));
			} catch (Exception e) {
				logger.info("Erreur dans la création");
			}
			response.sendRedirect("./main");
		}
	}
	


	//***********************Test si une page existe*********************************//
	private boolean viewExists(String view_name) {
		String viewFullpath = getServletContext().getRealPath(view_name);    
		try {            
			File viewFile = new File(viewFullpath);
			return viewFile.exists();         
		} catch (Exception e) {}
		return false;
	}
	
	
	//***********************Assosie les données à la page*********************************//
	@SuppressWarnings("unused")
	private void getViewModel(HttpServletRequest request, String page) {

		
		FilmRepository repo = new FilmRepository();
		List<Film> allFilms = repo.list();

		if(page == null) {
			return;
		}else if (page.equalsIgnoreCase("films")) {	

			String action = request.getParameter("action");	
			
			if(action == null) {
				request.setAttribute("allFilms", allFilms);
			}else if(action.equalsIgnoreCase("detail")) {
				try {
					request.setAttribute("action", action);
					int idTxt = Integer.parseInt(request.getParameter("id"));
					Film item = repo.find(idTxt);
					List<Filmacteur> listActeur = item.getFilmacteurs();
					List<Filmrealisateur> listRealisateur = item.getFilmrealisateurs();
				
					if(item != null) {
						request.setAttribute("item", item);
						request.setAttribute("listActeur", listActeur);
						request.setAttribute("listRealisateur", listRealisateur);
					}else {
						request.setAttribute("allFilms", allFilms);
						request.setAttribute("action", null);
					}
				} catch (Exception e) {
					request.setAttribute("allFilms", allFilms);
					request.setAttribute("action", null);
				}
			}else {
				request.setAttribute("action", action);
				return;
			}
		}else if(page.equalsIgnoreCase("compte")) {
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			CommandeRepository repoCom = new CommandeRepository();
			List<Commande> commandes = repoCom.getListByUser(user);
			session.setAttribute("commandes", null);
			session.setAttribute("commandes", commandes);
		
		}else if(page.equalsIgnoreCase("admin")) {
			HttpSession session = request.getSession();
			ActeurRepository repoAct = new ActeurRepository();
			RealisateurRepository repoReal = new RealisateurRepository();
			List<Acteur> listAct = repoAct.list();
			List<Realisateur> listReal = repoReal.list();
			session.setAttribute("listAct", listAct);
			session.setAttribute("listReal", listReal);
		}else if(page.equalsIgnoreCase("inscription")) {
//			String nom = request.getParameter("nom");
//			String mail = request.getParameter("email");
//			String password = request.getParameter("password");
//			
//			try {
//				UserRepository repoUser = new UserRepository();
//				RoleDao dao = new RoleDao();
//				Role parDefault = dao.find(2);
//				repoUser.add(new User(0, mail, nom, password, parDefault));
//			} catch (Exception e) {
//				logger.info("Erreur dans la création");
//			}
		}
	
	}
	
	
	
	
	
	//***********************Affiche la page en question*********************************//
	private void display(HttpServletRequest request, HttpServletResponse response,String string) {

		try {
			RequestDispatcher dispatcher = request.getRequestDispatcher(string); // ..definition page
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {	
			logger.error("Erreur: "+ e);
		} 
	}
}
