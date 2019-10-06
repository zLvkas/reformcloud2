package systems.reformcloud.reformcloud2.executor.api.spigot.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.plugin.Plugin;
import systems.reformcloud.reformcloud2.executor.api.common.ExecutorAPI;
import systems.reformcloud.reformcloud2.executor.api.common.plugins.basic.DefaultPlugin;
import systems.reformcloud.reformcloud2.executor.api.common.process.ProcessInformation;
import systems.reformcloud.reformcloud2.executor.api.common.utility.list.Links;
import systems.reformcloud.reformcloud2.executor.api.spigot.SpigotExecutor;

public final class ExtraListenerHandler implements Listener {

    @EventHandler
    public void handle(final PluginEnableEvent event) {
        Plugin plugin = event.getPlugin();
        DefaultPlugin defaultPlugin = new DefaultPlugin(
                plugin.getDescription().getVersion(),
                plugin.getDescription().getAuthors().get(0),
                plugin.getDescription().getMain(),
                plugin.getDescription().getDepend(),
                plugin.getDescription().getSoftDepend(),
                plugin.isEnabled(),
                plugin.getName()
        );
        ExecutorAPI.getInstance().getThisProcessInformation().getPlugins().add(defaultPlugin);
        ExecutorAPI.getInstance().getThisProcessInformation().updateRuntimeInformation();
        ExecutorAPI.getInstance().update(ExecutorAPI.getInstance().getThisProcessInformation());
    }

    @EventHandler
    public void handle(final PluginDisableEvent event) {
        final ProcessInformation processInformation = ExecutorAPI.getInstance().getThisProcessInformation();
        Plugin plugin = event.getPlugin();
        DefaultPlugin defaultPlugin = Links.filter(processInformation.getPlugins(), in -> in.getName().equals(plugin.getName()));
        if (defaultPlugin != null) {
            processInformation.getPlugins().remove(defaultPlugin);
            processInformation.updateRuntimeInformation();
            SpigotExecutor.getInstance().setThisProcessInformation(processInformation);
            ExecutorAPI.getInstance().update(processInformation);
        }
    }
}