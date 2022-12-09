package saltsheep.dialog.file.word.require;

import java.util.Map;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import saltsheep.dialog.file.IRequireAddable;
import saltsheep.dialog.file.IRequireAddable.Requirement;
import saltsheep.dialog.file.word.AFileWordRequireHandler;
import saltsheep.lib.nbt.SheepNBT;
import saltsheep.lib.player.PlayerHelper;

public class WordVarInRange extends AFileWordRequireHandler {

	@Override
	public String methodWord() {
		return "varInRange";
	}

	@Override
	public String[] paramWords() {
		return new String[] {"var","max","min"};
	}

	@Override
	public Requirement getRequire(IRequireAddable addtor, Map<String, String> params) {
		int max = params.containsKey("max")? Integer.valueOf(params.get("max")):Integer.MAX_VALUE;
		int min = params.containsKey("min")? Integer.valueOf(params.get("min")):Integer.MIN_VALUE;
		String var = params.get("var");
		return new VarInRange(var,max,min);
	}
	
	public static class VarInRange implements Requirement{
		
		private final String var;
		private final int max,min;
		public VarInRange(String var,int max,int min) {
			this.var = var;
			this.max = max;
			this.min = min;
		}
		@Override
		public boolean apply(EntityPlayerMP player, EntityLivingBase hostingEntity) {
			SheepNBT nbt = PlayerHelper.getSheepData(player).getOrCreateCompound("vars");
			double value = nbt.getDouble(var);
			return value>=min&&value<=max;
		}
	}

}
