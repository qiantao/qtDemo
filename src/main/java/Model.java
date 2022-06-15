import javax.swing.*;
import java.awt.*;

public class Model {
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				MyFrame frame = new MyFrame(40,30);
				frame.setVisible(true);
				while(true){
					
				}
			}
		});
	}
}

class MyFrame extends JFrame{
	public int width = 1;
	public int heigth = 2;
	private MyPanl pan;
	public MyFrame(){
		super();
		initFrame();
	}
	MyFrame(int width,int height){
		this.width = width;
		this.heigth = height;
		pan = new MyPanl(this);
		setContentPane(pan);
		initFrame();
	}
	private void initFrame(){
		setSize(width*20,heigth*20);
		setTitle("TestDemo");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		 
		
	}
	public void rePaint(){
		pan.repaint();
	}
	
}
class MyPanl extends JPanel{
	private MyFrame frame;
	
	public MyPanl(MyFrame frame){
		super();
		this.frame = frame;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawLine(g);
		
	}
	
	private void drawLine(Graphics g){
		frame.setTitle("表格");
		for(int i=0;i<frame.width;i++){
			g.drawLine(i*20, 0, i*20, frame.heigth*20);
		}
		for(int i=0;i<frame.heigth;i++){
			g.drawLine(0, i*20, frame.width*20,i*20 );
		}
		
	}
	
}