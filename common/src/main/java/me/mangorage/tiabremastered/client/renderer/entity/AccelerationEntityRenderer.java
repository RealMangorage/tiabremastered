package me.mangorage.tiabremastered.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import me.mangorage.tiabremastered.common.entities.AccelerationEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class AccelerationEntityRenderer extends EntityRenderer<AccelerationEntity> {
    public AccelerationEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public boolean shouldRender(AccelerationEntity entity, Frustum frustum, double d, double e, double f) {
        return true;
    }

    @Override
    protected boolean shouldShowName(AccelerationEntity entity) {
        return false;
    }

    @Override
    protected void renderNameTag(AccelerationEntity entity, Component component, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {

    }

    @Override
    public void render(AccelerationEntity entity, float entityYaw, float partialTicks, PoseStack matrixStack, MultiBufferSource bufferIn, int packedLightIn) {
        String timeRate = "x" + entity.getTimeRate();
        float paddingLeftRight = entity.getTimeRate() < 10 ? 0.11F : 0.19F;
        drawText(matrixStack, timeRate, new Vector3f(-paddingLeftRight, 0.064F, 0.51F), Vector3f.YP.rotationDegrees(0), ChatFormatting.WHITE.getColor()); // Front
        drawText(matrixStack, timeRate, new Vector3f(paddingLeftRight, 0.064F, -0.51F), Vector3f.YP.rotationDegrees(180F), ChatFormatting.WHITE.getColor()); // Back
        drawText(matrixStack, timeRate, new Vector3f(0.51F, 0.064F, paddingLeftRight), Vector3f.YP.rotationDegrees(90F), ChatFormatting.WHITE.getColor()); // Right
        drawText(matrixStack, timeRate, new Vector3f(-0.51F, 0.064F, -paddingLeftRight), Vector3f.YP.rotationDegrees(-90F), ChatFormatting.WHITE.getColor()); // Left
        drawText(matrixStack, timeRate, new Vector3f(-paddingLeftRight, 0.51F, -0.064F), Vector3f.XP.rotationDegrees(90F), ChatFormatting.WHITE.getColor()); // Top
        drawText(matrixStack, timeRate, new Vector3f(-paddingLeftRight, -0.51F, 0.064F), Vector3f.XP.rotationDegrees(-90F), ChatFormatting.WHITE.getColor()); // Bottom

    }

    @Override
    public ResourceLocation getTextureLocation(AccelerationEntity entity) {
        return null;
    }

    private void drawText(PoseStack matrixStack, String text, Vector3f translateVector, Quaternion rotate, int color) {
        matrixStack.pushPose();
        matrixStack.translate(translateVector.x(), translateVector.y(), translateVector.z());
        matrixStack.scale(0.02F, -0.02F, 0.02F);
        matrixStack.mulPose(rotate);
        getFont().draw(matrixStack, text, 0, 0, color);
        matrixStack.popPose();
    }
}
