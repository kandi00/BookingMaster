using ApiHeroku.Data.ViewModel;

namespace ApiHeroku.Repositories
{
    public interface IBookingRepository
    {
        public void AddNewBooking(BookingViewModel newBooking);
    }
}
