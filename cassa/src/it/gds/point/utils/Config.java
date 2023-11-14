package it.gds.point.utils;

import it.gds.point.utils.ReadProperty;

public class Config {

	public static Boolean getDebug() {
		ReadProperty r = ReadProperty.getInstance();
		boolean debug = Boolean.parseBoolean(r.getProperty("debug"));
		return debug;
	}

	public static Boolean isDebug() {
		ReadProperty r = ReadProperty.getInstance();
		boolean debug = Boolean.parseBoolean(r.getProperty("debug"));
		return debug;
	}

	public static String getRealPath() {
		String directory = ReadProperty.getInstance().getRealPath();
		return directory;
	}

	public static String getJDBCCONNECTION() {
		ReadProperty r = ReadProperty.getInstance();
		String result = "";
		if (Config.isDebug() == true) {
			result = r.getProperty("debug_jdbc_connection");
		} else if (Config.isDebug() == false) {
			result = r.getProperty("release_jdbc_connection");
		}
		return result;
	}

	public static String getJDBCUSER() {
		ReadProperty r = ReadProperty.getInstance();
		String result = "";
		if (Config.isDebug() == true) {
			result = r.getProperty("debug_jdbc_user");
		} else if (Config.isDebug() == false) {
			result = r.getProperty("release_jdbc_user");
		}
		return result;
	}

	public static String getJDBPASSWORD() {
		ReadProperty r = ReadProperty.getInstance();
		String result = "";
		if (Config.isDebug() == true) {
			result = r.getProperty("debug_jdbc_password");
		} else if (Config.isDebug() == false) {
			result = r.getProperty("release_jdbc_password");
		}
		return result;
	}

	public static String getNEWUSERLEVEL() {
		ReadProperty r = ReadProperty.getInstance();
		String result = "";
		if (Config.isDebug() == true) {
			result = r.getProperty("debug_new_user_level");
		} else if (Config.isDebug() == false) {
			result = r.getProperty("release_new_user_level");
		}
		return result;
	}

	public static String getNEWUSERNEXTLEVEL() {
		ReadProperty r = ReadProperty.getInstance();
		String result = "";
		if (Config.isDebug() == true) {
			result = r.getProperty("debug_new_user_next_level");
		} else if (Config.isDebug() == false) {
			result = r.getProperty("release_new_user_next_level");
		}
		return result;
	}

	public static String getSERVICEPRINTER() {
		ReadProperty r = ReadProperty.getInstance();
		String result = "";
		if (Config.isDebug() == true) {
			result = r.getProperty("debug_serviceprinter_name");
		} else if (Config.isDebug() == false) {
			result = r.getProperty("release_serviceprinter_name");
		}
		return result.trim();
	}

	public static String getPOSPRINTER() {
		ReadProperty r = ReadProperty.getInstance();
		String result = "";
		if (Config.isDebug() == true) {
			result = r.getProperty("debug_posprinter_ip");
		} else if (Config.isDebug() == false) {
			result = r.getProperty("release_posprinter_ip");
		}
		return result.trim();
	}

	public static String getSCONTRINOPATH() {
		ReadProperty r = ReadProperty.getInstance();
		String result = "";
		if (Config.isDebug() == true) {
			result = r.getProperty("debug_scontrino_path");
		} else if (Config.isDebug() == false) {
			result = r.getProperty("release_scontrino_path");
		}
		return result.trim();
	}

	public static String getNEGOZIO() {
		ReadProperty r = ReadProperty.getInstance();
		String result = "";
		if (Config.isDebug() == true) {
			result = r.getProperty("debug_negozio");
		} else if (Config.isDebug() == false) {
			result = r.getProperty("release_negozio");
		}
		return result;
	}

}