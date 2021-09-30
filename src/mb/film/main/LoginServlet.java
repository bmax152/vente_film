package mb.film.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import mb.film.dao.Article;
import mb.film.dao.User;
import mb.film.model.UserRepository;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet({ "/LoginServlet", "/login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger("Login");  
	private static final String VIEW_SUFFIX = ".jsp";
	private static final String VIEW_DIR = "views/";
	
    public LoginServlet() {
        super();

    }

  //***********************GET*********************************//
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if(action.equals("logout")) {
			HttpSession session = request.getSession();
			session.setAttribute("user", null);
			//on détruit la liste une fois déco
			session.setAttribute("listCommande", null);
			response.sendRedirect("./main");
		}
	}

	//***********************POST*********************************//
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		String login = request.getParameter("login");
		String password = request.getParameter("psw");
		
		UserRepository repository = new UserRepository();
		User connected = repository.log(login, password);
		
		//Gestion de la Session!
		HttpSession session = request.getSession();
		session.setAttribute("user", connected);
				
		if(connected!=null) {		
			//logger.info(connected);
			//On crée une liste de commande par user et par session
			List<Article> listCommande = new ArrayList<Article>();
			session.setAttribute("listCommande", listCommande);
			response.sendRedirect("./main"); //Redirection vers AUTRE Servlet!
		}else {
			response.sendRedirect("./main");
		}
	}

}
