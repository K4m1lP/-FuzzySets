import visualization.MyGraphics;
import controller.Controller;

public class Main {

    private static boolean program = true;

    public static void main(String[] args) {

        if(program){
            Thr one = new Thr(true);
            Thr two = new Thr(false);
            one.start();
            two.start();
            try {
                one.join();
                two.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            Controller controller = new Controller("fuzzy_example.fcl");
            controller.calculate(0, 0, true);

        }

    }
}

class Thr extends Thread{
    private final boolean controller;
    public Thr(boolean controller){
        this.controller = controller;
    }
    @Override
    public void run(){
        MyGraphics myGraphics = new MyGraphics(controller);
    }
}