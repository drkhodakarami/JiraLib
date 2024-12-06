package jiraiyah.jiralib.record;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record BooleanPayload(boolean value) implements CustomPayload
{
    /**
     * The unique identifier for the {@link BooleanPayload} type.
     * <p>
     * This ID is used to distinguish the {@link BooleanPayload} when it is sent
     * over the network. It is constructed using an {@link Identifier} with the
     * namespace "jiralib" and the path "boolean_payload".
     * </p>
     */
    public static final Id<BooleanPayload> ID = new Id<>(Identifier.of("jiralib","boolean_payload"));

    /**
     * A Codec for serializing and deserializing instances of {@link BooleanPayload}.
     * <p>
     * This Codec utilizes the {@link RecordCodecBuilder} to define the structure of the
     * {@link BooleanPayload} object for encoding and decoding operations. It specifies
     * how each field of the {@link BooleanPayload} should be processed during these operations.
     * </p>
     *
     * <p>
     * The Codec is used to convert {@link BooleanPayload} objects to a serialized form
     * that can be transmitted over a network or stored, and to reconstruct the objects
     * from this serialized form. This is particularly useful in scenarios where data
     * needs to be shared between different parts of a system or between different systems.
     * </p>
     *
     * @see Codec
     * @see RecordCodecBuilder
     * @see BooleanPayload
     */
    public static final Codec<BooleanPayload> CODEC = RecordCodecBuilder.create(inst -> inst.group(
            Codec.BOOL.fieldOf("value").forGetter(BooleanPayload::value)
    ).apply(inst, BooleanPayload::new));

    /**
     * A packet codec for encoding and decoding {@link BooleanPayload} objects
     * to and from {@link ByteBuf}.
     * <p>
     * This codec utilizes the organization's internal {@link PacketCodec} framework
     * to facilitate the serialization and deserialization of {@link BooleanPayload}
     * within network packets. It ensures that the data is correctly transformed
     * between its in-memory representation and its byte stream format.
     * The codec uses the float value for encoding and decoding.
     * </p>
     */
    public static final PacketCodec<ByteBuf, BooleanPayload> PACKET_CODEC =
            PacketCodec.tuple(PacketCodecs.BOOL, BooleanPayload::value, BooleanPayload::new);

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