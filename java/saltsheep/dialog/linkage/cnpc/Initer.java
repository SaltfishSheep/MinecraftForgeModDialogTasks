package saltsheep.dialog.linkage.cnpc;

import saltsheep.dialog.DialogTasks;

public class Initer {

	public static void init() {
		DialogTasks.info("Register the CNPC module.");
		saltsheep.dialog.base.Initer.registerAuto(new WordFinishedCNPCQuest());
		saltsheep.dialog.base.Initer.registerAuto(new WordStartingCNPCQuest());
		saltsheep.dialog.base.Initer.registerAuto(new WordStartCNPCQuest());
		saltsheep.dialog.base.Initer.registerAuto(new WordStopCNPCQuest());
	}
	
}
