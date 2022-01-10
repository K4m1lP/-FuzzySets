package visualization;

import controller.Controller;
import simulation.SimulationEngine;
import utils.Rocket;

import javax.swing.*;
import java.awt.*;

public class MyGraphics extends JFrame {

    public MyGraphics(boolean con){
        Rocket rocket = new Rocket();
        int h=500;
        int w=500;

        setLayout(new BorderLayout());
        setSize(h, w);
        if(con){
            setTitle("Simulation with controller");
        } else {
            setTitle("Simulation without controller");
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        MyCanvas canvas = new MyCanvas(rocket);
        add("Center", canvas);
        if(con){
            Controller controller = new Controller("fuzzy_example.fcl");
            SimulationEngine simulationEngine = new SimulationEngine(controller, canvas, rocket);
            simulationEngine.runSimulation();
        } else {
            SimulationEngine simulationEngine = new SimulationEngine(null, canvas, rocket);
            simulationEngine.runSimulation();
        }
    }
}
