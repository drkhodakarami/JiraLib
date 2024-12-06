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
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

/**
 * A custom payload that contains a single integer value.
 *
 * @author TurtyWurty
 */
@SuppressWarnings("unused")
public record IntegerPayload(int value) implements CustomPayload
{
    /**
     * The unique identifier for the {@link IntegerPayload} type.
     * <p>
     * This ID is used to distinguish the {@link IntegerPayload} when it is sent
     * over the network. It is constructed using an {@link Identifier} with the
     * namespace "jiralib" and the path "integer_payload".
     * </p>
     */
    public static final Id<IntegerPayload> ID = new Id<>(Identifier.of("jiralib","integer_payload"));

    /**
     * A Codec for serializing and deserializing instances of {@link IntegerPayload}.
     * <p>
     * This Codec utilizes the {@link RecordCodecBuilder} to define the structure of the
     * {@link IntegerPayload} object for encoding and decoding operations. It specifies
     * how each field of the {@link IntegerPayload} should be processed during these operations.
     * </p>
     *
     * <p>
     * The Codec is used to convert {@link IntegerPayload} objects to a serialized form
     * that can be transmitted over a network or stored, and to reconstruct the objects
     * from this serialized form. This is particularly useful in scenarios where data
     * needs to be shared between different parts of a system or between different systems.
     * </p>
     *
     * @see Codec
     * @see RecordCodecBuilder
     * @see IntegerPayload
     */
    public static final Codec<IntegerPayload> CODEC = RecordCodecBuilder.create(inst -> inst.group(
            Codec.INT.fieldOf("value").forGetter(IntegerPayload::value)
    ).apply(inst, IntegerPayload::new));

    /**
     * A packet codec for encoding and decoding {@link IntegerPayload} objects
     * to and from {@link ByteBuf}.
     * <p>
     * This codec utilizes the organization's internal {@link PacketCodec} framework
     * to facilitate the serialization and deserialization of {@link IntegerPayload}
     * within network packets. It ensures that the data is correctly transformed
     * between its in-memory representation and its byte stream format.
     * </p>
     */
    public static final PacketCodec<ByteBuf, IntegerPayload> PACKET_CODEC =
            PacketCodec.tuple(PacketCodecs.INTEGER, IntegerPayload::value, IntegerPayload::new);

    /**
     * Retrieves the unique identifier for this payload type.
     *
     * @return The ID associated with this IntegerPayload.
     */
    @Override
    public Id<? extends CustomPayload> getId()
    {
        return ID;
    }
}