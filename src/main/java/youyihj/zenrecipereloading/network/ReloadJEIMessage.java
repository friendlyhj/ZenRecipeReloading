package youyihj.zenrecipereloading.network;

import io.netty.buffer.ByteBuf;
import mezz.jei.JustEnoughItems;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import youyihj.zenrecipereloading.mixins.jei.JEIProxyClientAccessor;

import static crafttweaker.mc1120.commands.SpecialMessagesChat.getNormalMessage;

/**
 * @author youyihj
 */
public class ReloadJEIMessage implements IMessage {
    @Override
    public void fromBytes(ByteBuf byteBuf) {

    }

    @Override
    public void toBytes(ByteBuf byteBuf) {

    }

    public static class Handler implements IMessageHandler<ReloadJEIMessage, IMessage> {

        @Override
        public IMessage onMessage(ReloadJEIMessage reloadJEIMessage, MessageContext messageContext) {
            Minecraft.getMinecraft().addScheduledTask(() -> {
                JEIProxyClientAccessor jeiProxy = (JEIProxyClientAccessor) JustEnoughItems.getProxy();
                long a = System.currentTimeMillis();
                jeiProxy.getStarter().start(jeiProxy.getPlugins(), jeiProxy.getTextures());
                long b = System.currentTimeMillis();
                Minecraft.getMinecraft().player.sendMessage(getNormalMessage("Reloaded JEI successfully in " + (b - a) + "ms"));
            });
            return null;
        }
    }
}
