package com.gildedgames.aether.core.network.packet.server;

import com.gildedgames.aether.common.inventory.provider.AccessoriesProvider;
import com.gildedgames.aether.core.network.AetherPacketHandler;
import com.gildedgames.aether.core.network.IAetherPacket.AetherPacket;
import com.gildedgames.aether.core.network.packet.client.ClientGrabItemPacket;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkHooks;

public class OpenAccessoriesPacket extends AetherPacket
{
    private final int playerID;

    public OpenAccessoriesPacket(int playerID) {
        this.playerID = playerID;
    }

    @Override
    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(this.playerID);
    }

    public static OpenAccessoriesPacket decode(FriendlyByteBuf buf) {
        int playerID = buf.readInt();
        return new OpenAccessoriesPacket(playerID);
    }

    @Override
    public void execute(Player playerEntity) {
        if (playerEntity != null && playerEntity.getServer() != null && playerEntity.level.getEntity(this.playerID) instanceof ServerPlayer serverPlayer) {
            ItemStack itemStack = serverPlayer.getInventory().getSelected();
            serverPlayer.getInventory().setPickedItem(ItemStack.EMPTY);
            NetworkHooks.openGui(serverPlayer, new AccessoriesProvider());
            if (!itemStack.isEmpty()) {
                serverPlayer.getInventory().setPickedItem(itemStack);
                AetherPacketHandler.sendToPlayer(new ClientGrabItemPacket(serverPlayer.getId(), itemStack), serverPlayer);
            }
        }
    }
}
