using ApiHeroku.Data.Response;

namespace ApiHeroku.Services
{
    public interface IAccommodationService
    {
        public Task<AccommodationsResponse> GetAccomodations();
        public Task<AccommodationsResponse> GetAccomodationsByLocation(string? Location, DateTime FromDate, DateTime ToDate);
        public Task<AccommodationsResponse> GetAccomodationsByName(string? Name, DateTime FromDate, DateTime ToDate);
    }
}
