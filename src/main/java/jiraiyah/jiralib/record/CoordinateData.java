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
import net.minecraft.util.math.BlockPos;

/**
 * A record class representing a block position and its dimension.
 *
 * @author Jiraiyah
 */
@SuppressWarnings("unused")
public record CoordinateData(BlockPos pos, String dimension)
{
    /**
     * A codec for serializing and deserializing {@link CoordinateData} objects.
     * <p>
     * This codec uses {@link RecordCodecBuilder} to define the structure of the
     * {@link CoordinateData} record, specifying how each field should be encoded
     * and decoded. The {@code pos} field is encoded using {@link BlockPos#CODEC},
     * and the {@code dimension} field is encoded as a string.
     * </p>
     */
    public static final Codec<CoordinateData> CODEC = RecordCodecBuilder.create(inst ->
                                                                                        inst.group(
                                                                                                BlockPos.CODEC.fieldOf("pos").forGetter(CoordinateData::pos),
                                                                                                Codec.STRING.fieldOf("dimension").forGetter(CoordinateData::dimension)
                                                                                        ).apply(inst, CoordinateData::new));
    /**
     * A packet codec for encoding and decoding {@link CoordinateData} objects
     * to and from {@link PacketByteBuf}.
     * <p>
     * This codec utilizes the organization's internal {@link PacketCodec} framework
     * to facilitate the serialization and deserialization of {@link CoordinateData}
     * within network packets. It ensures that the data is correctly transformed
     * between its in-memory representation and its byte stream format.
     * </p>
     */
    public static final PacketCodec<PacketByteBuf, CoordinateData> PACKET_CODEC =
            PacketCodec.tuple(BlockPos.PACKET_CODEC, CoordinateData::pos,
                              PacketCodecs.STRING, CoordinateData::dimension,
                              CoordinateData::new);
}