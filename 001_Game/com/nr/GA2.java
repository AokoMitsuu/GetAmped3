package com.nr;

import java.io.ByteArrayInputStream;
import java.io.PrintStream;

import ga2.client.ui.LanPlayWindow;
import kotori.io.ExternalizerInputStream;
import kotori.io.KxrFile;

public class GA2 {
	public static void main(String[] args) throws Exception {
		PrintStream stream = new PrintStream("logs.txt");
		// System.setOut(stream);
		System.setErr(stream);

		String[] base = new String[] { "-developer", "-kxrres", "-kxrgs" };
		args = com.nr.Utils.mergeStringArray(base, args);

		for (int l = 0; l < args.length; ++l) {
			if (args[l].equals("-port")) {
				LanPlayWindow.PortBase = Integer.parseInt(args[l + 1]);
			}
		}

		ga2.client.GetAmped2.main(args);
		// KxrFile kxr = KxrFile.open("bin/cache/clientsetting", "cscspwpw", "rw");
		// KxrFile.Entry e = kxr.getRoot().getEntry("cs");
		// int size = e.size();
		// byte[] b = new byte[size];
		// e.getData(b);
		// System.err.println("start");
		// System.err.println(com.nr.Utils.toBinaryString(b));
		// System.err.println("end");
	}
}