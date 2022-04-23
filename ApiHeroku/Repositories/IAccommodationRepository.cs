using ApiHeroku.Data.Model;

namespace ApiHeroku.Repositories
{
    public interface IAccommodationRepository
    {
        public Task<IEnumerable<Accommodation>> GetAccomodations();
        public Task<IEnumerable<Accommodation>> GetAccomodationsByLocation(string Location);
        public Task<IEnumerable<Accommodation>> GetAccomodationsByName(string Name);
    }
}
