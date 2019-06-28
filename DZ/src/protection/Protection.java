package protection;
public class Protection {
	
	private static double Xtrip = 100;
	private static double Rtrip = 70;
	
	private static double fi1 = 70;
	private static double fi2 = -15;
	private static double fi3 = 115;
	
	public static boolean tripPDIS(double R, double X) {
		return (X<=Xtrip)&&(X>=R*Math.tan(Math.toRadians(fi2)))&&(X>=R*Math.tan(Math.toRadians(fi3)))&&(X>=(R-Rtrip)*Math.tan(Math.toRadians(fi1)));
	}

	public static double getXtrip() {
		return Xtrip;
	}

	public static double getRtrip() {
		return Rtrip;
	}

	public static double getFi1() {
		return fi1;
	}

	public static double getFi2() {
		return fi2;
	}

	public static double getFi3() {
		return fi3;
	}
	
	public static double getR1() {
		return (Xtrip/Math.tan(Math.toRadians(fi3)));
	}
	
	public static double getR2() {
		return (Rtrip*Math.tan(Math.toRadians(fi1))/(Math.tan(Math.toRadians(fi1))-Math.tan(Math.toRadians(fi2))));
	}
	
	public static double getX2() {
		return (getR2()*Math.atan(Math.toRadians(fi2)));
	}
	
	public static double getR3() {
		return (Rtrip + Xtrip/Math.tan(Math.toRadians(fi1)));
	}
}
