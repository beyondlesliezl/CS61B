public class Planet{
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    private static double G = 6.67e-11;

    public Planet(double xP, double yP, double xV,
              double yV, double m, String img){
                xxPos = xP;
                yyPos = yP;
                xxVel = xV;
                yyVel = yV;
                mass = m;
                imgFileName = img;
              }
    public Planet(Planet p){
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p){
        double diff_x = this.xxPos - p.xxPos;
        double diff_y = this.yyPos - p.yyPos;
        diff_x *= diff_x;
        diff_y *= diff_y;
        return Math.sqrt(diff_x + diff_y);
    }

    public double calcForceExertedBy(Planet p){
        double molecular = G * mass * p.mass;
        System.out.println(molecular);
        double r = calcDistance(p) * calcDistance(p);
        return molecular / r;
    }

    public double calcForceExertedByX(Planet p){
        return calcForceExertedBy(p) * ((p.xxPos -xxPos)) / calcDistance(p);
    }
    public double calcForceExertedByY(Planet p){
        return calcForceExertedBy(p) * ((p.yyPos -yyPos)) / calcDistance(p);
    }

    public double calcNetForceExertedByX(Planet[] p ){
        double force_x = 0;
        for(int i = 0; i < p.length; i++){
            if(p[i].equals(this)){
                continue;
            }
            force_x += calcForceExertedByX(p[i]);
        }
        return force_x;
    }
    public double calcNetForceExertedByY(Planet[] p){
        double force_y = 0;
        for(int i = 0; i < p.length; i++){
            if(p[i].equals(this)){
                continue;
            }
            force_y += calcForceExertedByY(p[i]);
        }
        return force_y;
    }

    public void update(double dt, double fX, double fY){
        double ax = fX / mass;
        double ay = fY / mass;
        xxVel += ax * dt;
        yyVel += ay * dt;
        xxPos += xxVel * dt;
        yyPos += yyVel * dt;
    }

    public void draw(){
        //use the draw api,draw the planet's position and its image
		StdDraw.picture(xxPos, yyPos,  "images/" + imgFileName);
    }
}