package kopr.nikdy.viac.entities;

import java.util.Objects;

public class ParkingLot {

    /**
     * Identifier of parking lot
     */
    private Integer id;

    /**
     * Amount of spaces for cars in the parking lot
     */
    private int capacity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingLot that = (ParkingLot) o;
        return capacity == that.capacity &&
                Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ParkingLot{" +
                "id=" + id +
                ", capacity=" + capacity +
                '}';
    }

}
