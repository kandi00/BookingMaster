using BookingMaster.Data;
using BookingMaster.Data.Model;
using BookingMaster.Exceptions;
using Microsoft.EntityFrameworkCore;

namespace BookingMaster.Repositories
{
    public class RoomRepository : IRoomRepository
    {
        private readonly AppDbContext _context;

        public RoomRepository(AppDbContext context)
        {
            _context = context;
        }
        public async Task<IEnumerable<Room>> GetRoomsByLocation(string Location)
        {
            try
            {
                var rooms = _context.Rooms.Where(r => r.Accommodation.Location.Equals(Location));
                return rooms.ToListAsync().Result;
            }
            catch (Exception ex)
            {
                throw new GetRequestException(ex.Message);
            }
        }
    }
}
