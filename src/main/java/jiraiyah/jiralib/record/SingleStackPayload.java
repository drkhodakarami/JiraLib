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

import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

/**
 * A custom payload that contains a single Item stack.
 *
 * @author TurtyWurty
 */
@SuppressWarnings("unused")
public record SingleStackPayload(ItemStack stack) implements CustomPayload
{
    /**
     * The unique identifier for the {@link SingleStackPayload} type.
     * <p>
     * This ID is used to distinguish the {@link SingleStackPayload} when it is sent
     * over the network. It is constructed using an {@link Identifier} with the
     * namespace "jiralib" and the path "single_stack_payload".
     * </p>
     */
    public static final Id<SingleStackPayload> ID = new Id<>(Identifier.of("jiralib","single_stack_payload"));

    /**
     * A packet codec for encoding and decoding {@link SingleStackPayload} objects
     * to and from {@link RegistryByteBuf}.
     * <p>
     * This codec utilizes the organization's internal {@link PacketCodec} framework
     * to facilitate the serialization and deserialization of {@link SingleStackPayload}
     * within network packets. It ensures that the data is correctly transformed
     * between its in-memory representation and its byte stream format.
     * </p>
     */
    public static final PacketCodec<RegistryByteBuf, SingleStackPayload> PACKET_CODEC =
            PacketCodec.tuple(ItemStack.PACKET_CODEC, SingleStackPayload::stack, SingleStackPayload::new);

    /**
     * Retrieves the unique identifier for this payload type.
     *
     * @return The ID associated with this SingleStackPayload.
     */
    @Override
    public Id<? extends CustomPayload> getId()
    {
        return ID;
    }
}