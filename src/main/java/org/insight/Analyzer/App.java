package org.insight.Analyzer;

import org.jfree.chart.*;
import org.jfree.chart.axis.*;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.CandlestickRenderer;
import org.jfree.data.xy.*;


import java.io.File;
import java.net.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
    	
    	Analisys Calc = new Analisys();
    	Calc.MESSAGE();
    	
    	String InitialTicker = "JBLU";
    	
    	JFrame Window = MakeWindow(InitialTicker);// Create Initial Window and input panel (Border layout is west)
    	
        //Window.pack();
        Window.setSize(1550,838);
        
    	Window.setVisible(true);
    	
    	
    	System.out.println( "Hello World!" );

    }
    
    // Gets Stock data from Yahoo Finance
    static ArrayList<ArrayList<String>> GetData(String Ticker) throws Exception {
    	
    	// get the last week of data 
    	long Time = Calendar.getInstance().getTimeInMillis()/1000L;
    	String Period2 = String.valueOf(Time);
    	Time = Time - (86400*10);
    	String Period1 = String.valueOf(Time);

    	ArrayList<ArrayList<String>> Data = new ArrayList<ArrayList<String>>();
    	
    	Data.add(new ArrayList<String>());//Dates
    	Data.add(new ArrayList<String>());//Open
    	Data.add(new ArrayList<String>());//High
    	Data.add(new ArrayList<String>());//Low
    	Data.add(new ArrayList<String>());//Close
    	Data.add(new ArrayList<String>());//Volume

    	//create URL for the Yahoo request
    	String Link = "https://query1.finance.yahoo.com/v7/finance/download/";
    	Link = Link.concat(Ticker);
    	Link = Link.concat("?period1=");
    	Link = Link.concat(Period1);
    	Link = Link.concat("&period2=");
    	Link = Link.concat(Period2);
    	Link = Link.concat("&interval=1d&events=history&includeAdjustedClose=true");
		URL yahoo = new URL(Link);
		URLConnection yc = yahoo.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
		
		String inputLine;

		while ((inputLine = in.readLine()) != null){ 			
			if(!inputLine.equals("Date,Open,High,Low,Close,Adj Close,Volume")){
			
				ArrayList<String> DASTA= new ArrayList<String>(Arrays.asList(inputLine.split(",")));
								
				Data.get(0).add(DASTA.get(0));
				Data.get(1).add(DASTA.get(1));
				Data.get(2).add(DASTA.get(2));
				Data.get(3).add(DASTA.get(3));
				Data.get(4).add(DASTA.get(4));
			}
		}
		in.close();
		
		//boolean X = DetectDoji(Data);
		
		// Data is ordered from old to new we need to get the newest 5 so we reverse to get new to old.
		Collections.reverse(Data.get(0));
		Collections.reverse(Data.get(1));
		Collections.reverse(Data.get(2));
		Collections.reverse(Data.get(3));
		Collections.reverse(Data.get(4));
    	
		
    	return  Data;
    }
    
    // Creates the Initial Window and Input Panel
    static JFrame MakeWindow(String InitialTicker) throws Exception {
    	
    	final JFrame Window = new JFrame("Stock Trend Technical Analysis");
    	Window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	JPanel InputPanel = new JPanel();
    	InputPanel.setLayout(new GridBagLayout());
    	Window.add(InputPanel);
        Window.add(InputPanel, BorderLayout.WEST);
        
        GridBagConstraints Zoning = new GridBagConstraints();
    	Zoning.insets = new Insets(10,10,10,10);
    	
    	JLabel CompanyName = new JLabel("INSERT COMPANY TICKER TO ANALYZE");
        Zoning.gridx = 0;
        Zoning.gridy = 1;
    	InputPanel.add(CompanyName,Zoning);
    	final JTextField TickerName = new JTextField(30);
    	TickerName.setText(InitialTicker);
        Zoning.gridx = 0;
        Zoning.gridy = 2;
    	InputPanel.add(TickerName,Zoning);
    	JButton ButtonStart = new JButton("Analyze Stock");
        Zoning.gridx = 1;
        Zoning.gridy = 2;
        ButtonStart.setSize(500, 100);
    	InputPanel.add(ButtonStart,Zoning);
    	JLabel OR = new JLabel("- OR -");
        Zoning.gridx = 0;
        Zoning.gridy = 3;
    	InputPanel.add(OR,Zoning);
    	JLabel Select = new JLabel("SELECT COMPANY STOCK TO ANALIZE");
        Zoning.gridx = 0;
        Zoning.gridy = 4;
    	InputPanel.add(Select,Zoning);
    	Zoning.gridx = 0;
        Zoning.gridy = 5;
        // CSV need to be aded and new method created to get choises to work
        
        try {
        	File FileOb = new File("companies.csv");
        	Scanner Reader = new Scanner(FileOb);
        	while(Reader.hasNextLine()) {
        		String data = Reader.nextLine();
        		System.out.println(data);
        	}
        }
        catch(FileNotFoundException e) {
        	System.out.println("FNF Error");
        }
        
    	String[] choises = {"AAPL","MSFT","TWTR","DIS","V","FB","ADBE"};
    	// end of CSV
    	final JComboBox<String> CompanySelect = new JComboBox<String>(choises);
    	InputPanel.add(CompanySelect,Zoning);
    	JButton ButtonStart2 = new JButton("Analyze Stock");
        Zoning.gridx = 1;
        Zoning.gridy = 5;
        ButtonStart.setSize(500, 100);
    	InputPanel.add(ButtonStart2,Zoning);
    	
    	final JPanel PanelOne = new JPanel();
    	
    	ArrayList<ArrayList<String>> Data = GetData(InitialTicker);
    	
    	PanelOne.add(MakeChart(Data));
    	Window.add(PanelOne,BorderLayout.CENTER);
    	Window.add(GETRESULT(Data),BorderLayout.EAST);
    	PanelOne.setBackground(Color.BLACK);
    	PanelOne.setSize(1550,600);
    	
    	final JPanel ResultPanel = GETRESULT(Data);
    	ResultPanel.setLayout(new GridBagLayout());
    	
    	ButtonStart.addActionListener(new ActionListener(){  
    		 public void actionPerformed(ActionEvent e){
    			 System.out.println( Window.getSize());
    			 PanelOne.removeAll();
    			 //ResultPanel.
    			 PanelOne.repaint();
    			 Window.getContentPane().remove(2);
    			 try {
    				 Window.add(GETRESULT(GetData(TickerName.getText())),BorderLayout.EAST);
    				 PanelOne.add(MakeChart(GetData(TickerName.getText())));
    			 } catch (Exception e1) {
    				 e1.printStackTrace();
    			 }
            	 // The chart Pannel Doesn't repaint corretly unless the window is resized
            	 // the window is resized to update the window, a better way is needed
    		     
    			 Window.setSize(1,1);
    			 Window.setSize(1550,838);
    	    	 //Window.pack();
    		 }
    	});    	
    	
    	Window.setSize(1550,838);
    	return Window;
    }
    
    static JPanel GETRESULT( ArrayList<ArrayList<String>> Data) {
    	
    	JPanel ResultPanel = new JPanel();
    	ResultPanel.setLayout(new GridBagLayout());
    	
    	Analisys Calc = new Analisys();
    	
    	GridBagConstraints Zoning = new GridBagConstraints();
    	Zoning.insets = new Insets(10,10,10,10);
    	Zoning.gridx = 0;
        Zoning.gridy = 1;
    	JLabel InfoText = new JLabel("-                                                                         - PATTERNS FOUND -                                                                         -");
    	ResultPanel.add(InfoText, Zoning);
    	JLabel DDOJI = Calc.DetectDDoji(Data, new JLabel());
    	Zoning.gridx = 0;
        Zoning.gridy = 2;
        ResultPanel.add(DDOJI,Zoning);
    	//JLabel LLDOJI = DetectLLDoji(Data,new JLabel());
    	JLabel LLDOJI = Calc.DetectLLDoji(Data,new JLabel());
    	Zoning.gridx = 0;
        Zoning.gridy = 3;
        ResultPanel.add(LLDOJI,Zoning);
    	JLabel GSDOJI = Calc.DetectGSDoji(Data, new JLabel());
    	Zoning.gridx = 0;
        Zoning.gridy = 4;
        ResultPanel.add(GSDOJI,Zoning);
    	JLabel BEDOJI = Calc.DetectBearishEngulfing(Data, new JLabel());
    	Zoning.gridx = 0;
        Zoning.gridy = 5;
        ResultPanel.add(BEDOJI,Zoning);
    	JLabel BUEDOJI = Calc.DetectBullishEngulfing(Data, new JLabel());
    	Zoning.gridx = 0;
        Zoning.gridy = 6;
        ResultPanel.add(BUEDOJI,Zoning);
    	JLabel MSD = Calc.DetectMorningStar(Data, new JLabel());
    	Zoning.gridx = 0;
        Zoning.gridy = 7;
        ResultPanel.add(MSD,Zoning);
    	JLabel ESD = Calc.DetectEveningStar(Data, new JLabel());
    	Zoning.gridx = 0;
        Zoning.gridy = 9;
        ResultPanel.add(ESD,Zoning);
        JLabel BHD = Calc.BullishHarami(Data, new JLabel());
    	Zoning.gridx = 0;
        Zoning.gridy = 10;
        ResultPanel.add(BHD,Zoning);
        JLabel BSHD = Calc.BearishHarami(Data, new JLabel());
    	Zoning.gridx = 0;
        Zoning.gridy = 11;
        ResultPanel.add(BSHD,Zoning);
    	
    	return ResultPanel;
    }
    
    //Makes chart using the data from yahoo finance
    static ChartPanel MakeChart(ArrayList<ArrayList<String>> Data) throws Exception{
    	
    	//JPanel ChartPanel = new JPanel();
    	
    	Double Min = 999999.9999;
    	Double Max = -999999.9999;
    	
    	DateFormat df = new SimpleDateFormat("y-M-d");
    	
    	DateAxis    domainAxis       = new DateAxis("Date");
    	
        NumberAxis  rangeAxis        = new NumberAxis("Price");

        CandlestickRenderer renderer = new CandlestickRenderer();
        
        OHLCDataItem[] data;
        
        DefaultOHLCDataset result = null;
        
        List<OHLCDataItem> dataItems = new ArrayList<OHLCDataItem>();
        
        
        
    	for(int i = 0;i<=4;i++) {
    		System.out.print(Data.get(0).get(i)+  " ");
    		System.out.print(Data.get(1).get(i)+ " ");
    		System.out.print(Data.get(2).get(i)+ " ");
    		System.out.print(Data.get(3).get(i)+ " ");
    		System.out.println(Data.get(4).get(i)+ " ");
    		
    		dataItems.add(new OHLCDataItem(
    				df.parse(Data.get(0).get(i)),
    				Double.parseDouble(Data.get(1).get(i)),
    				Double.parseDouble(Data.get(2).get(i)),
    				Double.parseDouble(Data.get(3).get(i)),
    				Double.parseDouble(Data.get(4).get(i)),
    				1
    				));
    		if(Double.parseDouble(Data.get(1).get(i))> Max) {
    			Max = Double.parseDouble(Data.get(1).get(i));
    		}
    		if(Double.parseDouble(Data.get(2).get(i))> Max) {
    			Max = Double.parseDouble(Data.get(2).get(i));
    		}
    		if(Double.parseDouble(Data.get(4).get(i))> Max) {
    			Max = Double.parseDouble(Data.get(4).get(i));
    		}
    		
    		if(Double.parseDouble(Data.get(1).get(i)) < Min) {
    			Min = Double.parseDouble(Data.get(1).get(i));
    		}
    		if(Double.parseDouble(Data.get(3).get(i))< Min) {
    			Min = Double.parseDouble(Data.get(3).get(i));
    		}
    		if(Double.parseDouble(Data.get(4).get(i))< Min) {
    			Min = Double.parseDouble(Data.get(4).get(i));
    		}
    	}
        

        //Convert the list into an array
        data = dataItems.toArray(new OHLCDataItem[dataItems.size()]);
        

        XYDataset   dataset  = new DefaultOHLCDataset("APPL",data) ;
        
        
        XYPlot mainPlot = new XYPlot(dataset, domainAxis, rangeAxis, renderer);
        
        renderer.setSeriesPaint(0, Color.BLACK);
        renderer.setDrawVolume(false);

        //Now create the chart and chart panel
        JFreeChart chart = new JFreeChart("AAPL", null, mainPlot, false);
        ChartPanel ChartContainer = new ChartPanel(chart);
        ChartContainer.setPreferredSize(new Dimension(450, 790));

        rangeAxis.setRange(Min-1,Max+1);
        domainAxis.setRange(df.parse(String.valueOf(java.time.LocalDate.now().minusDays(9))),df.parse(String.valueOf(java.time.LocalDate.now().plusDays(1))));
        
        //ChartPanel.add(chartContainer);
    	
    	return ChartContainer;
    }

}
