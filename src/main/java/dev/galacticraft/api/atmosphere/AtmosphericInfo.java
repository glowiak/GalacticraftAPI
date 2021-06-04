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

package dev.galacticraft.api.atmosphere;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.galacticraft.api.internal.codec.MapCodec;
import dev.galacticraft.api.registry.AddonRegistry;
import it.unimi.dsi.fastutil.objects.Object2DoubleArrayMap;
import it.unimi.dsi.fastutil.objects.Object2DoubleMap;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.util.registry.Registry;

import java.util.function.Supplier;

public record AtmosphericInfo(Object2DoubleMap<AtmosphericGas> composition, double temperature, float pressure) {
    private static final MapCodec<AtmosphericGas, Double, Object2DoubleMap<AtmosphericGas>> MAP_CODEC = MapCodec.create(Object2DoubleArrayMap::new, AtmosphericGas.REGISTRY_CODEC.xmap(Supplier::get, gas -> () -> gas), Codec.DOUBLE);
    public static final Codec<AtmosphericInfo> CODEC = RecordCodecBuilder.create(atmosphericInfoInstance -> atmosphericInfoInstance.group(
            MAP_CODEC.fieldOf("composition").forGetter(AtmosphericInfo::composition),
            Codec.DOUBLE.fieldOf("temperature").forGetter(AtmosphericInfo::temperature),
            Codec.FLOAT.fieldOf("pressure").forGetter(AtmosphericInfo::pressure)
    ).apply(atmosphericInfoInstance, AtmosphericInfo::new));

    public void writePacket(PacketByteBuf buf) {
        buf.writeInt(this.composition.size());
        buf.writeFloat(this.pressure);
        buf.writeDouble(this.temperature);
        for (Object2DoubleMap.Entry<AtmosphericGas> entry : this.composition.object2DoubleEntrySet()) {
            ;
            buf.writeIdentifier(entry.getKey().getId());
            buf.writeDouble(entry.getDoubleValue());
        }
    }

    public static AtmosphericInfo readPacket(DynamicRegistryManager dynamicRegistryManager, PacketByteBuf buf) {
        Registry<AtmosphericGas> reg = dynamicRegistryManager.get(AddonRegistry.ATMOSPHERIC_GAS_KEY);
        int size = buf.readInt();
        Builder builder = new Builder();
        builder.pressure(buf.readFloat());
        builder.temperature(buf.readDouble());
        for (int i = 0; i < size; i++) {
            builder.gas(reg.get(buf.readIdentifier()), buf.readDouble());
        }
        return builder.build();
    }

    public static class Builder {
        private final Object2DoubleMap<AtmosphericGas> composition = new Object2DoubleArrayMap<>();
        private double temperature = 15.0;
        private float pressure = 1.0f;

        public Builder temperature(double temperature) {
            this.temperature = temperature;
            return this;
        }

        public Builder pressure(float pressure) {
            this.pressure = pressure;
            return this;
        }

        public Builder gas(AtmosphericGas gas, double ppm) {
            this.composition.put(gas, ppm);
            return this;
        }

        public AtmosphericInfo build() {
            return new AtmosphericInfo(this.composition, this.temperature, this.pressure);
        }
    }
}
