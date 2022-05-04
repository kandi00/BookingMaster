using ApiHeroku.Data.Response;
using ApiHeroku.Data.ViewModel;

namespace ApiHeroku.Services
{
    public interface IBookingService
    {
        public Task<BookingResponse> AddNewBooking(BookingViewModel newBooking);

        public Task<BookingsByUserResponse> GetBookingsByUser(string? Email);
        public Task<DeleteBookingResponse> DeleteBooking(int iD);
    }
}
