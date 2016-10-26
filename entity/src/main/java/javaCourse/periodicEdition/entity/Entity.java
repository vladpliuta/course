package javaCourse.periodicEdition.entity;

/**
 * 
 * общая бизнес-сущность, от которой наследуются все бизнес-сущности системы
 * 
 * @author Vladimir Pliuta
 *
 */
public abstract class Entity {
	protected int id;

	public Entity() {
	}

	public Entity(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
