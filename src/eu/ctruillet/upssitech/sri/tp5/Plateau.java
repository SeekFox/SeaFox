package eu.ctruillet.upssitech.sri.tp5;

import processing.core.PApplet;

import java.util.Arrays;


public class Plateau {
	//Attributs
	private PApplet sketch;
	private int taille;
	private int tailleCase;
	private CasePlateau[][] matrice;

	//Constructeur
	public Plateau(PApplet sketch, int taille) {
		this.sketch = sketch;
		this.taille = taille;
		this.tailleCase = (650 - 30) / taille;
		this.matrice = new CasePlateau[this.taille][this.taille];
		for (int i = 0; i < this.taille; i++) {
			for (int j = 0; j < this.taille; j++) {
				this.matrice[i][j] = new CasePlateau(this.sketch, this.tailleCase,i,j);
			}
		}
	}

	//Méthodes
	public void affichage() {
		for (int i = 0; i < this.taille; i++) {
			for (int j = 0; j < this.taille; j++) {
				System.out.println(this.matrice[i][j].affichage());
			}
			System.out.println("\n");
		}
	}

	public int getTaille() {
		return this.taille;
	}

	public CasePlateau getCaseAt(int i, int j) {
		return this.matrice[i][j];
	}

	public void draw(int x, int y) { //ToDO
		sketch.noFill();
		for (int i = 0; i < this.taille; i++) {
			for (int j = 0; j < this.taille; j++) {
				this.matrice[i][j].draw(x + i * this.tailleCase, y + j * this.tailleCase);

			}
		}
	}

	public void removeAllCross(){
		for (int i = 0; i < this.taille; i++) {
			for (int j = 0; j < this.taille; j++) {
				if(this.getCaseAt(i,j).isCross()) this.getCaseAt(i,j).setCross(false);
			}
		}
	}

	@Override
	public String toString() {
		return "Plateau{" +
				"taille=" + taille +
				", tailleCase=" + tailleCase +
				", matrice=" + Arrays.toString(matrice) +
				'}';
	}

	public void setCrossAtCaseFull(boolean b){
		for (int i = 0; i < this.taille; i++) {
			for (int j = 0; j < this.taille; j++) {
				if(this.getCaseAt(i,j).estPleine()) this.getCaseAt(i,j).setCross(b);
			}
		}
	}

	/**
	 * Affiche (b=true) une croix sur la case si elle ne peut pas acceuillir le navire n
	 * @param type
	 * @param b
	 */
	public void setCrossIfICantAdd(TypeNav type, boolean b){
		for (int i = 0; i < this.taille; i++) {
			for (int j = 0; j < this.taille; j++) {
				if(!this.getCaseAt(i,j).canIAdd(type)) this.getCaseAt(i,j).setCross(b);
			}
		}
	}


	public boolean onClick(int x, int y){
		return x >= 15 && x <= (15 + this.tailleCase * this.taille) && y >= 15 && y <= (15 + this.tailleCase * this.taille);
	}

	public int getCaseXOnClick(int x){
		return (x - 15) / this.getTailleCase();
	}

	public int getCaseYOnClick(int y){
		return (y - 15) / this.getTailleCase();
	}

	public CasePlateau getCaseOnClick(int x,int y){
		return this.getCaseAt(getCaseXOnClick(x),getCaseYOnClick(y));
	}

	public int getTailleCase() {
		return tailleCase;
	}

	public CasePlateau[][] getMatrice() {
		return matrice;
	}

}
