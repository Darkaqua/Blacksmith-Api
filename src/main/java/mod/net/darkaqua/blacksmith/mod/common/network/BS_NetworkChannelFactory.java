package net.darkaqua.blacksmith.mod.common.network;

import net.darkaqua.blacksmith.api.common.network.INetworkChannel;
import net.darkaqua.blacksmith.api.common.network.NetworkChannelFactory;
import net.darkaqua.blacksmith.mod.common.network.channel.SimpleChannel;

/**
 * Created by cout970 on 24/12/2015.
 */
public class BS_NetworkChannelFactory extends NetworkChannelFactory {

    private BS_NetworkChannelFactory() {
    }

    public static void init() {
        INSTANCE = new BS_NetworkChannelFactory();
    }

    @Override
    protected INetworkChannel newNetworkChannel(String channelName) {
        return new BS_NetworkChannel(new SimpleChannel(channelName));
    }
}
