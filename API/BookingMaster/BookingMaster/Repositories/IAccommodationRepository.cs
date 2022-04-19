using BookingMaster.Data.Model;

namespace BookingMaster.Repositories
{
    public interface IAccommodationRepository
    {
        public Task<IEnumerable<Accommodation>> GetAccomodations();
    }
}
