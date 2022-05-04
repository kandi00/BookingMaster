using ApiHeroku.Data;
using ApiHeroku.Data.Model;
using ApiHeroku.Exceptions;
using Microsoft.EntityFrameworkCore;

namespace ApiHeroku.Repositories
{
    public class AccommodationRepository : IAccommodationRepository
    {
        private readonly AppDbContext _context;

        public AccommodationRepository(AppDbContext context)
        {
            _context = context;
        }


        public async Task<IEnumerable<Accommodation>> GetAccomodations()
        {
            try
            {
                var accommodations = _context.Accomodations;
                return accommodations.ToListAsync().Result;
            }
            catch (Exception ex)
            {
                throw new GetRequestException(ex.Message);
            }
        }

        public async Task<IEnumerable<Accommodation>> GetAccomodationsByLocation(string? Location, DateOnly FromDate, DateOnly ToDate)
        {
            try
            {
                //get booked rooms
                var bookedRooms = _context.Bookings
                    .Where(a => (a.from_date <= FromDate && FromDate < a.to_date) || (a.from_date < ToDate && ToDate <= a.to_date))
                    .Select(a => a.RoomId)
                    .ToListAsync().Result;
                //get accommodations with free rooms
                var accommodations = _context.Accomodations
                   .Select(a =>
                   new Accommodation
                   {
                       ID = a.ID,
                       Name = a.Name,
                       Phone_number = a.Phone_number,
                       Type = a.Type,
                       Location = a.Location,
                       Total_Number_Of_Rooms = a.Total_Number_Of_Rooms,
                       IsPublished = a.IsPublished,
                       Rooms = a.Rooms.Select(r => r)
                                      .Where(r => !bookedRooms.Contains(r.ID)).ToList() //free rooms
                   })
                   .Where(a => a.Location.Equals(Location)).ToListAsync().Result;
                return accommodations;
            }
            catch (Exception ex)
            {
                throw new GetRequestException(ex.Message);
            }
        }

        public async Task<IEnumerable<Accommodation>> GetAccomodationsByName(string? Name, DateOnly FromDate, DateOnly ToDate)
        {
            try
            {
                //get booked rooms
                var bookedRooms = _context.Bookings
                    .Where(a => (a.from_date <= FromDate && FromDate < a.to_date) || (a.from_date < ToDate && ToDate <= a.to_date))
                    .Select(a => a.RoomId)
                    .ToListAsync().Result;
                var accommodations = _context.Accomodations
                   .Select(a =>
                   new Accommodation
                   {
                       Name = a.Name,
                       Phone_number = a.Phone_number,
                       Type = a.Type,
                       Location = a.Location,
                       Total_Number_Of_Rooms = a.Total_Number_Of_Rooms,
                       IsPublished = a.IsPublished,
                       Rooms = a.Rooms.Select(p => p)
                                      .Where(r => !bookedRooms.Contains(r.ID)).ToList() //free rooms.ToList()
                   })
                   .Where(a => a.Name.Contains(Name));
                return accommodations.ToListAsync().Result;
            }
            catch (Exception ex)
            {
                throw new GetRequestException(ex.Message);
            }
        }
    }
}
