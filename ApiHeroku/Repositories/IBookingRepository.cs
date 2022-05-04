using ApiHeroku.Data.Model;

namespace ApiHeroku.Repositories
{
    public interface IBookingRepository
    {
        public Task<Booking> AddNewBooking(Booking newBooking);

        public Task<IEnumerable<Booking>> GetBookingsByUser(string? Email);

        public Task<bool> DeleteBooking(int ID);
    }
}
