import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

public class Quizz {
	private int score = -1;
	private long timeElapsed = -1;
	private int nbreQuestion = 0;
	GraphiqueQuizz qz = new GraphiqueQuizz();

	/**
	 * Constructeur de la classe ConsoleQuizz
	 * 
	 * @param nbreQuestion : Le nombre de questions à poser au joueur
	 */
	public Quizz(int nbreQuestion) {
		this.nbreQuestion = nbreQuestion;
	}
	
	/**
	 * Constructeur sans arguments de la classe ConsoleQuizz
	 * 
	 * @param nbreQuestion : Le nombre de questions à poser au joueur est demandé à ce dernier
	 */
	public Quizz() {
		nbreQuestion = qz.getNbQuestion();
	}

	/**
	 * Coeur du quizz, pose les questions, vérifie les réponses et compte le score
	 * @throws NoSuchAlgorithmException 
	 */
	public void start() throws NoSuchAlgorithmException {
		try {
			long startTime = System.currentTimeMillis();
			ArrayList<Question> questions = generate(nbreQuestion);
			score = 0;
			for (int i = 0; i < nbreQuestion; i++) {
				
				String userAnswer = qz.afficheQuestionPays(questions.get(i).getPays());

				if (userAnswer.equalsIgnoreCase(questions.get(i).getVille())) {
					score++;
					qz.bonneReponse(true);
				} else {
					qz.bonneReponse(false);
					qz.affichageReponse(questions.get(i).getVille());
				}
			}
			long endTime = System.currentTimeMillis();
			timeElapsed = endTime - startTime;
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * Permet de convertir le temps de milliseconde à seconde
	 * 
	 * @param timeInMilliSeconds : temps en milliseconde
	 * @return : temps en seconde
	 */
	private int getTimeElapsedInSeconds(long timeInMilliSeconds) {
		return (int) (timeInMilliSeconds / 1000);
	}

	/**
	 * Affiche le résultat au joueur, un message d'erreur sinon
	 */
	public void displayResultats() {
		if (score != -1 && (int)timeElapsed != -1) {
			displayScore();
			displayTimeElapsed();
		} else {
			qz.erreur();
		}
	}

	/**
	 * Affiche le temps utilisé par le joueur pour répondre aux questions
	 */
	private void displayTimeElapsed() {
		qz.afficheTempsQuestion(getTimeElapsedInSeconds(timeElapsed), nbreQuestion);
	}

	/**
	 * Affiche le score final
	 */
	private void displayScore() {
		qz.afficheScore(score, nbreQuestion);

	}

	/**
	 * Permet de créer un tableau de x questions-réponses, x étant choisit au lancement du jeu
	 * @param nbreQuestions : nombre de question choisit au lancement du jeu
	 * @return : un tableau de questions
	 * @throws NoSuchAlgorithmException 
	 */
	public ArrayList<Question> generate(int nbreQuestions) throws NoSuchAlgorithmException {
		String[][] data = null;
		LectureInfosBDD li = new LectureInfosBDD();
		ConnectionBDD co = new ConnectionBDD(li.getDbName(), li.getUrl(), li.getUsername(), li.getPassword());
		try {
			data = co.connection();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Question> questions = new ArrayList<Question>();

		if (nbreQuestions > data.length) {
			qz.afficheMaxQuestion(data.length);
			System.exit(0);
		} else {
			for (int i = 0; i < data.length; i++) {
				questions.add(new Question(data[i][0], data[i][1]));
			}
		}
		Collections.shuffle(questions);
		return questions;
	}
}
