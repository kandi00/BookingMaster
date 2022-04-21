using BookingMaster.Data.Model;

namespace BookingMaster.Repositories
{
    public interface IAccommodationRepository
    {
        public Task<IEnumerable<Accommodation>> GetAccomodations();
        public Task<IEnumerable<Accommodation>> GetAccomodationsByLocation(string Location);
        public Task<IEnumerable<Accommodation>> GetAccomodationsByName(string Name);
    }
}
