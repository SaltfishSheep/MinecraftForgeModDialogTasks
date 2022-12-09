package saltsheep.dialog.file.dialog;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import saltsheep.dialog.base.ADialogChainBase;
import saltsheep.dialog.file.IRequireAddable;
import saltsheep.dialog.file.IRunAddable;

public class FileDialogCreator implements saltsheep.dialog.base.IDialogCreator,IRequireAddable,IRunAddable {

	public String dialogType;
	public EntityLivingBase hostingEntity;
	public String dialogID;
	public String[] dialogTexts;
	public long[] dialogTimes;
	public List<FileDialogInterface> nextChains = Lists.newLinkedList();
	public List<Requirement> requires = Lists.newLinkedList();
	public List<ExtraRun>[] runs;
	public boolean isMain = false;
	
	@Override
	public ADialogChainBase create(EntityPlayerMP player) {
		if(dialogType.equalsIgnoreCase("JS"))
			return new FileDialogChainJS(player, hostingEntity, dialogID, dialogTexts, dialogTimes, nextChains, requires, runs);
		else if(dialogType.equalsIgnoreCase("MO"))
			return new FileDialogChainMO(player, hostingEntity, dialogID, dialogTexts, dialogTimes, nextChains, requires, runs);
		else if(dialogType.equalsIgnoreCase("NO"))
			return new FileDialogChainNO(player, hostingEntity, dialogID, dialogTexts, dialogTimes, requires, runs);
		else if(dialogType.equalsIgnoreCase("NONL"))
			return new FileDialogChainNONL(player, hostingEntity, dialogID, dialogTexts, dialogTimes, requires, runs);
		else if(dialogType.equalsIgnoreCase("YON"))
			return new FileDialogChainYON(player, hostingEntity, dialogID, dialogTexts, dialogTimes, nextChains, requires, runs);
		return null;
	}
	
	public ADialogChainBase create(EntityPlayerMP player,@Nullable EntityLivingBase hostingEntity) {
		this.hostingEntity = hostingEntity;
		return this.create(player);
	}

	@Override
	public void addRun(ExtraRun add,Object...extraParams) {
		if(extraParams.length<1||!(extraParams[0] instanceof Integer))
			return;
		int line = (Integer)extraParams[0];
		int runIndex = line-1;
		if(this.runs[runIndex]==null)
			this.runs[runIndex] = Lists.newLinkedList();
		this.runs[runIndex].add(add);
	}

	@Override
	public void addRequire(Requirement add, Object... extraParams) {
		this.requires.add(add);
	}
	
	public boolean isEmpty() {
		return this.dialogType==null||this.dialogID==null||this.dialogTexts==null;
	}

}
