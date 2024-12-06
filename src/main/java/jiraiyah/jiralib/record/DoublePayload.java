package jiraiyah.jiralib.record;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record DoublePayload(double value) implements CustomPayload
{
    /**
     * The unique identifier for the {@link DoublePayload} type.
     * <p>
     * This ID is used to distinguish the {@link DoublePayload} when it is sent
     * over the network. It is constructed using an {@link Identifier} with the
     * namespace "jiralib" and the path "double_payload".
     * </p>
     */
    public static final Id<DoublePayload> ID = new Id<>(Identifier.of("jiralib","double_payload"));

    /**
     * A Codec for serializing and deserializing instances of {@link DoublePayload}.
     * <p>
     * This Codec utilizes the {@link RecordCodecBuilder} to define the structure of the
     * {@link DoublePayload} object for encoding and decoding operations. It specifies
     * how each field of the {@link DoublePayload} should be processed during these operations.
     * </p>
     *
     * <p>
     * The Codec is used to convert {@link DoublePayload} objects to a serialized form
     * that can be transmitted over a network or stored, and to reconstruct the objects
     * from this serialized form. This is particularly useful in scenarios where data
     * needs to be shared between different parts of a system or between different systems.
     * </p>
     *
     * @see Codec
     * @see RecordCodecBuilder
     * @see DoublePayload
     */
    public static final Codec<DoublePayload> CODEC = RecordCodecBuilder.create(inst -> inst.group(
            Codec.DOUBLE.fieldOf("value").forGetter(DoublePayload::value)
    ).apply(inst, DoublePayload::new));

    /**
     * A packet codec for encoding and decoding {@link DoublePayload} objects
     * to and from {@link ByteBuf}.
     * <p>
     * This codec utilizes the organization's internal {@link PacketCodec} framework
     * to facilitate the serialization and deserialization of {@link DoublePayload}
     * within network packets. It ensures that the data is correctly transformed
     * between its in-memory representation and its byte stream format.
     * The codec uses the float value for encoding and decoding.
     * </p>
     */
    public static final PacketCodec<ByteBuf, DoublePayload> PACKET_CODEC =
            PacketCodec.tuple(PacketCodecs.DOUBLE, DoublePayload::value, DoublePayload::new);

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