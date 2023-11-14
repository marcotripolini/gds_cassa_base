package it.gds.point.utils;

import java.awt.print.PrinterJob;
import java.nio.charset.Charset;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.SimpleDoc;

public class MyPrint {
	private String data;
	private String printerName;
	private PrintService pService = null;

	public MyPrint(String printerName) {
		this.printerName = printerName;

		PrintService[] pServices = PrinterJob.lookupPrintServices();
		for(int i = 0; i < pServices.length; i++) {
			if(pServices[i].getName().toLowerCase().equals(this.printerName.toLowerCase())) {
				this.pService = pServices[i];
			}
		}
	}

	public void setData(String data, String encode) {
		try {
			if(encode != null)
				this.data = new String(data.getBytes(encode), Charset.forName(encode));
			else
				this.data = data;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void print() {
		try {
			if(this.pService != null) {
				DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
				DocPrintJob pJob = this.pService.createPrintJob();
				Doc doc = new SimpleDoc(this.data.getBytes(), flavor, null);
				pJob.print(doc, null);
			} else {
				System.err.println("No PrintService selected.");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void replaceSpecialChars() {
		try {
			this.data = this.data.replaceAll("�", "a`");
			this.data = this.data.replaceAll("�", "e`");
			this.data = this.data.replaceAll("�", "e`");
			this.data = this.data.replaceAll("�", "i`");
			this.data = this.data.replaceAll("�", "o`");
			this.data = this.data.replaceAll("�", "u`");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void replaceASCIISpecialChars() {
		try {
			char[] charArray = this.data.toCharArray();
			for(int i = 0; i < charArray.length; i++) {
				int charCode = (int)charArray[i];
				if(charCode > 127) {
					switch(charCode) {
						case 133: case 160: // a
							charArray[i] = 'a';
							//charArray = insertAfterIndex(charArray, i, '\'');
							break;
						case 130: case 138: // e
							charArray[i] = 'e';
							//charArray = insertAfterIndex(charArray, i, '\'');
							break;
						case 141: case 161: // i
							charArray[i] = 'i';
							//charArray = insertAfterIndex(charArray, i, '\'');
							break;
						case 149: case 162: // o
							charArray[i] = 'o';
							//charArray = insertAfterIndex(charArray, i, '\'');
							break;
						case 151: case 163: // u
							charArray[i] = 'u';
							//charArray = insertAfterIndex(charArray, i, '\'');
							break;
						default:
							charArray[i] = ' ';
							break;
					}
				}
			}
			this.data = String.valueOf(charArray);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("null")
	public char[] insertAfterIndex(char[] charArray, int index, char c) {
		char[] shiftedArray = null;
		try {
			for(int i = 0; i <= index; i++) {
				shiftedArray[i] = charArray[i];
			}
			shiftedArray[index + 1] = c;
			for(int i = index + 1; i < charArray.length; i++) {
				shiftedArray[i + 1] = charArray[i];
			}

		} catch(Exception e) {
			e.printStackTrace();
		}
		return shiftedArray;
	}

	public String getData() {
		return this.data;
	}

}
