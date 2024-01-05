package youyihj.zenrecipereloading.command;

import crafttweaker.mc1120.commands.CraftTweakerCommand;
import crafttweaker.mc1120.commands.SpecialMessagesChat;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextFormatting;
import youyihj.zenrecipereloading.network.NetworkHandler;

import static crafttweaker.mc1120.commands.SpecialMessagesChat.getNormalMessage;

/**
 * @author youyihj
 */
public class ReloadJEICommand extends CraftTweakerCommand {
    public ReloadJEICommand() {
        super("jeiReload");
    }

    @Override
    protected void init() {
        setDescription(SpecialMessagesChat.getNormalMessage("Reload JEI"));
    }

    @Override
    public void executeCommand(MinecraftServer server, ICommandSender sender, String[] args) {
        if (server.isDedicatedServer()) {
            sender.sendMessage(getNormalMessage(TextFormatting.DARK_RED + "The command only can be run in integrated server (SinglePlayer)!"));
        } else if (sender instanceof EntityPlayerMP) {
            NetworkHandler.INSTANCE.sendReloadJEIMessage(((EntityPlayerMP) sender));
        }
    }
}
