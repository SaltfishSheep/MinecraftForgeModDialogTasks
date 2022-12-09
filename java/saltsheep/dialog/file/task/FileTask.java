package saltsheep.dialog.file.task;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.entity.player.EntityPlayerMP;
import saltsheep.dialog.base.ATask;
import saltsheep.dialog.file.IRequireAddable;
import saltsheep.dialog.file.IRunAddable;;

public class FileTask extends ATask implements IRequireAddable, IRunAddable {

	public List<IRequireAddable.Requirement> finishRequires = Lists.newLinkedList();
	public List<IRunAddable.ExtraRun> startRuns = Lists.newLinkedList();
	public List<IRunAddable.ExtraRun> finishRuns = Lists.newLinkedList();
	
	public String taskID = null;
	
	@Override
	public String getID() {
		return this.taskID;
	}

	@Override
	protected void startRun(EntityPlayerMP player) {
		for(ExtraRun run:this.startRuns)
			run.run(player, null);
	}

	@Override
	protected void finishRun(EntityPlayerMP player) {
		for(ExtraRun run:this.finishRuns)
			run.run(player, null);
	}

	@Override
	public boolean canFinish(EntityPlayerMP player) {
		for(Requirement require:this.finishRequires)
			if(!require.apply(player, null))
				return false;
		return true;
	}

	@Override
	public void addRun(ExtraRun add, Object... extraParams) {
		if(extraParams.length!=1||!(extraParams[0] instanceof String))
			return;
		String runTime = (String) extraParams[0];
		if(runTime.equalsIgnoreCase("start"))
			this.startRuns.add(add);
		else if(runTime.equalsIgnoreCase("finish"))
			this.finishRuns.add(add);
	}

	@Override
	public void addRequire(Requirement add, Object... extraParams) {
		this.finishRequires.add(add);
	}
	
	public boolean isEmpty() {
		return this.taskID==null;
	}

}
