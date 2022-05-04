using ApiHeroku.Data.Model;
using ApiHeroku.Data.Response;
using ApiHeroku.Data.ViewModel;
using ApiHeroku.Exceptions;
using ApiHeroku.Repositories;
using ApiHeroku.Utils;

namespace ApiHeroku.Services
{
    public class BookingService : IBookingService
    {
        private readonly IBookingRepository _bookingRepository;

        public BookingService(IBookingRepository bookingRepository)
        {
            _bookingRepository = bookingRepository;
        }
        public async Task<BookingResponse> AddNewBooking(BookingViewModel newBooking)
        {
            Booking booking = new Booking()
            {
                from_date = newBooking.from_date,
                to_date = newBooking.to_date,
                booking_date = DateOnly.FromDateTime(DateTime.Now),
                RoomId = newBooking.RoomId,
                UserId = newBooking.UserId
            };
            BookingResponse response = new BookingResponse();

            try
            {
                response.Booking = await _bookingRepository.AddNewBooking(booking);
                if (response.Booking != null)
                {
                    response.Code = 200;
                    response.Message = APISuccessCodes.POST_BOOKING_SUCCESS;
                }
                return response;
            }
            catch (PostRequestException ex)
            {
                throw new PostException(ex.Message);
            }
        }
    }
}
