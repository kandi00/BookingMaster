using ApiHeroku.Data.Model;

namespace ApiHeroku.Repositories
{
    public interface IBookingRepository
    {
        public Task<Booking> AddNewBooking(Booking newBooking);
    }
}
