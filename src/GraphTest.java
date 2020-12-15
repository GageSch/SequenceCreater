import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.awt.geom.Ellipse2D;

class Surface extends JPanel {
	
	public static final int HEIGHT = 1000;
	public static final int WIDTH = 1920;
	public static final int X_MIN = 1;
	public static final long X_MAX = 12000;
	public static final int Y_MIN = -50;
	public static final long Y_MAX = 2162160;
	public static final double XRATIO = (double) WIDTH/(X_MAX - X_MIN);
	public static final double YRATIO = (double) HEIGHT/(Y_MAX - Y_MIN);
	public static final int RADIUS = 1;
	
	/*OEIS entry explaining the sequence
	*
	* https://oeis.org/A336823
	*
	*/
    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        
        long y = 1;
        for(int x = X_MIN; x < X_MAX; x++) {
        	String binaryString = Integer.toBinaryString(x);
        	String[] binary = binaryString.split("");
        	if(y % getSum(binary) == 0) {
        		y /= getSum(binary);
        	}
        	else {
        		y *= getSum(binary);
        	}
        	System.out.println(x+1 + " " + y);
        	int yCord = HEIGHT - (int) Math.round((y -Y_MIN) * YRATIO);
        	int xCord = (int) Math.round((x - X_MIN)* XRATIO);
        	Ellipse2D theCircle = new Ellipse2D.Double(xCord - RADIUS, yCord - RADIUS, 2 * RADIUS, 2 * RADIUS);
        	g2d.setPaint(Color.BLACK);
            g2d.fill(theCircle);
        }
    }
    
    public static int function(int n) {
    	return n*n;
    }
    
    static long getSum(String[] str) 
    {     
        long sum = 0;
        
        for (int i = 0; i < str.length; i++) {
        	if (Integer.parseInt(str[i]) == 1) {
        		sum++;
        	}
        }
      
        return sum; 
    }

    @Override
    public void paintComponent(Graphics g) {

        
        doDrawing(g);
    }
}
