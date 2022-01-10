package utils;

import controller.Controller;

import static java.lang.Math.*;

public class Rocket {

    private final Vector orientation;
    private boolean leftEngine = false;
    private boolean rightEngine = false;

    public Rocket(){
        this.orientation = new Vector(0, 1);
    }

    public Vector getOrientation() {
        return orientation;
    }

    public void update(Controller controller) {
        double distRight = 0;
        double distLeft = 0;
        if(orientation.getX()>=0)
            distRight = orientation.getX();
        else if(orientation.getX() < 0)
            distLeft = orientation.getX();
        double res = controller.calculate(distRight, distLeft)/300;
        int LOR = res>=0?1:-1;
        leftEngine = false;
        rightEngine = false;
        if(res!=0){
            if(LOR==1)
                leftEngine = true;
            else
                rightEngine = true;
        }
        Vector bigVec = orientation.getNewVector(abs(res), LOR);
        this.orientation.update(bigVec.getX(), bigVec.getY());
        this.orientation.normalize();
    }

    public void updateOrientation(double x, double y){
        this.orientation.update(x, y);
    }

    public double getAngle(){
        double r = atan(orientation.getY()/orientation.getX());
        r = r<0?r+Math.PI:r;
        return r;
    }
    public boolean getRightEngine(){
        return rightEngine;
    }
    public boolean getLeftEngine(){
        return leftEngine;
    }
}
