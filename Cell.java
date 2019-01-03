
public class Cell {

	double x;
	double y;
	double z;
	
	int past;
	int present;
	
	Cell(int x,int z,int y,int p) {
		this.x = x;
		this.y = y;
		this.z = z;
		past = p;
		present = p;
	}
	
	int get_x() {
		return (int) x;
	}
	
	int get_y() {
		return (int) y;
	}
	
	int get_z() {
		return (int) z;
	}
	
	int get_pa() {
		return past;
	}
	
	int get_pr() {
		return present;
	}
	
	void set_pr(int p) {
		present = p;
	}
	
	double d2_x() {
		return 30*(y+x/2);
	}
	
	double d2_y() {
		return 15*x*Math.sqrt(3);
	}
	
	int between(int x,int z,int y) {
		return present;
	}
	
	
}
