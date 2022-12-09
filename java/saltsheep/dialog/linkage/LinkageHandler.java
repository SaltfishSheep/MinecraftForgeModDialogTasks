package saltsheep.dialog.linkage;

public class LinkageHandler {

	public static void handle() {
		if(hasClass("noppes.npcs.CustomNpcs"))
			saltsheep.dialog.linkage.cnpc.Initer.init();
	}
	
	public static boolean hasClass(String className){
		try {
			Thread.currentThread().getContextClassLoader().loadClass(className);
			return true;
		} catch (ClassNotFoundException e) {
			return false;
		}
	}
	
}
