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

import jiraiyah.jiralib.network.BlockPosPayload;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

/**
 * Represents an abstract base class for block entities that require periodic updates and
 * can interact with extended screen handlers.
 *
 * <p>The {@code UpdatableBE} class extends {@link BlockEntity} and implements the
 * {@link ExtendedScreenHandlerFactory} interface with a {@link BlockPosPayload} type parameter.
 * This design allows block entities to manage their state updates and provide custom
 * screen handling capabilities.
 *
 * <p>Subclasses of {@code UpdatableBE} should implement the necessary logic to handle
 * block entity updates, such as synchronizing state changes with clients or saving
 * data to persistent storage. Additionally, they should define how the block entity
 * interacts with screen handlers, enabling custom user interfaces for interaction.
 *
 * <p>Example usage:
 * <pre>
 * {@code
 * public class CustomBlockEntity extends UpdatableBE {
 *     // Implement abstract methods and additional logic
 * }
 * }
 * </pre>
 *
 * <p>Key responsibilities of this class include:
 * <ul>
 *   <li>Tracking and managing block entity state changes.</li>
 *   <li>Providing mechanisms for client-server synchronization.</li>
 *   <li>Facilitating interaction with custom screen handlers.</li>
 * </ul>
 *
 * @author TurtyWurty, Modified by Jiraiyah
 */
@SuppressWarnings("unused")
public abstract class UpdatableBE extends NoScreenUpdatableBE implements ExtendedScreenHandlerFactory<BlockPosPayload>
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
    public UpdatableBE(BlockEntityType<?> type, BlockPos pos, BlockState state)
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
     *         This payload is used to synchronize the client with the server, ensuring
     *         that the correct block entity's screen is opened.
     */
    @Override
    public BlockPosPayload getScreenOpeningData(ServerPlayerEntity player)
    {
        return new BlockPosPayload(this.pos);
    }
}