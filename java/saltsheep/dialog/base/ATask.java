package saltsheep.dialog.base;

import net.minecraft.entity.player.EntityPlayerMP;
import saltsheep.lib.player.PlayerHelper;

public abstract class ATask {
	
	public abstract String getID();
	
	public void start(EntityPlayerMP player) {
		if(this.isStarting(player))
			return;
		this.markStart(player);
		this.startRun(player);
	}
	
	protected abstract void startRun(EntityPlayerMP player);
	
	public void finish(EntityPlayerMP player) {
		if(!this.isStarting(player))
			return;
		this.markFinish(player);
		this.finishRun(player);
	}
	
	protected abstract void finishRun(EntityPlayerMP player);
	
	public abstract boolean canFinish(EntityPlayerMP player);
	
	public void checkFinish(EntityPlayerMP player) {
		if(this.isStarting(player)&&this.canFinish(player))
			this.finish(player);
	}
	
	private void markStart(EntityPlayerMP player) {
		if(FTaskHandler.addRunning(player, this)) {
			this.setLastStartTimes(player, System.currentTimeMillis());
			this.setStartTimes(player, this.getStartTimes(player)+1);
		}
	}
	
	//*if you ignore finish to use it, it will force to end the task without finish prize.
	private void markFinish(EntityPlayerMP player) {
		if(FTaskHandler.removeRunning(player, this)) {
			this.setLastFinishTimes(player, System.currentTimeMillis());
			this.setFinishTimes(player, this.getFinishTimes(player)+1);
		}
	}
	
	public int getStartTimes(EntityPlayerMP player) {
		return PlayerHelper.getSheepData(player).getOrCreateCompound("tasks").getOrCreateCompound(this.getID()).getInteger("startTimes");
	}
	
	public int getFinishTimes(EntityPlayerMP player) {
		return PlayerHelper.getSheepData(player).getOrCreateCompound("tasks").getOrCreateCompound(this.getID()).getInteger("finishTimes");
	}
	
	public void setStartTimes(EntityPlayerMP player, int value) {
		PlayerHelper.getSheepData(player).getOrCreateCompound("tasks").getOrCreateCompound(this.getID()).setTag("startTimes", (int)value);
	}
	
	public void setFinishTimes(EntityPlayerMP player, int value) {
		PlayerHelper.getSheepData(player).getOrCreateCompound("tasks").getOrCreateCompound(this.getID()).setTag("finishTimes", (int)value);
	}
	
	public long getLastStartTime(EntityPlayerMP player) {
		return PlayerHelper.getSheepData(player).getOrCreateCompound("tasks").getOrCreateCompound(this.getID()).getLong("lastStartTime");
	}
	
	public long getLastFinishTime(EntityPlayerMP player) {
		return PlayerHelper.getSheepData(player).getOrCreateCompound("tasks").getOrCreateCompound(this.getID()).getLong("lastFinishTime");
	}
	
	public void setLastStartTimes(EntityPlayerMP player, long value) {
		PlayerHelper.getSheepData(player).getOrCreateCompound("tasks").getOrCreateCompound(this.getID()).setTag("lastStartTime", (long)value);
	}
	
	public void setLastFinishTimes(EntityPlayerMP player, long value) {
		PlayerHelper.getSheepData(player).getOrCreateCompound("tasks").getOrCreateCompound(this.getID()).setTag("lastFinishTime", (long)value);
	}
	
	public boolean isStarting(EntityPlayerMP player) {
		int startTimes = this.getStartTimes(player);
		int finishTimes = this.getFinishTimes(player);
		long lastStartTime = this.getLastStartTime(player);
		long lastFinishTime = this.getLastFinishTime(player);
		return startTimes>finishTimes&&lastStartTime>=lastFinishTime&&FTaskHandler.isRunning(player, this);
	}
	
	public boolean isFinished(EntityPlayerMP player) {
		int finishTimes = this.getFinishTimes(player);
		long lastFinishTime = this.getLastFinishTime(player);
		return finishTimes>0&&lastFinishTime>0;
	}

}
