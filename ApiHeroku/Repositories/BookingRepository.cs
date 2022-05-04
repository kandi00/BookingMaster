using ApiHeroku.Data;
using ApiHeroku.Data.Model;
using ApiHeroku.Exceptions;
using Microsoft.EntityFrameworkCore;

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
                    .Where(a => (a.from_date <= newBooking.from_date && newBooking.from_date < a.to_date) || (a.from_date < newBooking.to_date && newBooking.to_date <= a.to_date))
                    .Select(a => a.RoomId);
                if (!bookedRooms.Contains(newBooking.RoomId))
                {
                    var response = _context.Bookings.Add(newBooking);
                    _context.SaveChanges();
                    return response.Entity;
                }
                else
                {
                    throw new PostRequestException("This room is not free!");
                }
            }
            catch (Exception ex)
            {
                throw new PostRequestException(ex.Message);
            }
        }

        

        public async Task<IEnumerable<Booking>> GetBookingsByUser(string? Email)
        {
            try
            {


                var user = _context.UserInfosExample.Where(a => a.Email == Email).FirstOrDefault();
                var bookings = _context.Bookings.Select(a => new Booking { 
                   ID = a.ID,
                   from_date = a.from_date,
                   to_date = a.to_date,
                   UserId = a.UserId,
                   RoomId = a.RoomId,
                   booking_date = a.booking_date,
                   UserExample = a.UserExample,
                   Room = a.Room,
                   
                }).Where(a => a.UserId == user.ID).ToListAsync().Result;

                return bookings;


            }
            catch (Exception ex)
            {
                throw new PostRequestException(ex.Message);
            }
        }


        public async Task<bool> DeleteBooking(int ID)
        {
            var booking = await _context.Bookings.FindAsync(ID);
            if(booking == null)
            {
                return false;
            }

            _context.Bookings.Remove(booking);
            await _context.SaveChangesAsync();
            return true;
        }
    }

    
}
