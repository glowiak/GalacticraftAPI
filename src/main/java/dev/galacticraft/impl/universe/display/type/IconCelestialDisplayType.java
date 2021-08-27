/*
 * Copyright (c) 2019-2021 Team Galacticraft
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

package dev.galacticraft.impl.universe.display.type;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.serialization.Codec;
import dev.galacticraft.api.universe.display.CelestialDisplayType;
import dev.galacticraft.impl.universe.display.config.IconCelestialDisplayConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Matrix4f;
import net.minecraft.util.math.Vector4f;
import org.lwjgl.opengl.GL32C;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class IconCelestialDisplayType extends CelestialDisplayType<IconCelestialDisplayConfig> {
    public static final IconCelestialDisplayType INSTANCE = new IconCelestialDisplayType(IconCelestialDisplayConfig.CODEC);

    protected IconCelestialDisplayType(Codec<IconCelestialDisplayConfig> codec) {
        super(codec);
    }

    @Override
    public Vector4f render(MatrixStack matrices, BufferBuilder buffer, int size, double mouseX, double mouseY, float delta, Consumer<Supplier<Shader>> shaderSetter, IconCelestialDisplayConfig config) {
        shaderSetter.accept(GameRenderer::getPositionTexShader);
        Matrix4f model = matrices.peek().getModel();
        AbstractTexture texture = MinecraftClient.getInstance().getTextureManager().getTexture(config.texture());
        RenderSystem.setShaderTexture(0, texture.getGlId());
        texture.bindTexture();
        float width = GlStateManager._getTexLevelParameter(GL32C.GL_TEXTURE_2D, 0, GL32C.GL_TEXTURE_WIDTH);
        float height = GlStateManager._getTexLevelParameter(GL32C.GL_TEXTURE_2D, 0, GL32C.GL_TEXTURE_HEIGHT);
        buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE);
        buffer.vertex(model, config.scale() * -size, config.scale() * -size, 0).texture(config.u() / width, config.v() / height).next();
        buffer.vertex(model, config.scale() * -size, config.scale() * size, 0).texture(config.u() / width, (config.v() + config.height()) / height).next();
        buffer.vertex(model, config.scale() * size, config.scale() * size, 0).texture((config.u() + config.width()) / width, (config.v() + config.height()) / height).next();
        buffer.vertex(model, config.scale() * size, config.scale() * -size, 0).texture((config.u() + config.width()) / width, config.v() / height).next();
        buffer.end();
        BufferRenderer.draw(buffer);
        return new Vector4f(config.scale() * -size, config.scale() * -size, (config.scale() * size) * 2, (config.scale() * size) * 2);
    }
}
