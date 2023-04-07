//Basic Game Application
//Version 2
// Basic Object, Image, Movement
// Astronaut moves to the right.
// Threaded

//K. Chun 8/2018

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries
import java.awt.Graphics2D;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

//
//*******************************************************************************
// Class Definition Section

public class BasicGameApp implements Runnable, KeyListener, MouseListener, MouseMotionListener {

	//Variable Definition Section
	//Declare the variables used in the program
	//You can set their initial values too

	//Sets the width and height of the program window
	final int WIDTH = 1000;
	final int HEIGHT = 700;

	//Declare the variables needed for the graphics
	public JFrame frame;
	public Canvas canvas;
	public JPanel panel;


	public BufferStrategy bufferStrategy;
	public Image hollypic;
	public Image steffypic;
	public Image background;
	public Image bartpic;
	public Image jerrypic;
	public Image troffpic;

	//Declare the objects used in the program
	//These are things that are made up of more than one variable type
	//declaring there is a array with []
	private Astronaut holly;
	private Astronaut steffy;
	private Astronaut bart;
	private Astronaut jerry;


	// Main method definition
	// This is the code that runs first and automatically
	public static void main(String[] args) {
		BasicGameApp ex = new BasicGameApp();   //creates a new instance of the game
		new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method  
	}

	// Constructor Method
	// This has the same name as the class
	// This section is the setup portion of the program
	// Initialize your variables and construct your program objects here.
	public BasicGameApp() {

		setUpGraphics();
		canvas.addKeyListener(this);
		//variable and objects
		//create (construct) the objects needed for the game and load up
		background = Toolkit.getDefaultToolkit().getImage("FEELD.jpg");
		troffpic = Toolkit.getDefaultToolkit().getImage("troff.jpeg");
		hollypic = Toolkit.getDefaultToolkit().getImage("holly.png"); //load the picture
		steffypic = Toolkit.getDefaultToolkit().getImage("steffythegoat.png");
		bartpic = Toolkit.getDefaultToolkit().getImage("bart.png");
		jerrypic = Toolkit.getDefaultToolkit().getImage("jerry.PNG");
		holly = new Astronaut(100, 100);
		steffy = new Astronaut(100, 600);
		bart = new Astronaut(500, 330);
		jerry = new Astronaut(200, 400);
		holly.dy = 1;
		holly.dx = 1;
		steffy.dx = 7;
		steffy.dy = 9;
		bart.dx = 1;
		bart.dy = 1 / 3;
		jerry.dx = 3;
		jerry.dy = 2 / 7;


	}// BasicGameApp()


//*******************************************************************************

	public void run() {

		//for the moment we will loop things forever.
		while (true) {

			moveThings();  //move all the game objects
			render();  // paint the graphics
			pause(10);



		}
	}


	public void crash() {

		if (bart.rec.intersects(steffy.rec) && bart.isAlive == true && steffy.isAlive == true) {
			System.out.println("steffy and bart just crashed");
			steffy.dx = -1 * steffy.dx;
			steffy.dy = -steffy.dy;
			bart.dx = -1 * bart.dx;
			bart.dy = -bart.dy;
			//steffy.isAlive = false;
			bartpic = steffypic;
		}
	}

	public void moveThings() {
			//calls the move( ) code in the objects
		//	holly.move();
			//holly.bounce();
			bart.bounce();
			bart.move();
			steffy.move();
			steffy.bounce();
			jerry.move();
	    	//jerry.bounce();
			jerry.wrap();

			crash();
			crash2();

		}






	//Pauses or sleeps the computer for the amount specified in milliseconds
	public void pause(int time) {
		//sleep
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {

		}
	}

	//Graphics setup method
	private void setUpGraphics() {
		frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.
		panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
		panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
		panel.setLayout(null);   //set the layout


		// creates a canvas which is a blank rectangular area of the screen onto which the application can draw
		// and trap input events (Mouse and Keyboard events)
		canvas = new Canvas();
		canvas.setBounds(0, 0, WIDTH, HEIGHT);
		canvas.setIgnoreRepaint(true);


		panel.add(canvas);  // adds the canvas to the panel.
		canvas.addKeyListener(this);
		canvas.addMouseListener(this);
		canvas.addMouseMotionListener(this);


		// frame operations
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
		frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
		frame.setResizable(false);   //makes it so the frame cannot be resized
		frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

		// sets up things so the screen displays images nicely.
		canvas.createBufferStrategy(2);
		bufferStrategy = canvas.getBufferStrategy();
		canvas.requestFocus();
		System.out.println("DONE graphic setup");

	}


	//paints things on the screen using bufferStrategy
	private void render() {
		Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		g.clearRect(0, 0, WIDTH, HEIGHT);


		g.drawImage(background, 0, 0, WIDTH, HEIGHT, null);
		//g.drawImage(troffpic,10,400, 100, 50, null);
		for (int i = 0; i <troff.length; i++) {
			g.drawImage(troffpic,troff[i].xpos, troff[i].ypos, troff[i].width, troff[i].height, null);
		}
		g.drawImage(hollypic, holly.xpos, holly.ypos, holly.width, holly.height, null);
		if (holly.isAlive == true) {
			g.drawImage(steffypic, steffy.xpos, steffy.ypos, steffy.width, steffy.height, null);
		}
		g.drawImage(bartpic, bart.xpos, bart.ypos, bart.width, bart.height, null);
		g.drawImage(jerrypic, jerry.xpos, jerry.ypos, jerry.width, jerry.height, null);
		if (bart.isAlive == true) {
			g.drawImage(steffypic, steffy.xpos, steffy.ypos, steffy.width, steffy.height, null);
		}
		bufferStrategy.show();
	}




	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		System.out.println(code);

		if (code == 65)
			holly.xpos = holly.xpos -3;
		if (code == 87)
			holly.ypos = holly.ypos - 3;
		if (code == 68)
			holly.xpos = holly.xpos +3;
		if (code == 83)
			holly.ypos = holly.ypos +3;


		//if (code )
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println(e.getX());


	}

	@Override
	public void mousePressed(MouseEvent e) {
		holly.xpos = e.getX();
		holly.ypos = e.getY();
		holly.width = holly.width * 2;

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		holly.width = 80;
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {


	}

	@Override
	public void mouseMoved(MouseEvent e) {
		holly.xpos = e.getX();
		holly.ypos = e.getY();
		holly.width = 200;

	}

	public BufferStrategy getBufferStrategy() {
		return bufferStrategy;
	}

	public Troff[] troff;
	//	public static void main (String [] args)
	{
		troff = new Troff[5];
		for (int i = 0; i <troff.length; i++) {
			troff[i] = new Troff((int) (Math.random() * 700) + 10, 500);


		}



	}
	public void crash2() {

		if (holly.rec.intersects (jerry.rec) && holly.isAlive == true && jerry.isAlive == true) {

			jerry.dx = -1 * jerry.dx;
			jerry.dy = -jerry.dy;
			holly.dx = -1 * holly.dx;
			holly.dy = -holly.dy;
			System.out.println("holly just hit jerry!");
		}

		}
	}

