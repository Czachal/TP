import java.awt.List;
import java.util.ArrayList;

public class Plansza {
	
	ArrayList<Cell> lista = new ArrayList();
	
	public Plansza(int size) {		
		
		int x=size;
		int y=-size;
		int z=0;
		
		for(int i=0; i < size*2+1; i++)
		{	
			System.out.printf("\n");
			
			if(i<=size) {
				
				y=-size;
				z= -x-y;
				
				for(int j=0; j<size+i+1;j++) {
					new_cell(x,z,y,0);
					y++;
					z--;
				}
			}
			
			if(i>size) {
				
				z=size;
				y=-z-x;
				
				for(int j=0; j<size*3+1-i;j++) {
					new_cell(x,z,y,0);
					y++;
					z--;
				}
			}	
			x--;		
		}
		
		for(int i=1; i<7; i++) {
			
			System.out.printf("\n");
			
			switch(i) {
			
			case 1:
				x=size*2;
				for(int j=0; j<size; j++){
					y=-size;
					z=-y-x;
					for(int k=0;k<=j;k++){
						new_cell(x,z,y,1);
						y++;
						z--;
					}
					x--;
				}
				break;
				
			case 2:
				y=size*2;
				for(int j=0; j<size; j++)
				{
					z=-size;
					x=-y-z;
					for(int k=0;k<=j;k++){
						new_cell(x,z,y,2);
						z++;
						x--;
					}
					y--;
				}
				break;
				
			case 3:
				z=size*2;
				for(int j=0; j<size; j++)
				{
					x=-size;
					y=-z-x;
					for(int k=0;k<=j;k++){
						new_cell(x,z,y,3);
						x++;
						y--;
					}
					z--;
				}
				break;
				
			case 4:
				z=-size*2;
				for(int j=0; j<size; j++)
				{
					x=size;
					y=-z-x;
					for(int k=0;k<=j;k++){
						new_cell(x,z,y,4);
						x--;
						y++;
					}
					z++;
				}
				break;
				
			case 5:
				y=-size*2;
				for(int j=0; j<size; j++)
				{
					z=size;
					x=-y-z;
					for(int k=0;k<=j;k++){
						new_cell(x,z,y,5);
						z--;
						x++;
					}
					y++;
				}
				break;
				
			case 6:
				x=-size*2;
				for(int j=0; j<size; j++){
					y=size;
					z=-y-x;
					for(int k=0;k<=j;k++){
						new_cell(x,z,y,6);
						y--;
						z++;
					}
					x++;
				}
				break;
				
					
			}
		}
		
	}

	void new_cell(int x,int z,int y,int p) {
		//System.out.printf(x+""+z+""+y+","+p+"::");
		
		lista.add(new Cell(x,z,y,p));
		
	}
	
	ArrayList<Cell> dajliste()
	{
		return lista;
	}
	
	
	
	
	
}
