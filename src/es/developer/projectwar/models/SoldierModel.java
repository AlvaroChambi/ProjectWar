package es.developer.projectwar.models;


public class SoldierModel extends UnitModel{

	public SoldierModel(int id) {
		super(id);
		this.setResource("player.png");
		this.setMovement(4);
		this.setAttackRange(1);
	}
}
