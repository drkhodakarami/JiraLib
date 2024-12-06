/***********************************************************************************
 * Copyright (c) 2024 Alireza Khodakarami (Jiraiyah)                               *
 * ------------------------------------------------------------------------------- *
 * MIT License                                                                     *
 * =============================================================================== *
 * Permission is hereby granted, free of charge, to any person obtaining a copy    *
 * of this software and associated documentation files (the "Software"), to deal   *
 * in the Software without restriction, including without limitation the rights    *
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell       *
 * copies of the Software, and to permit persons to whom the Software is           *
 * furnished to do so, subject to the following conditions:                        *
 * ------------------------------------------------------------------------------- *
 * The above copyright notice and this permission notice shall be included in all  *
 * copies or substantial portions of the Software.                                 *
 * ------------------------------------------------------------------------------- *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR      *
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,        *
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE     *
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER          *
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,   *
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE   *
 * SOFTWARE.                                                                       *
 ***********************************************************************************/

package jiraiyah.jiralib.record;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

/**
 * A record class representing a block position and its dimension.
 *
 * @author Jiraiyah
 */
@SuppressWarnings("unused")
public record CoordinateDataPayload(BlockPos pos, String dimension) implements CustomPayload
{
    /**
     * The unique identifier for the {@link CoordinateDataPayload} type.
     * <p>
     * This ID is used to distinguish the {@link CoordinateDataPayload} when it is sent
     * over the network. It is constructed using an {@link Identifier} with the
     * namespace "jiralib" and the path "coordinate_data_payload".
     * </p>
     */
    public static final Id<CoordinateDataPayload> ID = new Id<>(Identifier.of("jiralib", "coordinate_data_payload"));

    /**
     * A codec for serializing and deserializing {@link CoordinateDataPayload} objects.
     * <p>
     * This codec uses {@link RecordCodecBuilder} to define the structure of the
     * {@link CoordinateDataPayload} record, specifying how each field should be encoded
     * and decoded. The {@code pos} field is encoded using {@link BlockPos#CODEC},
     * and the {@code dimension} field is encoded as a string.
     * </p>
     */
    public static final Codec<CoordinateDataPayload> CODEC = RecordCodecBuilder.create(inst -> inst.group(
            BlockPos.CODEC.fieldOf("pos").forGetter(CoordinateDataPayload::pos),
            Codec.STRING.fieldOf("dimension").forGetter(CoordinateDataPayload::dimension)
    ).apply(inst, CoordinateDataPayload::new));
    /**
     * A packet codec for encoding and decoding {@link CoordinateDataPayload} objects
     * to and from {@link PacketByteBuf}.
     * <p>
     * This codec utilizes the organization's internal {@link PacketCodec} framework
     * to facilitate the serialization and deserialization of {@link CoordinateDataPayload}
     * within network packets. It ensures that the data is correctly transformed
     * between its in-memory representation and its byte stream format.
     * </p>
     */
    public static final PacketCodec<PacketByteBuf, CoordinateDataPayload> PACKET_CODEC =
            PacketCodec.tuple(BlockPos.PACKET_CODEC, CoordinateDataPayload::pos,
                              PacketCodecs.STRING, CoordinateDataPayload::dimension,
                              CoordinateDataPayload::new);

    /**
     * Retrieves the unique identifier for this payload type.
     *
     * @return The ID associated with this FloatPayload.
     */
    @Override
    public Id<? extends CustomPayload> getId()
    {
        return ID;
    }
}