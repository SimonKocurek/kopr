package kopr.nikdy.viac.entities;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class ParkingTicket {

    /**
     * Parking ticket id, unique across all parking lots
     */
    private UUID id;

    /**
     * Id of car the ticket belongs to
     */
    @SerializedName("car_licence_plate")
    private String carLicencePlate;

    /**
     * Identifier of parking lot the ticket belongs to
     */
    @SerializedName("parking_lot_id")
    private Integer parkingLotId;

    /**
     * Time the ticket was issued
     */
    @SerializedName("arrival_time")
    private LocalDateTime arrivalTime;

    /**
     * Time the car left parking lot
     */
    @SerializedName("leave_time")
    private LocalDateTime leaveTime;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCarLicencePlate() {
        return carLicencePlate;
    }

    public void setCarLicencePlate(String carLicencePlate) {
        this.carLicencePlate = carLicencePlate;
    }

    public Integer getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(Integer parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public LocalDateTime getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(LocalDateTime leaveTime) {
        this.leaveTime = leaveTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingTicket that = (ParkingTicket) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(carLicencePlate, that.carLicencePlate) &&
                Objects.equals(parkingLotId, that.parkingLotId) &&
                Objects.equals(arrivalTime, that.arrivalTime) &&
                Objects.equals(leaveTime, that.leaveTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ParkingTicket{" +
                "id=" + id +
                ", carLicencePlate='" + carLicencePlate + '\'' +
                ", parkingLotId=" + parkingLotId +
                ", arrivalTime=" + arrivalTime +
                ", leaveTime=" + leaveTime +
                '}';
    }

}
