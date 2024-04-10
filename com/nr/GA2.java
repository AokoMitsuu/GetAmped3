package com.nr;

import ga2.client.ui.LanPlayWindow;

public class GA2 {
	public static void main(String[] args) throws Exception {
		String[] base = new String[] { "-developer", "-kxrres", "-kxrgs" };
		args = com.nr.Utils.mergeStringArray(base, args);

		for (int l = 0; l < args.length; ++l) {
			if (args[l].equals("-port")) {
				LanPlayWindow.PortBase = Integer.parseInt(args[l + 1]);
			}
		}

		ga2.client.GetAmped2.main(args);
	}
}