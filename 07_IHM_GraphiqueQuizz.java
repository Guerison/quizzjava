import javax.swing.JOptionPane;

public class GraphiqueQuizz {
	
	public int getNbQuestion() {
		return Integer.parseInt(JOptionPane.showInputDialog(null, "veuillez entrer le nombre de questions auxquelles vous voulez répondre : "));
	}
	
	public String afficheQuestionPays(String s) {
		return JOptionPane.showInputDialog(null, "Quelle est la capitale de ce pays: " + s);
	}
	
	public void bonneReponse(boolean b) {
		if(b) {
			JOptionPane.showMessageDialog(null, "Bonne Reponse");
		} else {
			JOptionPane.showMessageDialog(null, "Mauvaise Reponse");
		}
	}
	
	public void affichageReponse(String s) {
		JOptionPane.showMessageDialog(null, "la bonne reponse etait: " + s);
	}
	
	public void erreur() {
		JOptionPane.showMessageDialog(null, "Vous n'avez rien répondu ou une erreur est survenue");
	}
	
	public void afficheTempsQuestion (int temps, int nbQuestion) {
		JOptionPane.showMessageDialog(null, "Il vous a fallu environ " + temps + " secondes pour repondre aux " + nbQuestion + " questions");
	}
	
	public void afficheScore(int score, int nbQuestion) {
		JOptionPane.showMessageDialog(null, "votre score final est de : " + score + "/" + nbQuestion);
	}
	
	public void afficheMaxQuestion(int taille) {
		JOptionPane.showMessageDialog(null, "On ne peut generer que " + taille + " questions maximun\n");
	}
}
