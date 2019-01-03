import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Manager extends JPanel implements MouseListener, KeyListener {
	
	public static final int HMAX = 700;
	public static final int WMAX = 700;
	
	ArrayList<Hexagon> pola = new ArrayList<>();
	ArrayList<Cell> siatka = new ArrayList<>();

	Move_checker moveh;
	
	Plansza pg;
	
	int playernr=1;
	
	int Px1 = 0;
	int Px2 = 0;
	int Py1 = 0;
	int	Py2	= 0;
	
	int hoplock=0;
	
	JButton button;
	
	int point1=0;
	int point2=0;
	
	Boolean turn=false;
	
	Manager(int size){
		
		moveh = new Move_checker();
		
		set_window();
		
		pg = new Plansza(size);
		
		siatka = pg.dajliste();
		
		
		for(int i=0;i<siatka.size(); i++)
		{
			pola.add(new Hexagon(350+siatka.get(i).d2_x(),350+siatka.get(i).d2_y()));
		}
			
		repaint();	
			
	//	make_move(in_cell(470,298),in_cell(380,401));
		
		make_move(1,77);
		make_move(8,78);
		
		/*
		move_tester(68,1);
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {}
		move_tester(1,7);
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {}
		move_tester(7,9);
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {}
		move_tester(8,3);
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {}
		move_tester(3,2);
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {}
		move_tester(2,68);
		*/
		
		//player_turn();
				
	}
	
	protected void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		for(int i=0; i<pola.size();i++) {
			
			switch (siatka.get(i).get_pa()) {
				case 0: g2d.setColor(new Color(200,200,200)); break;
				case 1: g2d.setColor(new Color(255,200,200)); break;
				case 2: g2d.setColor(new Color(200,200,255)); break;
				case 3: g2d.setColor(new Color(200,255,200)); break;
				case 4: g2d.setColor(new Color(200,255,200)); break;
				case 5: g2d.setColor(new Color(200,200,255)); break;
				case 6: g2d.setColor(new Color(255,200,200)); break;
			}
			
			g2d.fillOval(pola.get(i).getx()-15,pola.get(i).gety()-15, 31, 31);	
		}
		
		if(in_cell(Px1,Py1) != -1){
			g2d.setColor(new Color(255,255,255));
			g2d.fillOval(pola.get(in_cell(Px1,Py1)).getx()-13,pola.get(in_cell(Px1,Py1)).gety()-13, 26, 26);
		}
		
		for(int i=0; i<pola.size();i++) {
			
			switch (siatka.get(i).get_pr()) {
				case 1: g2d.setColor(new Color(255,135,135)); break;
				case 2: g2d.setColor(new Color(135,135,255)); break;
				case 3: g2d.setColor(new Color(135,255,135)); break;
				case 4: g2d.setColor(new Color(0,135,0)); break;
				case 5: g2d.setColor(new Color(0,0,135)); break;
				case 6: g2d.setColor(new Color(135,0,0)); break;
			}
			
			if(siatka.get(i).get_pr()!=0)
			g2d.fillOval(pola.get(i).getx()-10,pola.get(i).gety()-10, 20, 20);	
		}
		
		
		
	}
	
 	public void set_window() {
		JFrame f = new JFrame("Gra");
		f.setPreferredSize(new Dimension(HMAX,WMAX ));
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(HMAX, WMAX);
		f.setVisible(true);
		setBackground(Color.BLACK);
		f.add(this);
		f.addMouseListener(this);	
		f.addKeyListener(this);
	}
	
	int in_cell(int x,int y) {
		
		for(int i=0;i<pola.size();i++) {
			
			if(Math.sqrt( Math.pow(x-pola.get(i).getx(),2) + Math.pow(y-pola.get(i).gety(),2)  ) < 15  ) return i;

		}
		
		return -1;
	}

	void make_move(int cell1,int cell2) {
		
		if(point1 == 0) point1=cell1;
		point2=cell2;
		
		int temp;
		
		temp = siatka.get(cell1).get_pr();
		siatka.get(cell1).set_pr(siatka.get(cell2).get_pr());
		siatka.get(cell2).set_pr(temp);
		
		repaint();
		
		has_won();
		
	}
		
	void move_tester(int c1,int c2) {
		
		
		switch (moveh.check_move(c1, c2, playernr, siatka)) {
		
			case 0:
				if(hoplock == 0){
					Px1 = 0;
					Px2 = 0;
					Py1 = 0;
					Py2	= 0;
					System.out.printf("move0\n");
					repaint();
				}
				break;
				
			case 1:
				if(hoplock == 0){
					System.out.printf("move1\n");
					make_move(c1,c2);
					hoplock = -1;
					Px1 = 0;
					Px2 = 0;
					Py1 = 0;
					Py2	= 0;
				}
				break;
				
			case 2:
				if(hoplock >=0 ) {
					System.out.printf("move2\n");
					make_move(c1,c2);
					hoplock = 1;
					Px1=Px2;
					Py1=Py2;
					Px2=0;
					Py2=0;
				}
				break;
				
				
		}		
	}
	
	boolean has_won() {
		
		int player[] = new int[7];
		
		for(int i=0; i<7; i++) player[i]=0;

		
		for(int i=0; i<siatka.size(); i++) {
			
			if(siatka.get(i).get_pr() + siatka.get(i).get_pa() == 7) player[siatka.get(i).get_pr()] ++;
				
		}
		
		for(int i=0; i<7; i++) if( player[i] == 10) {
			System.out.printf("Palyer " + i + " won\n");
			return true;
		}
			
		return false;
	}

	void endturn() {
		turn = false;
		Px1 = 0;
		Px2 = 0;
		Py1 = 0;
		Py2	= 0;
		hoplock=0;
		beginturn();
	}
	
	void beginturn() {
		turn = true;
	}
	
	int getPoint(int i) {
		
		if(i==1) return point1;
		if(i==2) return point2;
		
		return 0;
	}
	
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
				
		if(turn==false) return;
		
		if(Px1 == 0 && Py1 ==0) {
			System.out.printf("m1\n");
			Px1 = e.getX()-8;
			Py1 = e.getY()-30;
			
			repaint();
			
		}
		else if(Px1 != 0 && Py1 !=0) {
			System.out.printf("m2\n");
			Px2 = e.getX()-8;
			Py2 = e.getY()-30;
			
			if(in_cell(Px1,Py1) != -1 && in_cell(Px2,Py2) != -1) move_tester(in_cell(Px1,Py1),in_cell(Px2,Py2));
			else {
				if(hoplock == 0) {
					Px1 = 0;
					Px2 = 0;
					Py1 = 0;
					Py2	= 0;
				}
			}
		}
	}
	
	public void keyPressed(KeyEvent e) {
       if(e.getKeyCode() == 10 ) endturn();
    }

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}

	public static void main(String[] args) throws Exception {
		Socket s = new Socket("127.0.0.1",7171);
	
	}
}
