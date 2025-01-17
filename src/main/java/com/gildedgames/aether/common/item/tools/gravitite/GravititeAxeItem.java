package com.gildedgames.aether.common.item.tools.gravitite;

import com.gildedgames.aether.common.item.tools.abilities.IGravititeToolItem;
import com.gildedgames.aether.common.registry.AetherItemGroups;
import com.gildedgames.aether.common.registry.AetherItemTiers;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.InteractionResult;

public class GravititeAxeItem extends AxeItem implements IGravititeToolItem
{
	public GravititeAxeItem() {
		super(AetherItemTiers.GRAVITITE, 5.0F, -3.0F, new Item.Properties().tab(AetherItemGroups.AETHER_TOOLS));
	}
	
	@Override
	public InteractionResult useOn(UseOnContext context) {
		if (context.getPlayer() != null && context.getPlayer().isShiftKeyDown()) {
			return this.startFloatBlock(context);
		} else {
			InteractionResult result = super.useOn(context);
			if (result == InteractionResult.PASS || result == InteractionResult.FAIL) {
				return this.startFloatBlock(context);
			}
			return result;
		}
	}

	private InteractionResult startFloatBlock(UseOnContext context) {
		float destroySpeed = this.getDestroySpeed(context.getItemInHand(), context.getLevel().getBlockState(context.getClickedPos()));
		float efficiency = this.getTier().getSpeed();
		return floatBlock(context, destroySpeed, efficiency);
	}
}
