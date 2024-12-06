package jiraiyah.jiralib.blockentity;

import jiraiyah.jiralib.record.BlockPosPayload;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

/**
 * The {@code UpdatableEndTickBE} class is an abstract extension of the {@code UpdatableBE} class.
 * This class is designed to represent block entities that require updates at the end of each tick
 * within the Minecraft game loop. It provides a framework for implementing custom behavior that
 * should occur after all other tick updates have been processed.
 *
 * <p>Subclasses of {@code UpdatableEndTickBE} should override the necessary methods to define
 * specific end-of-tick update logic. This is particularly useful for block entities that need
 * to perform actions or checks after the main game logic has been executed.</p>
 *
 * @see UpdatableBE
 * @see net.minecraft.block.entity.BlockEntityType
 */
@SuppressWarnings("unused")
public abstract class UpdatableEndTickBE extends NoScreenUpdatableEndTickBE implements ExtendedScreenHandlerFactory<BlockPosPayload>
{
    /**
     * Constructs a new instance of UpdatableBE.
     *
     * @param type  The type of the block entity. This parameter is used to define the specific kind of block entity
     *              that this instance represents. It must not be null.
     * @param pos   The position of the block entity in the world. This parameter specifies the exact location
     *              where the block entity is placed. It must not be null.
     * @param state The state of the block associated with this block entity. This parameter provides information
     *              about the block's properties and behavior. It must not be null.
     */
    public UpdatableEndTickBE(BlockEntityType<?> type, BlockPos pos, BlockState state)
    {
        super(type, pos, state);
    }

    /**
     * Retrieves the data necessary for opening a screen on the client side.
     * This method is used to provide the client with the position of the block entity
     * when a screen associated with this block entity is opened. The position is
     * encapsulated in a {@link BlockPosPayload} object, which is sent to the client
     * to ensure that the correct block entity is referenced.
     *
     * @param player The server-side player entity requesting the screen opening data.
     *               This parameter represents the player who is interacting with the
     *               block entity and is used to determine the context of the interaction.
     *
     * @return A {@link BlockPosPayload} containing the position of this block entity.
     * This payload is used to synchronize the client with the server, ensuring
     * that the correct block entity's screen is opened.
     */
    @Override
    public BlockPosPayload getScreenOpeningData(ServerPlayerEntity player)
    {
        return new BlockPosPayload(this.pos);
    }
}