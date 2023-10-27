import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionBDD {

	private Connection connection;
	private String dbName;
	private String url;
	private String username;
	private String password;

	/**
	 * Constructeur sans arguments
	 */
	public ConnectionBDD() {
		connection = null;
		dbName = "quizz";
		url = "jdbc:mysql://localhost:3306/" + dbName;
		username = "root";
		password = "SIO2022";
	}

	/**
	 * Constructeur avec arguments
	 * 
	 * @param connection : objet de connection
	 * @param dbName : nom de la base de données
	 * @param url : lien ou est stocké la base de données
	 * @param username : nom de l'utilisateur pour se connecter à la base de données
	 * @param password : mot de passe pour se connecter à la base de données
	 */
	public ConnectionBDD(String dbName, String url, String username, String password) {
		this.connection = null;
		this.dbName = dbName;
		this.url = url + dbName;
		this.username = username;
		this.password = password;
	}

	/**
	 * Permet de vérifier que la connection à la base fonctionne
	 * 
	 * @return un tableau de string à deux dimensions contenant les pays et les
	 *         capitales contenues en BDD
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@SuppressWarnings("deprecation")
	public String[][] connection()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		ResultSet rs = null;
		PreparedStatement ps = null;
		String[][] data = null;
		int i = 0;
		int nbLignes = 0;

		Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		connection = DriverManager.getConnection(url, username, password);
		// Permet de récupérer le nombre de ligne dans ma BDD pour initialiser mon
		// tableau à la bonne taille
		ps = connection.prepareStatement("SELECT COUNT(*) AS nbLignes FROM quizz.questions");
		rs = ps.executeQuery();
		rs.next();
		nbLignes = rs.getInt("nbLignes");
		data = new String[nbLignes][2];
		// récupère l'ensemble des informations de ma table questions
		ps = connection.prepareStatement("SELECT * FROM quizz.questions;");
		rs = ps.executeQuery();
		while (rs.next()) {
			// System.out.println("Pays : " + rs.getString("pays") + " Capitale : " + rs.getString("capitales"));
			data[i][0] = rs.getString("pays");
			data[i][1] = rs.getString("capitales");
			i++;
		}
		return data;
	}

	/**
	 * Permet d'ajouter un jeu de test à la bdd pour l'exemple du quizz
	 * 
	 * @throws SQLException
	 */
	public void ajouterData() throws SQLException {
		PreparedStatement ps = null;
		String[][] data = { { "Senegal", "Dakar" }, { "France", "Paris" }, { "Australie", "Canberra" },
				{ "Argentine", "Buenos Aires" }, { "Brasil", "Brasilia" }, { "Italie", "Rome" }, { "Perou", "Lima" },
				{ "Allemagne", "Berlin" }, { "Canada", "Ottawa" } };

		for (int i = 0; i < data.length; i++) {
			ps = connection.prepareStatement("INSERT INTO `quizz`.`questions` (`pays`, `capitales`) VALUES ('"
					+ data[i][0] + "', '" + data[i][1] + "');\r\n" + "");
			ps.execute();
		}
	}

}
