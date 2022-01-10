package simulation;

import visualization.MyCanvas;
import controller.Controller;
import utils.Rocket;
import utils.Vector;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class SimulationEngine {

    private final MyCanvas canvas;
    private final Controller controller;
    private final Rocket rocket;

    public SimulationEngine(Controller controller, MyCanvas graphics, Rocket rocket){
        this.controller = controller;
        this.canvas = graphics;
        this.rocket = rocket;
    }

    public void runSimulation(){
        while(true){
            canvas.repaint();
            generateWind();
            if(controller!=null){
                rocket.update(controller);
            }
            try {
                sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void generateWind(){
        Vector orientation = this.rocket.getOrientation();
        int leftOrRight = leftOrRight();
        if(orientation.getY()<=0.01){
            leftOrRight = keepPosition(orientation);
        }
        double change = getChange();
        if(controller!=null&&getRandomNumber(50)==25)
            change*=20;
        updateRoc(leftOrRight, change);
    }

    private void updateRoc(int leftOrRight, double change) {
        Vector bigVec = rocket.getOrientation().getNewVector(change, leftOrRight);
        rocket.updateOrientation(bigVec.getX(), bigVec.getY());
        rocket.getOrientation().normalize();
    }



    private double getChange() {
        return 0.01;
    }

    private int keepPosition(Vector orientation) {
        if(orientation.getX() <= 0){
            rocket.updateOrientation(-1, 0);
            return 1;
        } else if(orientation.getX() >= 0){
            rocket.updateOrientation(1, 0);
            return -1;
        }
        return 0;
    }

    private int leftOrRight(){
        Vector orientation = this.rocket.getOrientation();
        List<Integer> toGetFrom = new ArrayList<>();
        double myX = orientation.getX();
        int prob = (int)(50*myX + 50);
        for(int i=0;i<prob;i++){
            toGetFrom.add(1);
        }
        for(int i=0;i<100-prob;i++){
            toGetFrom.add(-1);
        }
        for(int i=0;i<30;i++){
            toGetFrom.add(-1);
            toGetFrom.add(1);
        }
        int index = getRandomNumber(130);
        return toGetFrom.get(index);
    }

    private int getRandomNumber(int max) {
        return (int) (Math.random() * max);
    }
}
