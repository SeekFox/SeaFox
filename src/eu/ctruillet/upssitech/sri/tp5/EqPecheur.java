package eu.ctruillet.upssitech.sri.tp5;

import processing.core.PApplet;

class EqPecheur extends Equipe{
	public EqPecheur(PApplet sketch, int idEq, Nature n){
		super(sketch, n,idEq);
		this.id=idEq;
	}
}
