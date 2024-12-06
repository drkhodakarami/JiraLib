package jiraiyah.jiralib.record;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record StringPayload(String value) implements CustomPayload
{
    /**
     * The unique identifier for the {@link StringPayload} type.
     * <p>
     * This ID is used to distinguish the {@link StringPayload} when it is sent
     * over the network. It is constructed using an {@link Identifier} with the
     * namespace "jiralib" and the path "string_payload".
     * </p>
     */
    public static final Id<StringPayload> ID = new Id<>(Identifier.of("jiralib","string_payload"));

    /**
     * A Codec for serializing and deserializing instances of {@link FloatPayload}.
     * <p>
     * This Codec utilizes the {@link RecordCodecBuilder} to define the structure of the
     * {@link FloatPayload} object for encoding and decoding operations. It specifies
     * how each field of the {@link FloatPayload} should be processed during these operations.
     * </p>
     *
     * <p>
     * The Codec is used to convert {@link FloatPayload} objects to a serialized form
     * that can be transmitted over a network or stored, and to reconstruct the objects
     * from this serialized form. This is particularly useful in scenarios where data
     * needs to be shared between different parts of a system or between different systems.
     * </p>
     *
     * @see Codec
     * @see RecordCodecBuilder
     * @see FloatPayload
     */
    public static final Codec<StringPayload> CODEC = RecordCodecBuilder.create(inst -> inst.group(
            Codec.STRING.fieldOf("value").forGetter(StringPayload::value)
    ).apply(inst, StringPayload::new));

    /**
     * A packet codec for encoding and decoding {@link StringPayload} objects
     * to and from {@link ByteBuf}.
     * <p>
     * This codec utilizes the organization's internal {@link PacketCodec} framework
     * to facilitate the serialization and deserialization of {@link StringPayload}
     * within network packets. It ensures that the data is correctly transformed
     * between its in-memory representation and its byte stream format.
     * The codec uses the float value for encoding and decoding.
     * </p>
     */
    public static final PacketCodec<ByteBuf, StringPayload> PACKET_CODEC =
            PacketCodec.tuple(PacketCodecs.STRING, StringPayload::value, StringPayload::new);

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