package com.ellisonalves.thehotel.domain.entity;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;

@Entity
public class Booking extends BaseEntity<UUID> {

	private static final long serialVersionUID = 1L;

	private Guest guest;
	private Room room;
	private Instant startDate;
	private Instant endDate;

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Override
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	@ManyToOne
	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	@ManyToOne
	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Instant getStartDate() {
		return startDate;
	}

	public void setStartDate(Instant startDate) {
		this.startDate = startDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Instant getEndDate() {
		return endDate;
	}

	public void setEndDate(Instant endDate) {
		this.endDate = endDate;
	}

	@Override
	public boolean equalTo(Object o) {
		Booking other = (Booking) o;
		return Objects.equals(id, other.getId());
	}

	@Override
	public int hashCodePrime() {
		return 31;
	}

	@Transient
	public boolean isMissingMandatoryFields() {
		return !(hasGuestId() || hasRoomId() || hasStartDate() || hasEndDate());
	}

	@Transient
	public boolean isStartOrEndDatesBefore(Instant instant) {
		return hasStartAndEndDates() && startDate.isBefore(instant) || endDate.isBefore(instant);
	}

	@Transient
	public boolean isStartDateAfterEndDate() {
		return hasStartAndEndDates() && startDate.isAfter(endDate);
	}

	private boolean hasEndDate() {
		return endDate != null;
	}

	private boolean hasStartDate() {
		return startDate != null;
	}

	private boolean hasRoomId() {
		return room != null && room.getId() != null;
	}

	private boolean hasGuestId() {
		return guest != null && guest.getId() != null;
	}

	private boolean hasStartAndEndDates() {
		return hasStartDate() && hasEndDate();
	}

}
