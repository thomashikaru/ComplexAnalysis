/*
 * Mandelbrot.java
 * 
 * Creates a StdDraw window which allows interactive exploration of
 * the Mandelbrot set. 
 * 
 * Features:
 * - Mouse click to zoom in on cursor
 * - Press 1 to reduce maximum number of iterations (brightens image)
 * - Press 2 to increase maximum number of iterations (darkens image)
 * - Press Esc to return to default zoom
 * 
 * Dependencies:
 * StdDraw.java from Princeton
 * https://introcs.cs.princeton.edu/java/stdlib/StdDraw.java.html
 * available in the stdlib.jar library
 */

import java.awt.event.*;

public class Mandelbrot {
	
	// resolution
	private static final double res = 1000;
	
	// maximum iterations
	private static int maxiters = 100;
	
	// window measurements
	private static double xstep = 0.001;
	private static double ystep = 0.001;
	private static double xmin = -2.0;
	private static double xmax = 1.0;
	private static double ymin = -1.5;
	private static double ymax = 1.5;
	private static double pensize = 0.001;

	public static void main(String[] args) {
		
		StdDraw.enableDoubleBuffering();
		StdDraw.setCanvasSize(900, 900);
		
		// draw the initial zoom
		draw();
		
		while (true) {
			// check for mouse click and if pressed, zoom in on mouse point
			if (StdDraw.isMousePressed()) {
				double centerx = StdDraw.mouseX();
				double centery = StdDraw.mouseY();
				double windowwidth = (xmax-xmin)/10.0;
				xmax = centerx + windowwidth;
				xmin = centerx - windowwidth;
				ymax = centery + windowwidth;
				ymin = centery - windowwidth;
				pensize = 0.01*(xmax-xmin)/res;
				maxiters += 120;
				draw();
			}
			// return to default zoom
			if (StdDraw.isKeyPressed(KeyEvent.VK_ESCAPE)) {
				xstep = 0.001;
				ystep = 0.001;
				xmin = -2.0;
				xmax = 1.0;
				ymin = -1.5;
				ymax = 1.5;
				pensize = 0.001;
				maxiters =100;
				draw();
			}
			// decreases maxiters by a factor of two (effect: brightens image)
			if (StdDraw.isKeyPressed(KeyEvent.VK_1)) {
				maxiters /= 2;
				draw();
			}
			// increases maxiters by a factor of two (effect: darkens image)
			if (StdDraw.isKeyPressed(KeyEvent.VK_2)) {
				maxiters *= 2;
				draw();
			}
		}
	}
	
	// draw the mandelbrot set for the current window
	public static void draw() {
		
		// clear screen and set scale & step
		StdDraw.clear();
		StdDraw.setXscale(xmin, xmax);
		StdDraw.setYscale(ymin, ymax);
		xstep = (xmax-xmin)/res;
		ystep = (ymax-ymin)/res;
				
		// loop over every pixel point in the window at a resolution of res
		for (double x = xmin; x < xmax; x += xstep) {
			for (double y = ymin; y < ymax; y += ystep) {
				// z starts at 0, c is the current point
				Complex z = new Complex(0.0, 0.0);
				Complex c = new Complex(x, y);
				double mag = 0;
				int iters = 0;
				
				// repeatedly iterate function
				while (iters < maxiters && mag < 2.0) {
					z = func(z, c);
					mag = Complex.mag(z);
					iters++;
				}
				
				// set pen color and draw dot
				StdDraw.setPenColor(255*iters/maxiters, 255*iters/maxiters, 255*iters/maxiters);
				StdDraw.filledCircle(x, y, pensize);
			}
		}
		
		// draw buffer to screen
		StdDraw.show();
	}

	// f(z) = z^2 + c
	public static Complex func(Complex z, Complex c) {
		return Complex.add(Complex.mult(z, z), c);
	}
}
