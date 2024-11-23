package jiraiyah.jiralib.blockentity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;

/**
 * The {@code NoScreenUpdatableEndTickBE} class extends {@code NoScreenUpdatableBE}
 * and represents a block entity that does not have a user interface screen but
 * requires updates at the end of each tick cycle.
 *
 * <p>As a subclass of {@code NoScreenUpdatableBE}, this class inherits the
 * basic update capabilities and adds additional behavior specific to end-of-tick
 * updates. This is particularly useful for block entities that need to perform
 * actions or calculations after all other updates have been processed in a tick.</p>
 *
 * <p>Developers can extend this class to create custom block entities that
 * require end-of-tick processing without a GUI. Ensure that any additional
 * functionality is implemented in accordance with the organization's best
 * practices and coding standards.</p>
 *
 * @see NoScreenUpdatableBE
 */
public class NoScreenUpdatableEndTickBE extends NoScreenUpdatableBE
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
    public NoScreenUpdatableEndTickBE(BlockEntityType<?> type, BlockPos pos, BlockState state)
    {
        super(type, pos, state);
    }

    /**
     * Determines whether the block entity should defer its update logic until the end of the current tick.
     *
     * <p>This method is used to control the timing of the block entity's update operations. By returning
     * {@code true}, the block entity indicates that its update logic should be postponed until the end
     * of the current server tick. This can be useful for coordinating updates with other game mechanics
     * or ensuring that certain conditions are met before performing the update for example those objects that
     * implement {@code ISync} and need syncing before update.
     *
     * <p>Subclasses can override this method to implement specific conditions under which the update
     * should be delayed. For example, a block entity might wait for a certain game state or event
     * before proceeding with its update logic. If you don't need to defer the update, override this method
     * and return {@code false}.
     *
     * <p>Example usage:
     * <pre>
     * {@code
     * @Override
     * public boolean shouldWaitEndTick() {
     *     // Custom logic to determine if update should be delayed
     *     return someConditionMet();
     * }
     * }
     * </pre>
     *
     * @return {@code true} if the update should be deferred to the end of the tick; {@code false} otherwise.
     */
    @Override
    public boolean shouldWaitEndTick()
    {
        return true;
    }
}