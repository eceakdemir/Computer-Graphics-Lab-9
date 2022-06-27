//201811002
//Zehra Ece Akdemir

package edu.utexas.se.swing.sample;

	import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent; 


	final public class AnimateExample extends JFrame
	{
		private static final long serialVersionUID = 1L;
		JFrame frame;
	    DrawPanel drawPanel;
	    private JPanel controlPanel;
	    
	    private int size = 24;
	    private int oneX = 85;
	    private int oneY = 75;
	    private int MAX_SIZE = 72;
	    private int MIN_SIZE = 24;
        private int x[] = { 10, 30, 40, 50, 110, 140 };
        private int y[] = { 140, 110, 50, 40, 30, 10 };
        private int numberofpoints = 6;
	    
	    private boolean started = true;
	    private Color shapeColor = Color.BLACK;
	    private Color[] colorArray = { Color.YELLOW, Color.ORANGE, Color.RED,
	    		Color.MAGENTA, Color.PINK, Color.BLUE, Color.CYAN, Color.GREEN
	    };


	    public static void main(String... args)
	    {
	        new AnimateExample().go();
	    }

	    private void go()
	    {
	    	
	        frame = new JFrame("Animate");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	        
	        drawPanel = new DrawPanel();
	        frame.getContentPane().add(BorderLayout.CENTER, drawPanel);
	        frame.setResizable(false);
	        frame.setSize(900, 900);
	        frame.setLocationByPlatform(true);
	        
	        controlPanel = new JPanel();
	        JButton startButton = new JButton("Start");        
	        JButton stopButton = new JButton("Stop");
	        
	        startButton.addActionListener(new ActionListener() {
	           public void actionPerformed(ActionEvent e) {
	        	   started = true;
	        	   System.out.println("start");
	           }          
	        });
	        stopButton.addActionListener(new ActionListener() {
	           public void actionPerformed(ActionEvent e) {
	        	   started = false;
	        	   System.out.println("stop");
	           }
	        });

	        controlPanel.add(startButton);
	        controlPanel.add(stopButton); 
	        frame.setLocationRelativeTo(null);  
	        frame.setVisible(true);
	    	
	        moveIt();

	    }
	    
	    
	    private void createUI(final JFrame frame){  
	        JPanel panel = new JPanel();
	        LayoutManager layout = new FlowLayout();  
	        panel.setLayout(layout);       

	        JButton okButton = new JButton("Ok");
	        JButton cancelButton = new JButton("Cancel");
	        cancelButton.setEnabled(false);
	        JButton submitButton = new JButton("Submit");

	        okButton.addActionListener(new ActionListener() {
	           public void actionPerformed(ActionEvent e) {
	              JOptionPane.showMessageDialog(frame, "Ok Button clicked.");
	           }
	        });

	        submitButton.addActionListener(new ActionListener() {
	           public void actionPerformed(ActionEvent e) {
	              JOptionPane.showMessageDialog(frame, "Submit Button clicked.");
	           }
	        });

	        frame.getRootPane().setDefaultButton(submitButton);

	        panel.add(okButton);
	        panel.add(cancelButton);
	        panel.add(submitButton);
	        
	        frame.getContentPane().add(panel, BorderLayout.CENTER);    
	     }
	    

	    class DrawPanel extends JPanel
	    {
	        private static final long serialVersionUID = 1L;

	        public void paintComponent(Graphics g)
	        {
	           g.setColor(Color.BLACK);
	           g.fillRect(0, 0, this.getWidth(), this.getHeight());
	           g.setColor(Color.RED);
	           g.fillRect(3, 3, this.getWidth() - 6, this.getHeight() - 6);
	           g.setColor(Color.DARK_GRAY);
	           g.fillRect(6, 6, this.getWidth() - 12, this.getHeight() - 12);
	           g.setColor(Color.GRAY);
	           g.fillPolygon(x, y, numberofpoints);
	           g.setColor(shapeColor);
	           g.fillOval(oneX, oneY, size, size);
	        }
	    }
	    
	    
	    private void moveIt()
	    {
	    	int state = 0;
	    	boolean flag = true;
	    	int c = 0, j = 0;
	        while (started)
	        {
	        	switch(state){
	        	case 0 :
	        		for(int i=0 ; i < numberofpoints; i++)
	        		{
	        			x[i] += 3;
	        		}
	        		oneX += 3;
	        		if(oneX > 650)
	        			state = 1;
	        		break;
	        	case 1 :
	        		for(int i=0 ; i < numberofpoints; i++)
	        		{
	        			y[i] += 3;
	        		}
	        		oneY += 3;
	        		if(oneY > 700)
	        			state = 2;
	        		break;
	        	case 2 :
	        		for(int i=0 ; i < numberofpoints; i++)
	        		{
	        			x[i] -= 3;
	        		}
	        		oneX -= 3;
	        		if(oneX < 200)
	        			state = 3;
	        		break;
	        	case 3 :
	        		for(int i=0 ; i < numberofpoints; i++)
	        		{
	        			y[i] -= 3;
	        		}
	        		oneY -= 3;
	        		if(oneY < 200)
	        			state = 0;
	        		break;
	        	}
	        	
	            if(size > MAX_SIZE)
	            	flag = false;   
	            else if(size < MIN_SIZE)
	            	flag = true;    
	            
	            if(flag)
	            	size++; 
	            else 
	            	size--; 

	            if(c % 25 == 0)
	            {
	            	shapeColor = colorArray[j];
	            	j++;
	            	if(j == 8)
	            		j = 0;	            	
	            }
	            
	            try
	            {
	                Thread.sleep(10);
	            }
	            catch (Exception e)
	            {
	                e.printStackTrace();
	            }
	            frame.repaint();
	            c++;
	        }
	        frame.repaint();
	    }
	}
