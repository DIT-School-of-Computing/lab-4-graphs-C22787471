package ie.tudublin;

import processing.core.PApplet;

public class Arrays extends PApplet
{
	String[] months = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};

	float[] rainfall = {200, 260, 300, 150, 100, 50, 10, 40, 67, 160, 400, 420};

	public float map1(float a, float b, float c, float d, float e)
	{
		float r1 = c -b;
		float r2 = e - d;

		float howFar = a - b;
		return d + (howFar / r1) * r2;
	}

	int mode = 0;

	void randomize()
	{
		for (int i = 0; i < rainfall.length; i++) {
			rainfall[i] = random(500);
		}
	}

	public void settings()
	{
		size(500, 500);

		String[] m1 = months;
		//months[0] = "XXX";
		print(m1[0]);
		for(int i = 0; i < months.length; i ++)
		{
			println("Month: " + months[i] + "\t" + rainfall[i]);
		}
		for (String s : m1) {
			println(s);
		}

		int minIndex = 0;
		for(int i= 0 ; i < rainfall.length ; i ++)
		{
			if (rainfall[i] < rainfall[minIndex])
			{
				minIndex = i;
			}
		}
		
		int maxIndex = 0;
		for(int i= 0 ; i < rainfall.length ; i ++)
		{
			if (rainfall[i] > rainfall[maxIndex])
			{
				maxIndex = i;
			}
		}

		println("The month with the minimum rainfall was " + months[minIndex] + " with " + rainfall[minIndex] + "rain");
		println("The month with the max rainfall was " + months[maxIndex] + " with " + rainfall[maxIndex] + "rain");
		
		
		float tot = 0;
		for(float f:rainfall)
		{
			tot += f;
		}

		float avg = tot / (float) rainfall.length;

		// a b-c d-e;
		println(map1(5, 0, 10, 0, 100));
		// 50

		println(map1(25, 20, 30, 200, 300));
		// 250

		println(map1(26, 25, 35, 0, 100));
		// 10 


	}

	public void setup() {
		//colorMode(HSB);
		background(255);
		
	}

	public void keyPressed() {
		if (key >= '0' && key <= '9') {
			mode = key - '0';
		}
		println(mode);
	}
	
	public void draw()
	{	
		background(0);

		switch (mode) {
			case 0:{
				barGraph();
				break;
			}
			case 1:{
				trendLine();
				break;
			}
			case 2:{
				pieChart();
				break;
			}
			default:{
				print("Invalid operator");
			}
		}		
	}


	private void barGraph() {
		background(0);
		strokeWeight(10);
		stroke(0);
		strokeCap(SQUARE);
		textAlign(BOTTOM);
		textSize(20);
		int numBars = months.length;
		int barWidth = width / numBars;
		for (int i = 0; i < numBars; i++) {
			float barHeight = map(rainfall[i], 0, max(rainfall), 0, height);
			float x = i * barWidth;
			float y = height - barHeight; 
			fill(0, 150, 255);
			rect(x, y, barWidth, barHeight);
			fill(0);
			text(months[i], x + barWidth/5, height);
		}

	}

	private void trendLine() {
		background(0);
		stroke(255, 255, 0);
		strokeWeight(2);
		line(50, 450, 450, 450);
		line(50, 50, 50, 450);

		int numPoints = months.length;
		int pointWidth = width / numPoints;
		for(int i = 0; i < numPoints; i++) {
			float points = map(rainfall[i], 0, max(rainfall), 0, height);
			float x = pointWidth * (i + 1);
			float y = height - rainfall[i];
			point(x, y);
			stroke(255);
			strokeWeight(2);
			line(x, y, pointWidth, numPoints);
			textSize(15);
			text(months[i], x + 50, 480, numPoints);
		}
	}

	private void pieChart() {
		background(255);
		noStroke();
		float diameter = 300;
		float lastAngle = 0;
		float sum = 0;
		
		for (float number : rainfall) {
            sum += number;
        }

		for (int i = 0; i < months.length; i++) {
			fill(220);
			float angle = map(rainfall[i], 0, sum, 0, TWO_PI);
			arc(width/2, height/2, diameter, diameter, lastAngle, lastAngle+radians(rainfall[i]));
			lastAngle += radians(rainfall[i]);
			
		  } 
	
	}

}

