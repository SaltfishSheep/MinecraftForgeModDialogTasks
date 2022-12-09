package saltsheep.dialog.command;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import saltsheep.dialog.base.ADialogChainBase;
import saltsheep.dialog.base.FDialogHandler;

public class CommandRunChain extends CommandBase {

	@Override
	public String getName() {
		return "runChain";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "usage.command.runChain";
	}
	
	public int getRequiredPermissionLevel()
    {
        return 2;
    }

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if(args.length == 1) {
			if(sender.getCommandSenderEntity()!=null&&sender.getCommandSenderEntity() instanceof EntityPlayerMP ) {
				if(FDialogHandler.getMainDialogsID().contains(args[0])) {
					ADialogChainBase chain = FDialogHandler.createMainDialog(args[0], (EntityPlayerMP) sender.getCommandSenderEntity());
					if(chain.canRun()&&FDialogHandler.playersRunningDialog.get(sender.getCommandSenderEntity().getName())==null)
						chain.runChain();
					else
						sender.sendMessage(new TextComponentTranslation("command.message.dialogCantRun"));
				}else sender.sendMessage(new TextComponentTranslation("command.message.noDialog"));
			}
		}else{
			sender.sendMessage(new TextComponentTranslation(this.getUsage(sender)));
		}
	}
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos){
		if(args.length == 1)
			return FDialogHandler.getMainDialogsID();
		return super.getTabCompletions(server, sender, args, targetPos);
	}

}
