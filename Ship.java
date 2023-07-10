package application;

import java.util.List;

/**
 * The Ship class represents a ship in a game of Battleship.
 * Each ship has a size, an orientation, a list of cells it occupies on the board, and an owner.
 */
public class Ship {
    private int size;
    private int hitCount = 0;
    private List<Cell> location;
    private String color;
    private String direction;
    private String owner; 

    /**
     * Constructor to create a new Ship with a specified size and orientation.
     *
     * @param size The size of the ship.
     * @param direction The orientation of the ship.
     */
    public Ship(int size, String direction) {
        this.size = size;
        this.direction = direction;
    }

    /**
     * Constructor to create a new Ship with a specified size, orientation, and owner.
     *
     * @param size The size of the ship.
     * @param direction The orientation of the ship.
     * @param owner The owner of the ship ("player" or "AI").
     */
    public Ship(int size, String direction, String owner) {
        this.size = size;
        this.direction = direction;
        this.owner = owner;
    }

    // Getters and setters
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<Cell> getLocation() {
        return location;
    }

    public void setLocation(List<Cell> location) {
        this.location = location;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    /**
     * Increase the hit count by one when a ship is hit.
     */
    public void hit() {
        hitCount++;
    }

    /**
     * Check if a ship is sunk, i.e., if the hit count is equal to or greater than the ship size.
     *
     * @return true if the ship is sunk, false otherwise.
     */
    public boolean isSunk() {
        return hitCount >= size;
    }
}
