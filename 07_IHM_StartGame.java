import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class StartGame {
/**
 * La fonction main est le point d'entr�e du programme, elle est unique, lanc�e au d�but et obligatoire
 * @param args : arguments pass�s lors de l'appel depuis une invite de commandes
 * @throws NoSuchAlgorithmException 
 * @throws IOException 
 */
	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
		
		Quizz quiz = new Quizz();
		quiz.start();
		quiz.displayResultats();
	}
}
