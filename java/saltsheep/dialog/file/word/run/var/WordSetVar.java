package saltsheep.dialog.file.word.run.var;

import java.util.Map;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import saltsheep.dialog.file.IRunAddable;
import saltsheep.dialog.file.IRunAddable.ExtraRun;
import saltsheep.dialog.file.word.AFileWordRunHandler;
import saltsheep.lib.nbt.SheepNBT;
import saltsheep.lib.player.PlayerHelper;

public class WordSetVar extends AFileWordRunHandler {

	@Override
	public String methodWord() {
		return "setVar";
	}

	@Override
	public String[] paramWords() {
		return new String[] {"var","value"};
	}

	@Override
	public ExtraRun getRun(IRunAddable creator, Map<String, String> params) {
		String var = params.get("var");
		double value = Double.valueOf(params.get("value"));
		return new SetVar(var,value);
	}
	
	public static class SetVar implements ExtraRun{
		private final String var;
		private final double value;
		public SetVar(String var,double value) {this.var = var;this.value = value;}
		@Override
		public boolean run(EntityPlayerMP player, EntityLivingBase hostingEntity) {
			SheepNBT nbt = PlayerHelper.getSheepData(player).getOrCreateCompound("vars");
			nbt.setTag(var, (int)value);
			return true;
		}
	}

}
