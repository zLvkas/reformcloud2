package systems.reformcloud.reformcloud2.executor.client.network.channel;

import io.netty.channel.ChannelHandlerContext;
import org.jetbrains.annotations.NotNull;
import systems.reformcloud.reformcloud2.executor.api.common.language.LanguageManager;
import systems.reformcloud.reformcloud2.executor.api.common.network.channel.manager.DefaultChannelManager;
import systems.reformcloud.reformcloud2.executor.api.common.network.channel.shared.SharedNetworkChannelReader;
import systems.reformcloud.reformcloud2.executor.client.ClientExecutor;

public class ClientNetworkChannelReader extends SharedNetworkChannelReader {

    @Override
    public void channelInactive(@NotNull ChannelHandlerContext context) {
        if (this.packetSender != null) {
            DefaultChannelManager.INSTANCE.unregisterChannel(packetSender);
            System.out.println(LanguageManager.get("network-channel-disconnected", packetSender.getName()));
            ClientExecutor.getInstance().handleDisconnect();
        }
    }
}
