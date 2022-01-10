package controller;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.rule.FuzzyRuleSet;

public class Controller {

    private final FIS fis;

    public Controller(String fileName){
        fis = FIS.load(fileName,false);
        if(fis == null){
            System.out.println("nie_wczytalo?");
        }
    }

    public double calculate(double rightEngine, double leftEngine, boolean charts){
        if(this.fis != null){
            FuzzyRuleSet fuzzyRuleSet = fis.getFuzzyRuleSet();
            fuzzyRuleSet.setVariable("rakieta_w_prawo", rightEngine);
            fuzzyRuleSet.setVariable("rakieta_w_lewo", leftEngine);
            fuzzyRuleSet.evaluate();
            if(charts){
                getCharts(fuzzyRuleSet);
            }
            return fuzzyRuleSet.getVariable("moc_silnika").defuzzify();
        }
        return 0;
    }
    public double calculate(double rightEngine, double leftEngine){
        return calculate(rightEngine, leftEngine, false);
    }

    private void getCharts(FuzzyRuleSet fuzzyRuleSet){
        fuzzyRuleSet.chart();
    }
}
