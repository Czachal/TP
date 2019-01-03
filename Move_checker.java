import java.util.ArrayList;

public class Move_checker {
	
	int check_move(int c1,int c2,int playernr ,ArrayList<Cell> siatka) {
		

		if(siatka.get(c1).get_x() == siatka.get(c2).get_x()+1 && siatka.get(c1).get_z() == siatka.get(c2).get_z()-1 && siatka.get(c2).get_pr() == 0 && siatka.get(c1).get_pr() == playernr) {
			return 1;
		} //LG
		
		if(siatka.get(c1).get_x() == siatka.get(c2).get_x()-1 && siatka.get(c1).get_z() == siatka.get(c2).get_z()+1 && siatka.get(c2).get_pr() == 0 && siatka.get(c1).get_pr() == playernr) {
			return 1;
		} //PD
		
		if(siatka.get(c1).get_y() == siatka.get(c2).get_y()+1 && siatka.get(c1).get_z() == siatka.get(c2).get_z()-1 && siatka.get(c2).get_pr() == 0 && siatka.get(c1).get_pr() == playernr) {
			return 1;
		} //L
		
		if(siatka.get(c1).get_y() == siatka.get(c2).get_y()-1 && siatka.get(c1).get_z() == siatka.get(c2).get_z()+1 && siatka.get(c2).get_pr() == 0 && siatka.get(c1).get_pr() == playernr) {
			return 1;
		} //P
		
		if(siatka.get(c1).get_x() == siatka.get(c2).get_x()+1 && siatka.get(c1).get_y() == siatka.get(c2).get_y()-1 && siatka.get(c2).get_pr() == 0 && siatka.get(c1).get_pr() == playernr) {
			return 1;
		} //PG
		
		if(siatka.get(c1).get_x() == siatka.get(c2).get_x()-1 && siatka.get(c1).get_y() == siatka.get(c2).get_y()+1 && siatka.get(c2).get_pr() == 0 && siatka.get(c1).get_pr() == playernr) {
			return 1;
		} //LD
	
		/// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ jump
		
		if(siatka.get(c1).get_x() == siatka.get(c2).get_x()+2 && siatka.get(c1).get_z() == siatka.get(c2).get_z()-2 && siatka.get(c2).get_pr() == 0 && siatka.get(c1).get_pr() == playernr && between(c1,c2,siatka) != 0) {
			return 2;
		} //LG
		
		if(siatka.get(c1).get_x() == siatka.get(c2).get_x()-2 && siatka.get(c1).get_z() == siatka.get(c2).get_z()+2 && siatka.get(c2).get_pr() == 0 && siatka.get(c1).get_pr() == playernr && between(c1,c2,siatka) != 0) {
			return 2;
		} //PD
		
		if(siatka.get(c1).get_y() == siatka.get(c2).get_y()+2 && siatka.get(c1).get_z() == siatka.get(c2).get_z()-2 && siatka.get(c2).get_pr() == 0 && siatka.get(c1).get_pr() == playernr && between(c1,c2,siatka) != 0) {
			return 2;
		} //L
		
		if(siatka.get(c1).get_y() == siatka.get(c2).get_y()-2 && siatka.get(c1).get_z() == siatka.get(c2).get_z()+2 && siatka.get(c2).get_pr() == 0 && siatka.get(c1).get_pr() == playernr && between(c1,c2,siatka) != 0) {
			return 2;
		} //P
		
		if(siatka.get(c1).get_x() == siatka.get(c2).get_x()+2 && siatka.get(c1).get_y() == siatka.get(c2).get_y()-2 && siatka.get(c2).get_pr() == 0 && siatka.get(c1).get_pr() == playernr && between(c1,c2,siatka) != 0) {
			return 2;
		} //PG
		
		if(siatka.get(c1).get_x() == siatka.get(c2).get_x()-2 && siatka.get(c1).get_y() == siatka.get(c2).get_y()+2 && siatka.get(c2).get_pr() == 0 && siatka.get(c1).get_pr() == playernr && between(c1,c2,siatka) != 0) {
			return 2;
		} //LD
		
		return 0;
		
	}
	
	int between(int c1,int c2,ArrayList<Cell> siatka) {
		
		int x = (siatka.get(c1).get_x() +  siatka.get(c2).get_x())/2;
		int y = (siatka.get(c1).get_y() +  siatka.get(c2).get_y())/2;
		int z = (siatka.get(c1).get_z() +  siatka.get(c2).get_z())/2;
		
		for(int i=0;i<siatka.size();i++) {
			if(siatka.get(i).get_x() == x &&  siatka.get(i).get_y() == y  && siatka.get(i).get_z() == z) return siatka.get(i).get_pr();
		}
		
		return 0;
	}
	
	
}
