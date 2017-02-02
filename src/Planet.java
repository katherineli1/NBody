
public class Planet {
	double myXPos;
	double myYPos;
	double myXVel;
	double myYVel;
	double myMass;
	String myFileName;
	
	public Planet(double xP, double yP, double xV,
            double yV, double m, String img) {
		myXPos = xP;
		myYPos = yP;
		myXVel = xV;
		myYVel = yV;
		myMass = m;
		myFileName = img;
	}
	
	public Planet(Planet p) {
		myXPos = p.myXPos; //could also say this.myXPos, which references this specific instance of the variable
		myYPos = p.myYPos;
		myXVel = p.myXVel;
		myYVel = p.myYVel;
		myMass = p.myMass;
		myFileName = p.myFileName;
	}
	
	public double calcDistance(Planet p) {
		double r = Math.sqrt(Math.pow(this.myXPos - p.myXPos, 2) + Math.pow(this.myYPos - p.myYPos, 2));
		return r;
	}
	
	public double calcForceExertedBy(Planet p) {
		double f = 6.67e-11 * this.myMass * p.myMass / Math.pow(this.calcDistance(p), 2);
		return f;
	}
	
	public double calcForceExertedByX(Planet p) {
		double fx = this.calcForceExertedBy(p) * (p.myXPos - this.myXPos) / this.calcDistance(p);
		return fx;
	}
	
	public double calcForceExertedByY(Planet p) {
		double fy = this.calcForceExertedBy(p) * (p.myYPos - this.myYPos) / this.calcDistance(p);
		return fy;
	}
	
	public double calcNetForceExertedByX(Planet[] p) {
		double netx = 0;
		for (int i = 0; i < p.length; i++) {
			if (! p[i].equals(this)) {
				netx += this.calcForceExertedByX(p[i]);
			}
		}
		return netx;
	}
	
	public double calcNetForceExertedByY(Planet[] p) {
		double nety = 0;
		for (int i = 0; i < p.length; i++) {
			if (! p[i].equals(this)) {
				nety += this.calcForceExertedByY(p[i]);
			}
		}
		return nety;
	}
	
	public void update(double time, double xforce, double yforce) {
		double ax = xforce / this.myMass;
		double ay = yforce / this.myMass;
		this.myXVel = this.myXVel + ax * time;
		this.myYVel = this.myYVel + ay * time;
		this.myXPos = this.myXPos + this.myXVel * time;
		this.myYPos = this.myYPos + this.myYVel * time;
	}
	
	public void draw() {
		StdDraw.picture(myXPos, myYPos, myFileName);
	}
}
