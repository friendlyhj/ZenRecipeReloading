package youyihj.zenrecipereloading.network;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import youyihj.zenrecipereloading.ZenRecipeReloading;

/**
 * @author youyihj
 */
public enum NetworkHandler {
    INSTANCE;

    NetworkHandler() {
        channel.registerMessage(ReloadJEIMessage.Handler.class, ReloadJEIMessage.class, 0, Side.CLIENT);
    }

    private final SimpleNetworkWrapper channel = NetworkRegistry.INSTANCE.newSimpleChannel(ZenRecipeReloading.MOD_ID);

    public void sendReloadJEIMessage(EntityPlayerMP player) {
        channel.sendTo(new ReloadJEIMessage(), player);
    }

    @SuppressWarnings("unused")
    public void sendReloadJEIMessageToAllPlayers() {
        channel.sendToAll(new ReloadJEIMessage());
    }
}
