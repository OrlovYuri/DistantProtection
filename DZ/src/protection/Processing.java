package protection;

public class Processing {
	double[] buffer = new double[80];
	double sum;
	int counter;
	
	double ax;
	double ay;
	double kf = Math.sqrt(2)/80;
	double sumx;
	double sumy;

	public Processing() {
		sum = 0;
		counter = 0;
		sumx = 0;
		sumy = 0;
		for (int i = 0; i < 80; i++) {
			buffer[i] = 0;
		}
	}

	public double getRMS(double x) {
		sum = sum + x*x - buffer[counter]*buffer[counter];
		buffer[counter] = x;
		counter++;
		if (counter == 80)
			counter = 0;
		return Math.sqrt(sum/80);
	}

	public void fourier(double x, int harm) {
		sumx = sumx + Math.sin(2*Math.PI*harm*counter/80)*(x - buffer[counter]);
		sumy = sumy + Math.cos(2*Math.PI*harm*counter/80)*(x - buffer[counter]);
		ax = sumx*kf;
		ay = sumy*kf;
		buffer[counter] = x;
		counter++;
		if (counter == 80)
			counter = 0;
	}
	
	public static double getR(double Ux, double Uy, double Ix, double Iy) {
		return (Ux*Ix + Uy*Iy)/(Ix*Ix + Iy*Iy);
	}	
	
	public static double getX(double Ux, double Uy, double Ix, double Iy) {
		return (Uy*Ix - Ux*Iy)/(Ix*Ix + Iy*Iy);
	}
	
	public static double getPhPh(double a, double b) {
		return (a - b);
	}
	
}
