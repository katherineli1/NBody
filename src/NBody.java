import java.io.*;
import java.util.*;

public class NBody {
	
	public static void main(String[] args){
		double T = 157788000.0; //2000000; //1000000.0;
		double dt = 25000.0;
		String pfile = "data/planets.txt";
		
		if (args.length > 2) {
			T = Double.parseDouble(args[0]);
			dt = Double.parseDouble(args[1]);
			pfile = args[2];
		}	
		Planet[] planets = readPlanets(pfile);
		double radius = readRadius(pfile);
		
		StdDraw.setScale(-radius, radius);
		StdDraw.picture(0, 0, "images/starfield.jpg");
		for (int i = 0; i < planets.length; i++) {
			planets[i].draw();
		}
		
		for (double time = 0; time < T; time += dt) {
			double[] xForces = new double[planets.length];
			double[] yForces = new double[planets.length];
			for (int i = 0; i < planets.length; i++) {
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}
			for (int i = 0; i < planets.length; i++) {
				planets[i].update(dt, xForces[i], yForces[i]);
			}
			StdDraw.picture(0, 0, "images/starfield.jpg");
			for (int i = 0; i < planets.length; i++) {
				planets[i].draw();
			}
			StdDraw.show(10);
		}
		
		System.out.printf("%d\n", planets.length);
		System.out.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
		    System.out.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		   		              planets[i].myXPos, planets[i].myYPos, 
		                      planets[i].myXVel, planets[i].myYVel, 
		                      planets[i].myMass, planets[i].myFileName);
		}
	}
	
	public static double readRadius(String fname) {
		Scanner scan;
		try {
			scan = new Scanner(new File(fname));
		} catch (FileNotFoundException e) {
            return 0;
        }
		scan.nextDouble();
		double answer = scan.nextDouble();
		scan.close();
		return answer;
	}
	
	public static Planet[] readPlanets(String fname) {
		Scanner scan;
		try {
			scan = new Scanner(new File(fname));
		} catch (FileNotFoundException e) {
            return null;
        }
		
		int n = scan.nextInt();
		double r = scan.nextDouble();
		Planet[] planets = new Planet[n];
		
		for (int i = 0; i < n; i++) {
			Planet p = new Planet(scan.nextDouble(), scan.nextDouble(), scan.nextDouble(), scan.nextDouble(), scan.nextDouble(), "images/" + scan.next());
			planets[i] = p;
		}
		scan.close();
		return planets;
	}
}
