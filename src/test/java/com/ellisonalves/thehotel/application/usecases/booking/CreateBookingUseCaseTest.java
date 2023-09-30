package com.ellisonalves.thehotel.application.usecases.booking;

import com.ellisonalves.thehotel.application.TimeHelper;
import com.ellisonalves.thehotel.application.vo.err.UseCaseResult;
import com.ellisonalves.thehotel.domain.entity.Accommodation;
import com.ellisonalves.thehotel.domain.entity.Booking;
import com.ellisonalves.thehotel.domain.entity.Guest;
import com.ellisonalves.thehotel.domain.repository.AccomodationRepository;
import com.ellisonalves.thehotel.domain.repository.BookingRepository;
import com.ellisonalves.thehotel.domain.repository.GuestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.BeanUtils;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateBookingUseCaseTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private AccomodationRepository roomRepository;

    @Mock
    private GuestRepository guestRepository;

    @Mock
    private TimeHelper timeHelper;

    @InjectMocks
    private CreateBookingUseCase useCase;

    private static final Instant TODAY = Instant.parse("2023-08-19T11:18:55.00Z");

    private static final Clock CLOCK_FIXED = Clock.fixed(TODAY, ZoneOffset.UTC);

    private static final TimeHelper TIME_HELPER = new TimeHelper(CLOCK_FIXED);

    private static final Instant BOOKING_START_DATE = TODAY.truncatedTo(ChronoUnit.DAYS);

    private static final Instant BOOKING_END_DATE = BOOKING_START_DATE.plus(3, ChronoUnit.DAYS)
            .atZone(CLOCK_FIXED.getZone()).withHour(23).withMinute(59).withSecond(59).toInstant();

    private static final UUID ROOM_ID = UUID.randomUUID();

    private static final UUID GUEST_ID = UUID.randomUUID();

    private static final UUID CREATED_BOOKING_ID = UUID.randomUUID();

    private static final CreateBookingInput VALID_BOOKING_INPUT = createBookingInput();

    @BeforeEach
    public void beforeEach() {
        useCase = new CreateBookingUseCase(bookingRepository, roomRepository, guestRepository, TIME_HELPER);
    }

    @Test
    void shouldCreateBookingSuccessfully() {
        var newBooking = createBookingFromInput(VALID_BOOKING_INPUT);

        var createdBooking = new Booking();
        BeanUtils.copyProperties(newBooking, createdBooking);
        createdBooking.setId(CREATED_BOOKING_ID);

        whenPersistNewBooking(newBooking).thenReturn(createdBooking);

        whenFindBookingsRepository(VALID_BOOKING_INPUT).thenReturn(Collections.emptyList());
        whenFindValidRoomById().thenReturn(Optional.of(newRoom()));
        whenFindValidGuestById().thenReturn(Optional.of(newGuest()));

        var bookingToBePersisted = createBookingFromInput(VALID_BOOKING_INPUT);

        var actual = useCase.execute(VALID_BOOKING_INPUT);

        assertEquals(UseCaseResult.ok(CREATED_BOOKING_ID.toString(), "Created!"), actual);

        verify(bookingRepository).persist(bookingToBePersisted);
    }

    @Test
    void shouldNotBookWhenThereAreMissingProperties() {
        var actual = useCase.execute(new CreateBookingInput(null, null, null, null));

        assertEquals(UseCaseResult.unprocessableFailure("Missing mandatory fields"), actual);

        verify(bookingRepository, never()).persist(any());
    }

    @Test
    void shouldNotBookWhenStartAndEndDatesAreInThePast() {
        var yesterday = TODAY.minus(1, ChronoUnit.DAYS);
        var lastWeek = yesterday.minus(5, ChronoUnit.DAYS);

        var actual = useCase.execute(new CreateBookingInput(UUID.randomUUID(), UUID.randomUUID(), lastWeek, yesterday));

        assertEquals(
                UseCaseResult.unprocessableFailure("Bookings with start and end dates in the past are not allowed"),
                actual);

        verify(bookingRepository, never()).persist(any());
    }

    @Test
    void shouldNotBookWhenStartDateIsAfterEndDate() {
        var nextWeek = TODAY.plus(5, ChronoUnit.DAYS);

        var actual = useCase.execute(new CreateBookingInput(UUID.randomUUID(), UUID.randomUUID(), nextWeek, TODAY));

        assertEquals(UseCaseResult.unprocessableFailure("Start date MUST be before end date"), actual);

        verify(bookingRepository, never()).persist(any());
    }

    @Test
    void shouldNotBookWhenTheRoomIsAlreadyBooked() {
        whenFindBookingsRepository(VALID_BOOKING_INPUT)
                .thenReturn(List.of(createBookingFromInput(VALID_BOOKING_INPUT)));

        var actual = useCase.execute(VALID_BOOKING_INPUT);

        assertEquals(UseCaseResult.unprocessableFailure("Booking not available"), actual);

        verify(bookingRepository, never()).persist(any());
    }

    @Test
    void shouldNotBookWhenTheRoomDoesNotExist() {
        whenFindBookingsRepository(VALID_BOOKING_INPUT).thenReturn(Collections.emptyList());

        var actual = useCase.execute(VALID_BOOKING_INPUT);

        assertEquals(UseCaseResult.unprocessableFailure("The room does not exist"), actual);

        verify(bookingRepository, never()).persist(any());
    }

    @Test
    void shouldNotBookWhenTheGuestDoesNotExist() {
        whenFindBookingsRepository(VALID_BOOKING_INPUT).thenReturn(Collections.emptyList());
        whenFindValidRoomById().thenReturn(Optional.of(newRoom()));

        var actual = useCase.execute(VALID_BOOKING_INPUT);

        assertEquals(UseCaseResult.unprocessableFailure("The guest does not exist"), actual);

        verify(bookingRepository, never()).persist(any());
    }

    private Booking createBookingFromInput(CreateBookingInput input) {
        var newBooking = new Booking();
        var room = new Accommodation();
        room.setId(input.roomId());
        var guest = new Guest();
        guest.setId(input.guestId());
        newBooking.setRoom(room);
        newBooking.setGuest(guest);
        newBooking.setStartDate(input.startDate());
        newBooking.setEndDate(input.endDate());
        return newBooking;
    }

    private Accommodation newRoom() {
        var room = new Accommodation();
        room.setId(VALID_BOOKING_INPUT.roomId());
        return room;
    }

    private Guest newGuest() {
        var guest = new Guest();
        guest.setId(GUEST_ID);
        return guest;
    }

    private static CreateBookingInput createBookingInput() {
        return new CreateBookingInput(ROOM_ID, GUEST_ID, BOOKING_START_DATE, BOOKING_END_DATE);
    }

    private OngoingStubbing<List<Booking>> whenFindBookingsRepository(CreateBookingInput input) {
        return when(bookingRepository.findBookings(input.roomId(), input.startDate(), input.endDate()));
    }

    private OngoingStubbing<Optional<Guest>> whenFindValidGuestById() {
        return when(guestRepository.findById(VALID_BOOKING_INPUT.guestId()));
    }

    private OngoingStubbing<Optional<Accommodation>> whenFindValidRoomById() {
        return when(roomRepository.findById(VALID_BOOKING_INPUT.roomId()));
    }

    private OngoingStubbing<Booking> whenPersistNewBooking(Booking newBooking) {
        return when(bookingRepository.persist(newBooking));
    }

    private OngoingStubbing<Instant> whenGetFirstMinuteOfToday() {
        return when(timeHelper.getFirstMinuteOfToday());
    }

}
