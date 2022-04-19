using BookingMaster.Data;
using BookingMaster.Data.Model;
using BookingMaster.Exceptions;
using Microsoft.EntityFrameworkCore;

namespace BookingMaster.Repositories
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
                var accomodations = _context.Accomodations;
                return accomodations.ToListAsync().Result;
            }
            catch (Exception ex)
            {
                throw new GetRequestException(ex.Message);
            }
        }
    }
}
