using ApiHeroku.Data.ViewModel;
using ApiHeroku.Repositories;

namespace ApiHeroku.Services
{
    public class BookingService : IBookingService
    {
        private readonly IBookingRepository _bookingRepository;

        public BookingService(IBookingRepository bookingRepository)
        {
            _bookingRepository = bookingRepository;
        }
        public void AddNewBooking(BookingViewModel newBooking)
        {
            _bookingRepository.AddNewBooking(newBooking);
        }
    }
}
