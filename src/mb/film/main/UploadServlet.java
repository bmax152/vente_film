package mb.film.main;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import mb.film.dao.Acteur;
import mb.film.dao.Film;
import mb.film.dao.Filmacteur;
import mb.film.dao.Filmphoto;
import mb.film.dao.Filmrealisateur;
import mb.film.dao.Photo;
import mb.film.dao.Realisateur;
import mb.film.model.ActeurRepository;
import mb.film.model.FilmRepository;
import mb.film.model.FilmacteurRepository;
import mb.film.model.FilmphotoRepository;
import mb.film.model.FilmrealisateurRepository;
import mb.film.model.PhotoRepository;
import mb.film.model.RealisateurRepository;
/**
 * Servlet implementation class UploadServlet
 */
@WebServlet({ "/UploadServlet", "/upload" })
@MultipartConfig( 	fileSizeThreshold = 1024 * 1024, 
maxFileSize = 1024 * 1024 * 5,
maxRequestSize = 1024 * 1024 * 5 * 5 )

public class UploadServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger("Upload Servlet");
	public static final String IMAGES_FOLDER = "/imageFilm";    
	public String uploadPath;
       
	@Override
	public void init() throws ServletException {
		logger.info("init..");

		uploadPath = getServletContext().getRealPath( IMAGES_FOLDER );
		logger.info("init..uploadPath = " + uploadPath);

		File uploadDir = new File( uploadPath );
		logger.info("init..uploadDir = " + uploadDir.getAbsolutePath());

		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}
	}
	
	
	private String getFileName( Part part ) {
		String fileName="";
		String param_value;
		
		String[] tokens = part.getHeader("content-disposition" ).split( ";" ) ;

		logger.info("========================================");
		logger.info("tokens  : "+part.getHeader("content-disposition" ));
		for (String token : tokens) {
			logger.info("token  : "+token);

			if (token.trim().startsWith("filename")) {
				fileName = token.substring(token.indexOf("=") + 2, token.length()-1);
				
				File uploadedFile = new File(fileName);
				fileName = uploadedFile.getName();
				
			}

		}	
		return fileName;
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/**
		 * Upload file
		 */
		logger.info("**********************************************");
		String fileName="";
		String nomImage="";
		try {
			for ( Part part : request.getParts() ) {

				fileName = getFileName( part );		
				if(!"".equals(fileName)) {
					String fullPath = uploadPath + File.separator + fileName;
					nomImage = fileName;
					part.write( fullPath );

					logger.info("========================================");
					logger.info("file  '"+fullPath+"' uploaded with success!");
					logger.info("========================================");
				}
			}
			//Partie de traitement après l'image:
			
			String nom = request.getParameter("nomFilm");
			logger.info("1");
			String desc = request.getParameter("descFilm");
			logger.info("2");
			double prix = (Double.parseDouble(request.getParameter("prixFilm").replace(",", ".")));
			logger.info("3");
			String resumer = request.getParameter("resumerFilm");
			logger.info("4");
			int quantiter = Integer.parseInt(request.getParameter("quantiterFilm"));
			logger.info(nom +" / "+ desc +" / "+ prix +" / "+ resumer +" / "+ quantiter);
			//Création du film
			FilmRepository repoFilm = new FilmRepository();
			repoFilm.add(new Film(0, desc, nom, new BigDecimal(prix), quantiter, resumer));
			Film film = repoFilm.findByName(nom);
			
			//Création de l'association image/film
			PhotoRepository repoPhoto = new PhotoRepository();
			repoPhoto.add(new Photo(0, nomImage));
			Photo imageFilm = repoPhoto.findByNamePhoto(nomImage);
			logger.info(imageFilm.getLabel());
			FilmphotoRepository repoFilmP = new FilmphotoRepository();
			repoFilmP.add(new Filmphoto(0, imageFilm, film));
			
			//Création de l'association acteur/film
			String[] tabAct = request.getParameterValues("filmAct");
			ActeurRepository repoAct = new ActeurRepository();
			List<Acteur> listActeur = new ArrayList<Acteur>();	
			for (int i = 1; i <= tabAct.length; i++) {
				Acteur acteur = repoAct.find(i);
				listActeur.add(acteur);
			}
			FilmacteurRepository repoFilmA = new FilmacteurRepository();
			for (Acteur acteur : listActeur) {
				repoFilmA.add(new Filmacteur(0, acteur, film));
			}
			
			//Création de l'association realisateur/film
			String[] tabReal = request.getParameterValues("filmReal");
			RealisateurRepository repoReal = new RealisateurRepository();
			List<Realisateur> listRealisateur = new ArrayList<Realisateur>();
			for (int i = 1; i <= tabReal.length; i++) {
				Realisateur realisateur = repoReal.find(i);
				listRealisateur.add(realisateur);
			}
			FilmrealisateurRepository repoFilmR = new FilmrealisateurRepository();
			for (Realisateur realisateur : listRealisateur) {
				repoFilmR.add(new Filmrealisateur(0, realisateur, film));
			}
			
			
			//request.getSession().setAttribute("img", fileName);
			response.sendRedirect("./main?page=admin");

		} catch (Exception e) {
			logger.info("Upload file ==> Error : "+e);				
			e.printStackTrace();
			response.sendRedirect("./main?page=admin");
		}
		logger.info("**********************************************");
	}

}
