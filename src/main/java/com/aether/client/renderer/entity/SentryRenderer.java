package com.aether.client.renderer.entity;

import com.aether.Aether;
import com.aether.client.renderer.entity.layers.SentryLayer;
import com.aether.entity.monster.SentryEntity;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.SlimeModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SentryRenderer extends MobRenderer<SentryEntity, SlimeModel<SentryEntity>> {

	private static final ResourceLocation TEXTURE     = new ResourceLocation(Aether.MODID, "textures/entity/sentry/sentry.png");
	private static final ResourceLocation TEXTURE_LIT = new ResourceLocation(Aether.MODID, "textures/entity/sentry/sentry_lit.png");
	
	public SentryRenderer(EntityRendererManager rendererManager) {
		super(rendererManager, new SlimeModel<>(0), 0.3f);
		this.addLayer(new SentryLayer(this));
	}

	@Override
	public ResourceLocation getEntityTexture(SentryEntity entity) {
		return entity.isAwake()? TEXTURE_LIT : TEXTURE;
	}
	
	@Override
	protected void preRenderCallback(SentryEntity entitylivingbaseIn, float partialTickTime) {
//		GL11.glScalef(1.75f, 1.75f, 1.75f);
		float f = 0.879f;
		GlStateManager.scalef(f, f, f);
		float f1 = entitylivingbaseIn.getSlimeSize();
		float f2 = 0.0f;// /*MathHelper.lerp(partialTickTime, entitylivingbaseIn.prevSquishFactor, entitylivingbaseIn.squishFactor)*/ / (f1 * 0.5F + 1.0F);
		float f3 = 1.0F / (f2 + 1.0F);
		GlStateManager.scalef(f3 * f1, 1.0F / f3 * f1, f3 * f1);
	}
	
}
