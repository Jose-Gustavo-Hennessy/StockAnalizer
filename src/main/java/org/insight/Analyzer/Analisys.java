package org.insight.Analyzer;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JLabel;

// this class will perform all the analisys of the stock data 
public class Analisys {

	public void MESSAGE() {
		System.out.println("IT WORKS!");
	}
	
    public JLabel DetectLLDoji(ArrayList<ArrayList<String>> Data, JLabel LLDOJI) {
    	
    	for(int i = 0;i<=4;i++) {
    		
    		Double OPEN = Double.parseDouble(Data.get(1).get(i));
    		Double HIGH = Double.parseDouble(Data.get(2).get(i));
    		Double LOW = Double.parseDouble(Data.get(3).get(i));;
    		Double CLOSE = Double.parseDouble(Data.get(4).get(i));
    		
    		Double RANGE = HIGH - LOW;// this is the size of the whole range of trading prices
    		Double ERROR = 0.05 * RANGE; // Margin of error of 5% of the whole range 
    		
    		// we need to know what is bigger the open or close so we can calculate using positive values
    		Double BIG = OPEN>CLOSE ? OPEN : CLOSE ;
    		Double SMALL = OPEN>CLOSE ? CLOSE : OPEN;

    		// if open are close are the same (within 5% error) its a doji
    		if( BIG - SMALL <= ERROR) {
    			if( CLOSE > LOW+(((HIGH-LOW))/3) && OPEN < (LOW+(2*(HIGH-LOW)/3))) {
    				System.out.println("LONG-LEGGED DOJI DETECTED!!! " + Data.get(0).get(i));
    				LLDOJI.setText("          LONG-LEGGED DOJI PATTERN WAS DETECTED          ");
    				LLDOJI.setForeground(Color.GREEN);
    				return LLDOJI;
    			}
    		}
    	}
    	LLDOJI.setText("          LONG-LEGGED DOJI PATTERN WAS NOT DETECTED          ");
		LLDOJI.setForeground(Color.RED);
    	return LLDOJI;
    }
}


