public class NBody{
    public static double readRadius(String file){
        In in = new In(file);
		int firstItemInFile = in.readInt();
		double secondItemInFile = in.readDouble();
        return secondItemInFile;
    }

    public static Planet[] readPlanets(String file){
        In in = new In(file);
        int len = in.readInt(); 
        Planet[]arr = new Planet[len];
        double radius = in.readDouble();
		for(int i = 0; i < len; i++){
           double xp = in.readDouble();
           double yp = in.readDouble();
           double vx = in.readDouble();
           double vy = in.readDouble();
           double m = in.readDouble();
           String img = in.readString();
           arr[i] = new Planet(xp, yp, vx, vy, m, img);
        }
        return arr;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        double r = readRadius(filename);
        Planet[] p = readPlanets(filename);

        StdDraw.setXscale(-r, r);
		StdDraw.setYscale(-r, r);
        StdDraw.enableDoubleBuffering();
        
        double t = 0;
        int num = p.length;
        while(t <= T){
            double[] x_force = new double[num];
            double[] y_force = new double[num];
            for(int i = 0; i < num; i++){
                x_force[i] = p[i].calcNetForceExertedByX(p);
                y_force[i] = p[i].calcNetForceExertedByY(p);
            }

            for(int i = 0; i < num; i++){
                p[i].update(dt, x_force[i], y_force[i]);
            }

			StdDraw.picture(0, 0, "images/starfield.jpg");
            for(int i = 0; i< num; i++){
                p[i].draw();
            }
			StdDraw.show();
			StdDraw.pause(10);
            t += dt;
        }
        StdOut.printf("%d\n", p.length);
        StdOut.printf("%.2e\n", r);
        for (int i = 0; i < p.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            p[i].xxPos, p[i].yyPos, p[i].xxVel,
            p[i].yyVel, p[i].mass,  p[i].imgFileName);   
        }
}

}
