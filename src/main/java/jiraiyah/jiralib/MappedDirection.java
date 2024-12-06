package jiraiyah.jiralib;

import net.minecraft.util.math.Direction;

import java.util.HashMap;
import java.util.Map;

public enum MappedDirection
{
    DOWN, UP, NORTH, SOUTH, WEST, EAST, NONE;

    private static final Map<Direction, MappedDirection> directionToMapped = new HashMap<>();
    private static final Map<MappedDirection, Direction> MappedToDirection = new HashMap<>();

    static {
        // Mapping Direction to JDirection
        directionToMapped.put(Direction.DOWN, MappedDirection.DOWN);
        directionToMapped.put(Direction.UP, MappedDirection.UP);
        directionToMapped.put(Direction.NORTH, MappedDirection.NORTH);
        directionToMapped.put(Direction.SOUTH, MappedDirection.SOUTH);
        directionToMapped.put(Direction.WEST, MappedDirection.WEST);
        directionToMapped.put(Direction.EAST, MappedDirection.EAST);

        // Mapping JDirection to Direction
        MappedToDirection.put(MappedDirection.DOWN, Direction.DOWN);
        MappedToDirection.put(MappedDirection.UP, Direction.UP);
        MappedToDirection.put(MappedDirection.NORTH, Direction.NORTH);
        MappedToDirection.put(MappedDirection.SOUTH, Direction.SOUTH);
        MappedToDirection.put(MappedDirection.WEST, Direction.WEST);
        MappedToDirection.put(MappedDirection.EAST, Direction.EAST);
    }

    public static MappedDirection fromDirection(Direction direction) {
        return direction == null ? MappedDirection.NONE : directionToMapped.get(direction);
    }

    public static Direction toDirection(MappedDirection direction) {
        return direction == MappedDirection.NONE ? null : MappedToDirection.get(direction);
    }
}