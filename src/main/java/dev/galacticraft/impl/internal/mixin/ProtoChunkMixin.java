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

package dev.galacticraft.impl.internal.mixin;

import dev.galacticraft.api.accessor.ChunkOxygenAccessor;
import dev.galacticraft.impl.internal.accessor.ChunkOxygenSyncer;
import dev.galacticraft.impl.internal.accessor.ChunkSectionOxygenAccessor;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.s2c.play.CustomPayloadS2CPacket;
import net.minecraft.world.chunk.ChunkSection;
import net.minecraft.world.chunk.ProtoChunk;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

/**
 * @author <a href="https://github.com/TeamGalacticraft">TeamGalacticraft</a>
 */
@Mixin(ProtoChunk.class)
public abstract class ProtoChunkMixin implements ChunkOxygenAccessor, ChunkOxygenSyncer {
    @Shadow @Final private ChunkSection[] sections;

    @Override
    public boolean isBreathable(int x, int y, int z) {
        if (((ProtoChunk)(Object)this).isOutOfHeightLimit(y)) return false;
        ChunkSection section = sections[y >> 4];
        if (!ChunkSection.isEmpty(section)) {
            return ((ChunkSectionOxygenAccessor) section).isBreathable(x & 15, y & 15, z & 15);
        }
        return false;
    }

    @Override
    public void setBreathable(int x, int y, int z, boolean value) {
        if (((ProtoChunk)(Object)this).isOutOfHeightLimit(y)) return;
        ChunkSection section = sections[y >> 4];
        if (!ChunkSection.isEmpty(section)) {
            ((ChunkSectionOxygenAccessor) section).setBreathable(x & 15, y & 15, z & 15, value);
        }
    }

    @Override
    public List<CustomPayloadS2CPacket> syncToClient() {
        throw new UnsupportedOperationException("ProtoChunks shouldn't be synced");
    }

    @Override
    public void readOxygenUpdate(byte b, PacketByteBuf buf) {
        throw new UnsupportedOperationException("ProtoChunks shouldn't be synced");
    }
}