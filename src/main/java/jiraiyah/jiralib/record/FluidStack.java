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
public record FluidStack(FluidVariant fluid, long amount)
{
    /**
     * A codec for serializing and deserializing {@link FluidStack} objects.
     * <p>
     * This codec uses {@link RecordCodecBuilder} to define the structure of the
     * {@link FluidStack} record, specifying how each field should be encoded
     * and decoded. The {@code fluid} field is encoded using {@link FluidVariant#CODEC},
     * and the {@code amount} field is encoded as a long.
     * </p>
     */
    public static final Codec<FluidStack> CODEC = RecordCodecBuilder.create(inst -> inst.group(
            FluidVariant.CODEC.fieldOf("fluid").forGetter(FluidStack::fluid),
            Codec.LONG.fieldOf("amount").forGetter(FluidStack::amount)
    ).apply(inst, FluidStack::new));

    /**
     * A packet codec for encoding and decoding {@link FluidStack} objects
     * to and from {@link RegistryByteBuf}.
     * <p>
     * This codec utilizes the organization's internal {@link PacketCodec} framework
     * to facilitate the serialization and deserialization of {@link FluidStack}
     * within network packets. It ensures that the data is correctly transformed
     * between its in-memory representation and its byte stream format.
     * </p>
     */
    public static final PacketCodec<RegistryByteBuf, FluidStack> PACKET_CODEC = PacketCodec.tuple(
            FluidVariant.PACKET_CODEC, FluidStack::fluid,
            PacketCodecs.LONG, FluidStack::amount,
            FluidStack::new
    );
}