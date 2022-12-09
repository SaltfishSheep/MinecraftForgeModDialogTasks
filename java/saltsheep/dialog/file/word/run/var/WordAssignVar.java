package saltsheep.dialog.file.word.run.var;

import java.util.Map;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import saltsheep.dialog.file.IRunAddable;
import saltsheep.dialog.file.IRunAddable.ExtraRun;
import saltsheep.dialog.file.word.AFileWordRunHandler;
import saltsheep.lib.nbt.SheepNBT;
import saltsheep.lib.player.PlayerHelper;

public class WordAssignVar extends AFileWordRunHandler {

	@Override
	public String methodWord() {
		return "assignVar";
	}

	@Override
	public String[] paramWords() {
		return new String[] {"var","value","max","min"};
	}

	@Override
	public ExtraRun getRun(IRunAddable creator, Map<String, String> params) {
		String var = params.get("var");
		String value = params.get("value");
		double max = params.containsKey("max")? Double.valueOf(params.get("max")):Integer.MAX_VALUE;
		double min = params.containsKey("min")? Double.valueOf(params.get("min")):Integer.MIN_VALUE;
		return new AssignVar(var,value,max,min);
	}
	
	public static class AssignVar implements ExtraRun{
		private final String var;
		private final String value;
		private final double max;
		private final double min;
		public AssignVar(String var,String value,double max,double min) {
			this.var = var;
			this.value = value;
			this.max = max;
			this.min = min;
		}
		@Override
		public boolean run(EntityPlayerMP player, EntityLivingBase hostingEntity) {
			SheepNBT nbt = PlayerHelper.getSheepData(player).getOrCreateCompound("vars");
			String exp = handlePlaceholder(player,hostingEntity,this.value);
			double v = get(player, hostingEntity, exp);
			nbt.setTag(var, (double)Math.min(this.max,Math.max(this.min,v)));
			return true;
		}
		public static String handlePlaceholder(EntityPlayerMP player, EntityLivingBase hostingEntity, String exp) {
			return PlaceholderHandler.handler(player, hostingEntity, exp);
		}
		public static double get(EntityPlayerMP player, EntityLivingBase hostingEntity, String exp) {
			return saltsheep.lib.math.MathExpression.get(exp);
		}
	}

}
