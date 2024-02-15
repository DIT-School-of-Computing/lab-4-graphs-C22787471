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
		size(1200, 600);

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
		colorMode(HSB);
		background(0);
		
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
		float w = width / (float)months.length;
		for(int i = 0 ; i < months.length ;  i ++)
		{
			float x = map1(i, 0, months.length, 0, width);
			rect(x, height, w, -rainfall[i]);
		}
		
		switch (mode) {
			case 0:
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
			case 1:
			stroke(0);
			line(50, height - 50, width - 50, height - 50);  // X-axis
			line(50, height - 50, 50, 50);    
			noFill();
        	beginShape();
			float xSpacing = width / (float)(rainfall.length + 1);
        	float ySpacing = height / 120.0f;
        	for (int i = 0; i < rainfall.length; i++) {
            	float x = 50 + xSpacing * (i + 1);
            	float y = height - 50 - ySpacing * rainfall[i];
            	vertex(x, y);
            	ellipse(x, y, 8, 8);  // Draw data points
			}
        	endShape();    
		}
	}
}
