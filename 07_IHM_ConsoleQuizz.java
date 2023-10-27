import java.util.Scanner;

public class ConsoleQuizz {
	Scanner clavier = new Scanner(System.in);
	
	public int getNbQuestion() {
		System.out.println("veuillez entrer le nombre de questions auxquelles vous voulez répondre : ");
		String userAnswer = clavier.nextLine();
		return Integer.parseInt(userAnswer);
	}
	
	public String afficheQuestionPays(String s) {
		System.out.println("Quelle est la capitale de ce pays: " + s);
		return clavier.nextLine();
	}
	
	public void bonneReponse(boolean b) {
		if(b) {
			System.out.println("Bonne Reponse");
		} else {
			System.out.println("Mauvaise Reponse");
		}
	}
	
	public void affichageReponse(String s) {
		System.out.println("la bonne reponse etait: " + s);
	}
	
	public void erreur() {
		System.out.println("Vous n'avez rien répondu ou une erreur est survenue");
	}
	
	public void afficheTempsQuestion (int temps, int nbQuestion) {
		System.out.printf("Il vous a fallu environ %d secondes pour repondre aux %d questions",
				temps, nbQuestion);
	}
	
	public void afficheScore(int score, int nbQuestion) {
		System.out.printf("votre score final est de : %d/%d\n ", score, nbQuestion);
	}
	
	public void afficheMaxQuestion(int taille) {
		System.out.printf("On ne peut generer que " + taille + " questions maximun\n");
	}
}
