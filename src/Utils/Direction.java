package Utils;

// Represents a 2D direction, which can be either left, right, up, or down
// each direction is given a property for velocity, which is based on screen coordinates (e.g. going downwards adds 1 to Y, so DOWN has a velocity of 1)
public enum Direction {
	LEFT(-1), RIGHT(1), UP(-1), DOWN(1), NONE(0);

	private int velocity;

	Direction(int velocity) {
		this.velocity = velocity;
	}

	public int getVelocity() {
		return velocity;
	}

	public Direction random(){
		int rand = (int)(Math.random()*4);
		if (rand == velocity){
			return random();
		}
		else{
			switch (rand) {
				case 0:
					return UP;
				case 1:
					return DOWN;
				case 2:
					return LEFT;
				case 3:
					return RIGHT;
				default:
					return NONE;
			}
		}
	}

	@Override
	public String toString() {
		switch (this) {
			case LEFT:
				return "LEFT";
			case RIGHT:
				return "RIGHT";
			case DOWN:
				return "DOWN";
			case UP:
				return "UP";
			default:
				return "NONE";
		}
	}
}
