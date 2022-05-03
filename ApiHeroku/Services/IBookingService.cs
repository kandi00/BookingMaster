using ApiHeroku.Data.ViewModel;

namespace ApiHeroku.Services
{
    public interface IBookingService
    {
        public void AddNewBooking(BookingViewModel newBooking);
    }
}
