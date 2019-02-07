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
    private Integer capacity;

    /**
     * Human readable identifier of parking lot (eg. GPS, street name)
     */
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingLot that = (ParkingLot) o;
        return capacity.equals(that.capacity) &&
                Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
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
                ", name='" + name + '\'' +
                '}';
    }

}
