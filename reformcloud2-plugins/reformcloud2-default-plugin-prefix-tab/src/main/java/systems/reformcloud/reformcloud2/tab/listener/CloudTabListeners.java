/*
 * MIT License
 *
 * Copyright (c) ReformCloud-Team
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package systems.reformcloud.reformcloud2.tab.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import systems.reformcloud.reformcloud2.executor.api.api.API;
import systems.reformcloud.reformcloud2.executor.api.common.event.handler.Listener;
import systems.reformcloud.reformcloud2.permissions.PermissionAPI;
import systems.reformcloud.reformcloud2.permissions.util.events.group.PermissionGroupUpdateEvent;
import systems.reformcloud.reformcloud2.permissions.util.events.user.PermissionUserUpdateEvent;
import systems.reformcloud.reformcloud2.tab.ReformCloudTabPlugin;

public class CloudTabListeners {

    public CloudTabListeners(Plugin plugin) {
        this.plugin = plugin;
    }

    private final Plugin plugin;

    @Listener
    public void handle(final @NotNull PermissionUserUpdateEvent event) {
        if (API.getInstance().getCurrentProcessInformation().getExtra().getBoolean("disable-tab")) {
            return;
        }

        Player player = Bukkit.getPlayer(event.getPermissionUser().getUniqueID());
        if (player == null) {
            return;
        }

        Bukkit.getScheduler().runTask(this.plugin, () -> ReformCloudTabPlugin.pullPlayerNameTags(player));
    }

    @Listener
    public void handle(final @NotNull PermissionGroupUpdateEvent event) {
        if (API.getInstance().getCurrentProcessInformation().getExtra().getBoolean("disable-tab")) {
            return;
        }

        Bukkit.getScheduler().runTask(this.plugin, () -> Bukkit.getOnlinePlayers().forEach(player -> {
            if (PermissionAPI.getInstance().getPermissionUtil().loadUser(player.getUniqueId()).isInGroup(event.getPermissionGroup().getName())) {
                ReformCloudTabPlugin.pullPlayerNameTags(player);
            }
        }));
    }
}