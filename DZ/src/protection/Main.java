package protection;
public class Main {
	private static double Rab, Xab, Rbc, Xbc, Rca, Xca;
	public static void main(String[] args) {
		//Создание окна, рисование полигональной ХС:
		TripDrawing td = new TripDrawing();
		//Создание экземпляров класса Processing:
		Processing prUab = new Processing();
		Processing prUbc = new Processing();
		Processing prUca = new Processing();
		Processing prIab = new Processing();
		Processing prIbc = new Processing();
		Processing prIca = new Processing();
		//Получение данных напряжений и токов:
		DataGet dg = new DataGet();
		for (int i = 0; i < 4000; i++) {
			//Расчет ортогональных составляющих:
			prUab.fourier(Processing.getPhPh(dg.x1[i], dg.x2[i]), 1);
			prUbc.fourier(Processing.getPhPh(dg.x2[i], dg.x3[i]), 1);
			prUca.fourier(Processing.getPhPh(dg.x3[i], dg.x1[i]), 1);
			prIab.fourier(Processing.getPhPh(dg.x4[i], dg.x5[i]), 1);
			prIbc.fourier(Processing.getPhPh(dg.x5[i], dg.x6[i]), 1);
			prIca.fourier(Processing.getPhPh(dg.x6[i], dg.x4[i]), 1);
			//Расчет активных и реактивных сопротивлений:
			Rab = -Processing.getR(prUab.ax, prUab.ay, prIab.ax, prIab.ay);
			Xab = Processing.getX(prUab.ax, prUab.ay, prIab.ax, prIab.ay);
			Rbc = -Processing.getR(prUbc.ax, prUbc.ay, prIbc.ax, prIbc.ay);
			Xbc = Processing.getX(prUbc.ax, prUbc.ay, prIbc.ax, prIbc.ay);
			Rca = -Processing.getR(prUca.ax, prUca.ay, prIca.ax, prIca.ay);
			Xca = Processing.getX(prUca.ax, prUca.ay, prIca.ax, prIca.ay);		
			//Проверка срабатывания защиты:
			if (Protection.tripPDIS(Rab, Xab) || Protection.tripPDIS(Rbc, Xbc) || Protection.tripPDIS(Rca, Xca))
				System.out.println(i + ") Срабатывание защиты");	
			//Визуализация работы защиты:
			td.setData(Rab, Xab, Rbc, Xbc, Rca, Xca);
		}
	}
}