package systems.reformcloud.reformcloud2.executor.api.packets.out;

import systems.reformcloud.reformcloud2.executor.api.common.configuration.JsonConfiguration;
import systems.reformcloud.reformcloud2.executor.api.common.network.NetworkUtil;
import systems.reformcloud.reformcloud2.executor.api.common.network.packet.DefaultPacket;

import java.util.UUID;

public final class APIPacketOutHasPlayerAccess extends DefaultPacket {

    public APIPacketOutHasPlayerAccess(UUID uuid, String name) {
        super(NetworkUtil.PLAYER_INFORMATION_BUS + 4, new JsonConfiguration()
                .add("uuid", uuid)
                .add("name", name)
        );
    }
}
