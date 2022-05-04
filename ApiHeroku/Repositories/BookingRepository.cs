using ApiHeroku.Data;
using ApiHeroku.Data.Model;
using ApiHeroku.Exceptions;

namespace ApiHeroku.Repositories
{
    public class BookingRepository : IBookingRepository
    {
        private readonly AppDbContext _context;

        public BookingRepository(AppDbContext context)
        {
            _context = context;
        }

        public async Task<Booking> AddNewBooking(Booking newBooking)
        {
            try
            {
                //get booked rooms
                var bookedRooms = _context.Bookings
                    //.Where(a => (a.from_date <= newBooking.from_date && newBooking.from_date < a.to_date) || (a.from_date < newBooking.to_date && newBooking.to_date <= a.to_date))
                    .Select(a => a.RoomId);
                //if (!bookedRooms.Contains(newBooking.RoomId))
                //{
                    var response = _context.Bookings.Add(newBooking);
                    _context.SaveChanges();
                    return response.Entity;
                //}
                //else
                //{
                //    throw new PostRequestException("This room is not free!");
                //}
            }
            catch (Exception ex)
            {
                throw new PostRequestException(ex.Message);
            }
        }

    }
}
