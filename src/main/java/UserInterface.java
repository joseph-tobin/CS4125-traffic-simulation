public class UserInterface{
	// needs a rethink? depedency?
	UIController uic;
	Simulation sim;

	public void draw(){

	}

	public void save(){

	}

	public void load(){

	}

	public void run(){
		sim.run();
	}

	public void pause(){
		sim.pause();
	}

	public void reset(){
		sim.reset();
	}

	public void displayMetrics(){
		uic.getMetrics();
	}
}