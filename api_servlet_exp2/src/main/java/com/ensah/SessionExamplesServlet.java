package com.ensah;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


/**
 * Cette classe servlet présente quelques exemples d'utilisation de la session
 * HTTP dans l'API Servlet
 * 
 * @author Tarik BOUDAA
 *
 */

@WebServlet("/")
public class SessionExamplesServlet extends HttpServlet {

	public SessionExamplesServlet() {

	}

	/**
	 * Cette méthode stocke un objet dans la session
	 * 
	 * @param rq     : l'objet représentant la requete
	 * @param key    : une chaîne de caractère qui represente la clé utilisée pour
	 *               référencer l'objet dans la session
	 * @param object : l'objet à stocker dans la session
	 */
	private void storeObjectInSession(HttpServletRequest rq, String key, Object object) {

		// On récupère la session
		HttpSession session = rq.getSession();

		// On stocke l'objet dans la session de l'utilisateur
		session.setAttribute(key, object);

	}

	/**
	 * Méthode qui permet de récupérer un objet de la session
	 * 
	 * @param rq     : l'objet représentant la requete
	 * @param key    : une chaîne de caractères qui represente la clé utilisée pour
	 *               récupérer l'objet dans la session
	 * @param object : l'objet à récupérer de la session
	 */
	private Object getObjectFromSession(HttpServletRequest rq, String key) {

		// On récupère la session
		HttpSession session = rq.getSession();

		// On récupère l'objet de la session en se basant sur la clé
		return session.getAttribute(key);

	}

	/**
	 * Permet d'écrire un paragraphe dans la réponse
	 * 
	 * @param response : objet de réponse à envoyer à l'utilisateur
	 * @throws IOException
	 */
	private void writeParagraph(HttpServletResponse response, String p) throws IOException {

		PrintWriter out = response.getWriter();
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		out.print("<p>" + p + "</p>");
	}

	/**
	 * Méthode qui s'execute lorsque la sevlet recoit une requete de type GET
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// On récupère un paramètre de la reqûete qui indique la tâche demandée,
		// ce paramètre attendu est : task
		String param = request.getParameter("task");

		// Suite à la valeur de ce paramère on execute l'une des tâches ci-dessous :

		// Tâche 1 : On stocke un objet étudiant dans la session
		if ("store".equals(param)) {
			//Création d'un objet Etudiant
			Etudiant et = new Etudiant("Karimi", "Ali", 22);
			//le stocker dans la session
			storeObjectInSession(request, "etudiant", et);

			// On insère du texte dans la réponse
			writeParagraph(response, "L'étudiant est bien ajouté dans la session");

		}
		// Tache 2 : On récupère un objet étudiant de la session
		else if ("get".equals(param)) {

			// On récupère un étudiant de la session
			// L'objet récupéré de la session est généralement de type Object,
			// on s'assure qu'il s'agit exactement d'un objet de type Etudiant
			// par instanceof avant de forcer la conversion
			Etudiant etudiant = null;
			Object obj = getObjectFromSession(request, "etudiant");
			if (obj instanceof Etudiant) {
				etudiant = (Etudiant) obj;
			}

			// si un étudiant est trouvé
			if (etudiant != null) {
				// On insère du texte dans la réponse
				// on laisse la méthode toString de Etudiant prendera soin de
				// convertir l'objet une chaine de caractères
				writeParagraph(response, "L'étudiant est trouvé : " + etudiant);
			}

			else {
				// On écrit un message indiquant que l'objet recherché n'existe
				// pas dans la session
				writeParagraph(response, "Aucun étudiant trouvé");

			}

		}
		else {
			writeParagraph(response, "il faut appeler cette servlet avec un paramètre task qui prend soit store ou get comme valeur");

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
