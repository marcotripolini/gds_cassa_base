package it.gds.point.actions;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Properties;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class ActionOperations extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String cmd;

	public String execute() {
		try {
			switch(getCmd()) {

				case "day":
					System.out.println(getCmd());
					Properties prop = (Properties)ServletActionContext.getRequest().getServletContext().getAttribute("PROPERTIES");

					BufferedWriter writer = new BufferedWriter(
							new OutputStreamWriter(new FileOutputStream(prop.getProperty("scontrino_path") + "scontrino.inp"), "utf-8"));

					String fiscale = prop.getProperty("print_1_ip") + "\n=C2\n=C500/$1";

					writer.write(fiscale);
					writer.close();
					Process pp = Runtime.getRuntime().exec("cmd /c start \"\" \"" + prop.getProperty("scontrino_path") + "m.lnk\"");
					pp.waitFor();

					break;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return NONE;
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

}
