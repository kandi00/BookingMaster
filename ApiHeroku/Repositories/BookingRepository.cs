using ApiHeroku.Data;
using ApiHeroku.Data.Model;
using ApiHeroku.Data.ViewModel;

namespace ApiHeroku.Repositories
{
    public class BookingRepository : IBookingRepository
    {
        private readonly AppDbContext _context;

        public BookingRepository(AppDbContext context)
        {
            _context = context;
        }

        public void AddNewBooking(BookingViewModel newBooking)
        {
            Booking booking = new Booking()
            {
                from_date = newBooking.from_date,
                to_date = newBooking.to_date,
                booking_date = DateTime.Now,
                RoomId = newBooking.RoomId,
                UserId = newBooking.UserId
            };
            _context.Bookings.Add(booking);
            _context.SaveChanges();
        }

    }
}
