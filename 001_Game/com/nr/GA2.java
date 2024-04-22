package com.nr;

import java.io.PrintStream;

import ga2.client.ui.LanPlayWindow;
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

		// KxrFile.main(new String[] { "-extract", "bin/1.kxr", "aoao" });
		// KxrFile.main(new String[] { "-extract", "bin/2.kxr", "aoao" });
		// KxrFile.main(new String[] { "-extract", "bin/3.kxr", "aoao" });
		// KxrFile.main(new String[] { "-extract", "bin/4.kxr", "aoao" });
		// KxrFile.main(new String[] { "-extract", "bin/5.kxr", "aoao" });

		// KxrFile.main(new String[] { "-extract", "bin/4.kxr", "aoao" });
		// KxrFile.main(new String[] { "-extract", "bin/5.kxr", "aoao" });
	}
}