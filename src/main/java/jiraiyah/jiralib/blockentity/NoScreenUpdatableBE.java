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

package jiraiyah.jiralib.blockentity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

/**
 * Represents an abstract base class for block entities that require periodic updates.
 *
 * <p>The {@code NoScreenUpdatableBE} class extends {@link BlockEntity}.
 * This design allows block entities to manage their state updates.
 *
 * <p>Subclasses of {@code NoScreenUpdatableBE} should implement the necessary logic to handle
 * block entity updates, such as synchronizing state changes with clients or saving
 * data to persistent storage.
 *
 * <p>Example usage:
 * <pre>
 * {@code
 * public class CustomBlockEntity extends NoScreenUpdatableBE {
 *     // Implement abstract methods and additional logic
 * }
 * }
 * </pre>
 *
 * <p>Key responsibilities of this class include:
 * <ul>
 *   <li>Tracking and managing block entity state changes.</li>
 *   <li>Providing mechanisms for client-server synchronization.</li>
 * </ul>
 *
 * @author TurtyWurty, Modified by Jiraiyah
 */
@SuppressWarnings("unused")
public abstract class NoScreenUpdatableBE extends BlockEntity
{
    /**
     * A flag indicating whether the block entity's state has changed and requires an update.
     *
     * <p>This field is used to track modifications to the block entity's data that need to be
     * synchronized with the client or saved to persistent storage. When set to {@code true},
     * it signifies that the block entity's state is "dirty" and should be updated accordingly.
     *
     * <p>It is internally used for setting this flag to {@code true} whenever a change occurs
     * that affects the block entity's state in the Update, such as a change in its properties
     * or contents. The flag should be reset to {@code false} after the necessary updates have
     * been performed.
     *
     * <p>Example:
     * <pre>
     * {@code
     * // Mark the block entity as dirty after modifying its state
     * this.isDirty = true;
     *
     * // Perform update logic
     * if (this.isDirty) {
     *     // Update client or save state
     *     this.isDirty = false;
     * }
     * }
     * </pre>
     */
    protected boolean isDirty = false;

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
    public NoScreenUpdatableBE(BlockEntityType<?> type, BlockPos pos, BlockState state)
    {
        super(type, pos, state);
    }

    /**
     * Updates the state of the block entity and performs necessary synchronization
     * with the client or persistent storage.
     *
     * <p>This method should be called whenever the block entity's state changes
     * and requires an update. It is responsible for handling the logic needed to
     * ensure that the block entity's state is consistent across the server and
     * client, as well as being saved correctly.
     *
     * <p>Subclasses should override this method to implement specific update
     * logic, such as sending packets to clients or writing data to NBT. The
     * method may also involve checking the {@code isDirty} flag to determine
     * whether an update is necessary.
     *
     * <p>Example usage:
     * <pre>
     * {@code
     * @Override
     * public void update() {
     *     // Perform update logic
     *
     *     super.update();
     * }
     * }
     * </pre>
     */
    public void update()
    {
        this.isDirty = true;
        if (!shouldWaitEndTick())
        {
            markDirty();

            if (this.world != null && !this.world.isClient)
                this.world.updateListeners(this.pos, getCachedState(), getCachedState(), Block.NOTIFY_ALL);
        }
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
    public boolean shouldWaitEndTick()
    {
        return false;
    }

    /**
     * Executes the logic that should occur at the end of each server tick for this block entity.
     *
     * <p>This method is intended to be called once per server tick, after all other tick-related
     * operations have been completed. It provides an opportunity to perform any final updates
     * or state changes that need to occur at the end of the tick cycle.
     *
     * <p>Subclasses can override this method to implement specific end-of-tick behavior, such as
     * finalizing state changes, triggering events, or performing cleanup tasks. This method is
     * particularly useful for operations that depend on the completion of other tick activities.
     *
     * <p>Example usage:
     * <pre>
     * {@code
     * @Override
     * public void endTick() {
     *     // Custom end-of-tick logic
     *
     *     super.endTick();
     * }
     * }
     * </pre>
     */
    public void endTick()
    {
        if (this.isDirty)
        {
            this.isDirty = false;

            markDirty();

            if (this.world != null)
                this.world.updateListeners(this.pos, getCachedState(), getCachedState(), Block.NOTIFY_ALL);
        }
    }

    /**
     * Creates a packet that is sent to the client to update the block entity's state.
     * This packet includes all necessary data to synchronize the client with the current
     * state of the block entity on the server.
     *
     * @return A packet containing the block entity update or null if there is no update.
     */
    @Override
    public @Nullable Packet<ClientPlayPacketListener> toUpdatePacket()
    {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    /**
     * Converts the initial chunk data of this block entity into an NBT compound.
     * This allows the block entity to serialize its initial state for sending
     * to clients when the chunk is loaded. This includes the inventory and any
     * other relevant data necessary for the client to replicate the block entity's
     * state accurately.
     *
     * @param registries The wrapper lookup registries used for serialization.
     *
     * @return An NbtCompound containing the initial chunk data of the block entity.
     */
    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registries)
    {
        NbtCompound nbt = super.toInitialChunkDataNbt(registries);
        writeNbt(nbt, registries);
        return nbt;
    }
}