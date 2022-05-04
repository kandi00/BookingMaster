using ApiHeroku.Data.Model;

namespace ApiHeroku.Repositories
{
    public interface IAccommodationRepository
    {
        public Task<IEnumerable<Accommodation>> GetAccomodations();
        public Task<IEnumerable<Accommodation>> GetAccomodationsByLocation(string Location, DateOnly FromDate, DateOnly ToDate);
        public Task<IEnumerable<Accommodation>> GetAccomodationsByName(string Name, DateOnly FromDate, DateOnly ToDate);
    }
}
