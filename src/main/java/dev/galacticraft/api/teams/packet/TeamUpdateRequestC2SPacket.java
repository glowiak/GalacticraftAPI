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

package dev.galacticraft.api.teams.packet;

import dev.galacticraft.api.teams.data.Team;
import dev.galacticraft.api.teams.packet.listener.ServerTeamsPacketListener;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketByteBuf;

import java.io.IOException;
import java.util.UUID;

public class TeamUpdateRequestC2SPacket implements Packet<ServerTeamsPacketListener> {

    private UUID requester;
    private String oldName;
    private Team updatedTeam;

    public TeamUpdateRequestC2SPacket(UUID requester, String oldName, Team updatedTeam) {
        this.requester = requester;
        this.oldName = oldName;
        this.updatedTeam = updatedTeam;
    }

    @Override
    public void read(PacketByteBuf buf) throws IOException {
        this.requester = buf.readUuid();
        this.oldName = buf.readString(1024);
        this.updatedTeam = Team.fromTag(buf.readCompoundTag());
    }

    @Override
    public void write(PacketByteBuf buf) throws IOException {
        buf.writeUuid(this.requester);
        buf.writeString(this.oldName);
        buf.writeCompoundTag(this.updatedTeam.toTag());
    }

    @Override
    public void apply(ServerTeamsPacketListener listener) {
        listener.onTeamUpdateRequest(this);
    }

    @Environment(EnvType.SERVER)
    public UUID getRequester() {
        return requester;
    }

    @Environment(EnvType.SERVER)
    public String getOldName() {
        return oldName;
    }

    @Environment(EnvType.SERVER)
    public Team getUpdatedTeam() {
        return updatedTeam;
    }
}