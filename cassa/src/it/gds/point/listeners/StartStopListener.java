package it.gds.point.listeners;

import java.io.InputStream;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.Timer;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import it.gds.point.timer.OrdiniTavoli;
import it.gds.point.utils.Config;
import it.gds.point.utils.ReadProperty;

public class StartStopListener implements ServletContextListener {
	Timer timer = new Timer();

	@Override
	public void contextInitialized(ServletContextEvent event) {
		try {
			ServletContext sc = event.getServletContext();

			String realpath = "";
			realpath = event.getServletContext().getRealPath("/");
			new ReadProperty(realpath, event.getServletContext().getServletContextName()).getInstance();

			System.out.println("Starting Java Application >>>>>>>>>>>>>>>>>>> "
					+ event.getServletContext().getServletContextName());
			Properties p = new Properties();
			InputStream file = StartStopListener.class.getClassLoader().getResourceAsStream("package.properties");
			p.load(file);
			event.getServletContext().setAttribute("PROPERTIES", p);
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Application successfully started!");

			if (Config.isDebug() == false) {
				int year = Calendar.getInstance().get(Calendar.YEAR);
				int month = Calendar.getInstance().get(Calendar.MONTH);
				int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
				int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
				int min = Calendar.getInstance().get(Calendar.MINUTE);
				int period = 30;
				// imposta data e ora di inizio in un oggetto calendar
				Calendar cal = new GregorianCalendar(year, month, day, hour, min);
				timer.scheduleAtFixedRate(new OrdiniTavoli(sc), cal.getTime(), 1000 * period);
				System.out.println(" ===> Aggiunto controllo pianificato");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		try {
			System.out.println("Stopping Java Application >>>>>>>>>>>>>>>>>>> "
					+ event.getServletContext().getServletContextName());
			Enumeration<Driver> drivers = DriverManager.getDrivers();
			while (drivers.hasMoreElements()) {
				Driver driver = drivers.nextElement();
				DriverManager.deregisterDriver(driver);
			}
			// hibernateUtil.shutdown();
			System.out.println("Application successfully stopped!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}