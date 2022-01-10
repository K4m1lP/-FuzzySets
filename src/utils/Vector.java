package utils;

import static java.lang.Math.*;
import static java.lang.Math.pow;

public class Vector {

    private double x;
    private double y;

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }

    public Vector(double x, double y){
        this.x = x;
        this.y = y;
    }

    public void normalize(){
        this.x = x/len();
        this.y = y/len();
    }

    public double len(){
        return sqrt(x*x + y*y);
    }

    public void update(double newX, double newY){
        this.x = newX;
        this.y = newY;
    }

    public Vector getNewVector(double c, int leftOrRight){
        double gamma = toDegrees(acos((2-pow(c, 2.0))/(2)));
        gamma = abs(gamma);
        gamma = leftOrRight==1?-gamma:gamma;
        double alphaS = toDegrees(atan(y/x));
        if(x==0 && y==1)
            alphaS = toDegrees(Math.PI/2);
        double xk = tan(toRadians(alphaS + gamma));
        double newX = 1/(sqrt(pow(xk, 2.0)+1));
        if( xk < 0)
            newX = -newX;
        Vector tmpVec = new Vector(newX, xk*newX);
        tmpVec.normalize();
        return tmpVec;
    }
    @Override
    public String toString(){
        return "["+x+","+y+"]";
    }
}
