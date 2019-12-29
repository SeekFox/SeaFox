package eu.ctruillet.upssitech.sri.tp5;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

public class CasePlateau {
	//Attributs
	private ArrayList<Navire> occupants = new ArrayList<>();
	private int tailleCase;
	private PApplet sketch;
	private int x;
	private int y;
	private boolean isCross;

	//Constructeur
	public CasePlateau(PApplet sketch, int tailleCase, int x, int y) {
		this.tailleCase = tailleCase;
		this.sketch = sketch;
		this.x=x;
		this.y=y;
		this.isCross = false;
		//ToDo
	}

	//Méthodes
	private ArrayList<Navire> getOccupants() {
		return this.occupants;
	}

	public void addOccupant(Navire occupant) {
		if(canIAdd(occupant.getType())){
			this.occupants.add(occupant);
		}
	}

	public boolean estOccupee() {
		return !this.occupants.isEmpty();
	}

	public boolean estVide() {
		return this.occupants.isEmpty();
	}

	@Override
	public String toString() {
		return "CasePlateau{" +
				"occupants=" + occupants +
				", tailleCase=" + tailleCase +
				", sketch=" + sketch +
				", x=" + x +
				", y=" + y +
				", isCross=" + isCross +
				'}';
	}

	public boolean canIAdd(TypeNav type){
		for(Navire n: this.occupants){
			if(n.getType() == TypeNav.SOUSMARIN){
				if(type==TypeNav.SOUSMARIN) return false;
			}else{
				if(type==TypeNav.DESTROYER || type==TypeNav.CHALUTIER) return false;
			}
		}
		return true;
	}

	public boolean estPleine() {
		return this.occupants.size() == 2;
	}

	public String affichage() {
		StringBuilder s = new StringBuilder("[");
		for (Navire e : this.occupants) {
			s.append(e.toString()).append(";");
		}
		return s + "]";
	}

	public void removeOccupant(Navire n) {
		this.occupants.remove(n);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isCross() {
		return isCross;
	}

	public void setCross(boolean cross) {
		isCross = cross;
	}

	public void draw(int x, int y) {
		sketch.stroke(181, 128, 87);
		sketch.square(x, y, this.tailleCase);
		for (Navire n : this.getOccupants()) {
			PImage img = sketch.loadImage("../doc/default.png");
			if (n.getType() == TypeNav.SOUSMARIN) {
				img = sketch.loadImage("../doc/sousmarin.png");
			}
			if (n.getType() == TypeNav.DESTROYER) {
				img = sketch.loadImage("../doc/destroyer.png");
			}
			if (n.getType() == TypeNav.CHALUTIER) {
				img = sketch.loadImage("../doc/chalutier.png");
			}

			//Teinte de couleur suivant l'équipe
			/*
			 * ROUGE : 0
			 * VERT  : 1
			 * BLEU  : 2
			 * JAUNE : 3
			 */
			sketch.tint((n.getNumEq() == 0 ? 255 : (n.getNumEq() == 3 ? 255 : 0)), (n.getNumEq() == 1 ? 255 : (n.getNumEq() == 3 ? 255 : (n.getNumEq() == 2 ? 100 : 0))), (n.getNumEq() == 2 ? 225 : 0));
			sketch.image(img, x, y);
			sketch.noTint();
		}

		if(this.isCross){
			PImage cross = sketch.loadImage("../doc/cross.png");
			sketch.image(cross, this.x*this.tailleCase+15, this.y*this.tailleCase+15);
		}
	}


}
