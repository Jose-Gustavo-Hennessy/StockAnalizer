package org.insight.Analyzer;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JLabel;

// this class will perform all the analisys of the stock data 
public class Analisys {

	public void MESSAGE() {
		System.out.println("IT WORKS!");
	}
	
    public JLabel DetectDDoji(ArrayList<ArrayList<String>> Data, JLabel DDOJI) {
    	
		
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
    		/*if( BIG - SMALL <= ERROR) {
    			if( CLOSE > LOW+((2*(HIGH-LOW))/3)) {
    				System.out.println("DRAGONFLY DOJI DETECTED!!! " + Data.get(0).get(i));
    				DDOJI.setText("          DRAGONFLY DOJI PATTERN WAS DETECTED          ");
    				DDOJI.setForeground(Color.GREEN);
    				return DDOJI;
    			}
    		}*/
    		if( (HIGH-LOW)*0.05 >= Math.abs(OPEN-CLOSE)) {
    			if( CLOSE > LOW+((2*(HIGH-LOW))/3)) {
    				System.out.println("DRAGONFLY DOJI DETECTED!!! " + Data.get(0).get(i));
    				DDOJI.setText("          DRAGONFLY DOJI PATTERN WAS DETECTED          ");
    				DDOJI.setForeground(Color.GREEN);
    				return DDOJI;
    			}
    		}
    	}
		DDOJI.setText("          DRAGONFLY DOJI PATTERN WAS NOT DETECTED          ");
		DDOJI.setForeground(Color.RED);
		return DDOJI;
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
    
    public JLabel DetectGSDoji(ArrayList<ArrayList<String>> Data, JLabel GSDOJI) {
    	
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
    			if( OPEN <= (LOW+((HIGH-LOW)/3))) {
    				System.out.println("GRAVESTONE DOJI DETECTED!!! " + Data.get(0).get(i));
    				GSDOJI.setText("          GRAVESTONE DOJI PATTERN WAS DETECTED          ");
    				GSDOJI.setForeground(Color.GREEN);
    				return GSDOJI;
    			}
    		}
    	}
    	GSDOJI.setText("          GRAVESTONE DOJI PATTERN WAS NOT DETECTED          ");
		GSDOJI.setForeground(Color.RED);
    	return GSDOJI;
    }
    
    public JLabel DetectBearishEngulfing(ArrayList<ArrayList<String>> Data, JLabel BEDOJI) {
    	
		
    	for(int i=4;i>=1;i--) {
    		Double OPEN = Double.parseDouble(Data.get(1).get(i));
    		Double HIGH = Double.parseDouble(Data.get(2).get(i));
    		Double LOW = Double.parseDouble(Data.get(3).get(i));;
    		Double CLOSE = Double.parseDouble(Data.get(4).get(i));

    		Double OPEN2 = Double.parseDouble(Data.get(1).get(i-1));
    		Double HIGH2 = Double.parseDouble(Data.get(2).get(i-1));
    		Double LOW2 = Double.parseDouble(Data.get(3).get(i-1));;
    		Double CLOSE2 = Double.parseDouble(Data.get(4).get(i-1));
    		
    		if((OPEN < CLOSE) && (OPEN2 > CLOSE2) && (HIGH<HIGH2) && (LOW>LOW2)) {
    			BEDOJI.setForeground(Color.GREEN);
    			BEDOJI.setText("          BEARISH ENGULFING PATTERN DETECTED          ");
    			return BEDOJI;
    		}

    		
    	}
		BEDOJI.setText("          BEARISH ENGULFING PATTERN WAS NOT DETECTED          ");
    	BEDOJI.setForeground(Color.RED);
    	return BEDOJI;
    }
    

    public JLabel DetectBullishEngulfing(ArrayList<ArrayList<String>> Data, JLabel BUEDOJI) {
    	
    	for(int i=4;i>=1;i--) {
    		Double OPEN = Double.parseDouble(Data.get(1).get(i));
    		Double HIGH = Double.parseDouble(Data.get(2).get(i));
    		Double LOW = Double.parseDouble(Data.get(3).get(i));;
    		Double CLOSE = Double.parseDouble(Data.get(4).get(i));

    		Double OPEN2 = Double.parseDouble(Data.get(1).get(i-1));
    		Double HIGH2 = Double.parseDouble(Data.get(2).get(i-1));
    		Double LOW2 = Double.parseDouble(Data.get(3).get(i-1));;
    		Double CLOSE2 = Double.parseDouble(Data.get(4).get(i-1));
    		
    		if((OPEN > CLOSE) && (OPEN2 < CLOSE2) && (HIGH<HIGH2) && (LOW>LOW2)) {
    			BUEDOJI.setForeground(Color.GREEN);
    			BUEDOJI.setText("          BULLISH ENGULFING PATTERN WAS DETECTED          ");
    			return BUEDOJI;
    		}

    		
    	}
		BUEDOJI.setForeground(Color.RED);
		BUEDOJI.setText("          BULLISH ENGULFING PATTERN WAS NOT DETECTED          ");
		return BUEDOJI;
    }
    
    public JLabel DetectMorningStar(ArrayList<ArrayList<String>> Data, JLabel MSC) {
    	
    	for(int i=4;i>=2;i--) {
    		Double OPEN = Double.parseDouble(Data.get(1).get(i));
    		Double HIGH = Double.parseDouble(Data.get(2).get(i));
    		Double LOW = Double.parseDouble(Data.get(3).get(i));;
    		Double CLOSE = Double.parseDouble(Data.get(4).get(i));

    		Double OPEN2 = Double.parseDouble(Data.get(1).get(i-1));
    		Double HIGH2 = Double.parseDouble(Data.get(2).get(i-1));
    		Double LOW2 = Double.parseDouble(Data.get(3).get(i-1));;
    		Double CLOSE2 = Double.parseDouble(Data.get(4).get(i-1));
    		
    		Double OPEN3 = Double.parseDouble(Data.get(1).get(i-2));
    		Double HIGH3 = Double.parseDouble(Data.get(2).get(i-2));
    		Double LOW3 = Double.parseDouble(Data.get(3).get(i-2));;
    		Double CLOSE3 = Double.parseDouble(Data.get(4).get(i-2));
    		
    		if((OPEN > CLOSE) && (Math.abs(OPEN-CLOSE)>(HIGH-LOW)/2) && // first bar bullish and middle sized
    		 ((HIGH2-LOW2)*0.25 >= Math.abs(OPEN2-CLOSE2) && (OPEN2<CLOSE) ) && // second bar is a close doji and lower than the close of prev
    		 (OPEN3<CLOSE3) && (OPEN3>CLOSE2) && (CLOSE3>(LOW+((HIGH-LOW)/2))) ) {
    			MSC.setForeground(Color.GREEN);
    			MSC.setText("          Morning Star PATTERN WAS DETECTED          ");
    			return MSC;
    		}

    		
    	}
    	MSC.setForeground(Color.RED);
		MSC.setText("          Morning Star PATTERN WAS NOT DETECTED          ");
    	return MSC;
    }
    
    public JLabel DetectEveningStar(ArrayList<ArrayList<String>> Data, JLabel ESC) {
    	
    	for(int i=4;i>=2;i--) {
    		Double OPEN = Double.parseDouble(Data.get(1).get(i));
    		Double HIGH = Double.parseDouble(Data.get(2).get(i));
    		Double LOW = Double.parseDouble(Data.get(3).get(i));;
    		Double CLOSE = Double.parseDouble(Data.get(4).get(i));

    		Double OPEN2 = Double.parseDouble(Data.get(1).get(i-1));
    		Double HIGH2 = Double.parseDouble(Data.get(2).get(i-1));
    		Double LOW2 = Double.parseDouble(Data.get(3).get(i-1));;
    		Double CLOSE2 = Double.parseDouble(Data.get(4).get(i-1));
    		
    		Double OPEN3 = Double.parseDouble(Data.get(1).get(i-2));
    		Double HIGH3 = Double.parseDouble(Data.get(2).get(i-2));
    		Double LOW3 = Double.parseDouble(Data.get(3).get(i-2));;
    		Double CLOSE3 = Double.parseDouble(Data.get(4).get(i-2));
    		
    		if((OPEN < CLOSE) && (Math.abs(OPEN-CLOSE)>(HIGH-LOW)/2) && // first bar bullish and middle sized
    		 ((HIGH2-LOW2)*0.25 >= Math.abs(OPEN2-CLOSE2) && (OPEN2>CLOSE) ) && // second bar is a close doji and lower than the close of prev
    		 (OPEN3>CLOSE3) && (OPEN3<CLOSE2) && (CLOSE3<(HIGH-((HIGH-LOW)/2))) ) {// this is bigger than second and closes higher than half of the first
    			ESC.setForeground(Color.GREEN);
    			ESC.setText("          Evening Star PATTERN WAS DETECTED          ");
    			return ESC;
    		}

    		
    	}
    	ESC.setForeground(Color.RED);
		ESC.setText("          Evening Star PATTERN WAS NOT DETECTED          ");
    	return ESC;
    }
    
    public JLabel BullishHarami(ArrayList<ArrayList<String>> Data, JLabel BH) {
    	
    	for(int i=4;i>=1;i--) {
    		Double OPEN = Double.parseDouble(Data.get(1).get(i));
    		Double HIGH = Double.parseDouble(Data.get(2).get(i));
    		Double LOW = Double.parseDouble(Data.get(3).get(i));;
    		Double CLOSE = Double.parseDouble(Data.get(4).get(i));

    		Double OPEN2 = Double.parseDouble(Data.get(1).get(i-1));
    		Double HIGH2 = Double.parseDouble(Data.get(2).get(i-1));
    		Double LOW2 = Double.parseDouble(Data.get(3).get(i-1));;
    		Double CLOSE2 = Double.parseDouble(Data.get(4).get(i-1));
    		
    		if((OPEN > CLOSE) && (OPEN2 < CLOSE2) && (Math.abs(OPEN-CLOSE) >= 3.50*Math.abs(OPEN2-CLOSE2))) {
    			BH.setForeground(Color.GREEN);
    			BH.setText("          BULLISH HARAMI PATTERN WAS DETECTED          ");
    			return BH;
    		}

    		
    	}
		BH.setForeground(Color.RED);
		BH.setText("          BULLISH HARAMI PATTERN WAS NOT DETECTED          ");
		return BH;
    }
    
    public JLabel BearishHarami(ArrayList<ArrayList<String>> Data, JLabel BH) {
    	
    	for(int i=4;i>=1;i--) {
    		Double OPEN = Double.parseDouble(Data.get(1).get(i));
    		Double HIGH = Double.parseDouble(Data.get(2).get(i));
    		Double LOW = Double.parseDouble(Data.get(3).get(i));;
    		Double CLOSE = Double.parseDouble(Data.get(4).get(i));

    		Double OPEN2 = Double.parseDouble(Data.get(1).get(i-1));
    		Double HIGH2 = Double.parseDouble(Data.get(2).get(i-1));
    		Double LOW2 = Double.parseDouble(Data.get(3).get(i-1));;
    		Double CLOSE2 = Double.parseDouble(Data.get(4).get(i-1));
    		
    		if((OPEN < CLOSE) && (OPEN2 > CLOSE2) && (Math.abs(OPEN-CLOSE) >= 3.50*Math.abs(OPEN2-CLOSE2))) {
    			BH.setForeground(Color.GREEN);
    			BH.setText("          BEARISH HARAMI PATTERN WAS DETECTED          ");
    			return BH;
    		}

    		
    	}
		BH.setForeground(Color.RED);
		BH.setText("          BEARISH HARAMI PATTERN WAS NOT DETECTED          ");
		return BH;
    }
}


