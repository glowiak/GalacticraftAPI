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

package dev.galacticraft.impl.universe.display.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.galacticraft.api.universe.display.CelestialDisplayConfig;
import net.minecraft.util.Identifier;

public record IconCelestialDisplayConfig(Identifier texture, int u, int v, int width, int height, float scale) implements CelestialDisplayConfig {
    public static final Codec<IconCelestialDisplayConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Identifier.CODEC.fieldOf("texture").forGetter(IconCelestialDisplayConfig::texture),
            Codec.INT.fieldOf("u").forGetter(IconCelestialDisplayConfig::u),
            Codec.INT.fieldOf("v").forGetter(IconCelestialDisplayConfig::v),
            Codec.INT.fieldOf("width").forGetter(IconCelestialDisplayConfig::width),
            Codec.INT.fieldOf("height").forGetter(IconCelestialDisplayConfig::height),
            Codec.FLOAT.fieldOf("scale").forGetter(IconCelestialDisplayConfig::scale)
    ).apply(instance, IconCelestialDisplayConfig::new));
}
