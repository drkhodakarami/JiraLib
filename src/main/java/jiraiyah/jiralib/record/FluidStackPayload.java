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
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

/**
 * Represents a stack of fluid in a game, encapsulating the fluid type and the amount.
 *
 * <p>This class is used to manage fluid quantities and their corresponding types
 * in a fluid handling system, such as in a Minecraft mod.</p>
 *
 * <p>It provides serialization and deserialization capabilities through codecs,
 * allowing for easy storage and network transmission of fluid data.</p>
 *
 * @param fluid The {@link FluidVariant} representing the type of fluid in the stack.
 * @param amount The long value representing the amount of fluid in the stack.
 */
@SuppressWarnings("unused")
public record FluidStackPayload(FluidVariant fluid, long amount) implements CustomPayload
{
    /**
     * The unique identifier for the {@link FluidStackPayload} type.
     * <p>
     * This ID is used to distinguish the {@link FluidStackPayload} when it is sent
     * over the network. It is constructed using an {@link Identifier} with the
     * namespace "jiralib" and the path "fluid_stack_payload".
     * </p>
     */
    public static final Id<FluidStackPayload> ID = new Id<>(Identifier.of("jiralib", "fluid_stack_payload"));

    /**
     * A codec for serializing and deserializing {@link FluidStackPayload} objects.
     * <p>
     * This codec uses {@link RecordCodecBuilder} to define the structure of the
     * {@link FluidStackPayload} record, specifying how each field should be encoded
     * and decoded. The {@code fluid} field is encoded using {@link FluidVariant#CODEC},
     * and the {@code amount} field is encoded as a long.
     * </p>
     */
    public static final Codec<FluidStackPayload> CODEC = RecordCodecBuilder.create(inst -> inst.group(
            FluidVariant.CODEC.fieldOf("fluid").forGetter(FluidStackPayload::fluid),
            Codec.LONG.fieldOf("amount").forGetter(FluidStackPayload::amount)
    ).apply(inst, FluidStackPayload::new));

    /**
     * A packet codec for encoding and decoding {@link FluidStackPayload} objects
     * to and from {@link RegistryByteBuf}.
     * <p>
     * This codec utilizes the organization's internal {@link PacketCodec} framework
     * to facilitate the serialization and deserialization of {@link FluidStackPayload}
     * within network packets. It ensures that the data is correctly transformed
     * between its in-memory representation and its byte stream format.
     * </p>
     */
    public static final PacketCodec<RegistryByteBuf, FluidStackPayload> PACKET_CODEC = PacketCodec.tuple(
            FluidVariant.PACKET_CODEC, FluidStackPayload::fluid,
            PacketCodecs.LONG, FluidStackPayload::amount,
            FluidStackPayload::new
    );

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