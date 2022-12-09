package saltsheep.dialog.file.dialog;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import saltsheep.dialog.base.FDialogHandler;
import saltsheep.dialog.file.IRequireAddable;
import saltsheep.dialog.file.IRunAddable;
import saltsheep.dialog.network.DataPlayerLock;

public class FileDialogChainNONL extends FileDialogChainNO {
	
	public FileDialogChainNONL(EntityPlayerMP player,@Nullable EntityLivingBase hostingEntity,String dialogID,String[] dialogTexts,long[] dialogTimes,List<IRequireAddable.Requirement> requires,List<IRunAddable.ExtraRun>[] runs) {
		super(player, hostingEntity, dialogID, dialogTexts, dialogTimes, requires, runs);
	}
	
	protected void lockPlayer() {
		DataPlayerLock.unlockByServer((EntityPlayerMP) this.player);
		FDialogHandler.playersRunningDialog.put(this.player.getName(), this);
	}

}
