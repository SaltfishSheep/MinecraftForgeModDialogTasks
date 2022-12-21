package saltsheep.dialog.linkage.cnpc;

import java.util.Map;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import noppes.npcs.NoppesUtilServer;
import noppes.npcs.api.constants.RoleType;
import noppes.npcs.constants.EnumGuiType;
import noppes.npcs.entity.EntityNPCInterface;
import saltsheep.dialog.file.IRunAddable;
import saltsheep.dialog.file.IRunAddable.ExtraRun;
import saltsheep.dialog.file.word.AFileWordRunHandler;

public class WordOpenCNPCShop extends AFileWordRunHandler {

	@Override
	public String methodWord() {
		return "openCNPCShop";
	}

	@Override
	public String[] paramWords() {
		return new String[] {};
	}

	@Override
	public ExtraRun getRun(IRunAddable creator, Map<String, String> params) {
		return new OpenCNPCShop();
	}
	
	public static class OpenCNPCShop implements ExtraRun{
		@Override
		public boolean run(EntityPlayerMP player, EntityLivingBase hostingEntity) {
			if(hostingEntity==null||!(hostingEntity instanceof EntityNPCInterface))
				return false;
			EntityNPCInterface npc = (EntityNPCInterface)hostingEntity;
			if(npc.roleInterface.getType()!=RoleType.TRADER)
				return false;
			NoppesUtilServer.setEditingNpc(player, (EntityNPCInterface) hostingEntity);
			NoppesUtilServer.sendOpenGui(player, EnumGuiType.PlayerTrader, npc);
			return true;
		}
	}

}
